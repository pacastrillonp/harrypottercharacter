package co.pacastrillon.harrypottercharacter.data.repository

import co.pacastrillon.harrypottercharacter.data.mapper.toCharacter
import co.pacastrillon.harrypottercharacter.data.remote.HpApiService
import co.pacastrillon.harrypottercharacter.domain.model.Character
import co.pacastrillon.harrypottercharacter.domain.model.Filter
import co.pacastrillon.harrypottercharacter.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRepositoryImpl @Inject constructor(
    private val api: HpApiService
) : CharactersRepository {

    private val mutex = Mutex()

    private val cacheByFilter = MutableStateFlow<Map<Filter, List<Character>>>(emptyMap())
    private val cacheById = MutableStateFlow<Map<String, Character>>(emptyMap())

    override fun observeCharacters(filter: Filter): Flow<List<Character>> =
        cacheByFilter.map { it[filter].orEmpty() }.distinctUntilChanged()

    override fun observeCharacter(id: String): Flow<Character?> =
        cacheById.map { it[id] }.distinctUntilChanged()

    override suspend fun refreshCharacters(filter: Filter) {
        val remote = when (filter) {
            Filter.All -> api.getCharacters()
            Filter.Students -> api.getStudents()
            Filter.Staff -> api.getStaff()
            is Filter.House -> api.getByHouse(filter.name)
        }

        val domainList = remote.map { it.toCharacter() }

        mutex.withLock {
            cacheByFilter.value = cacheByFilter.value.toMutableMap().apply {
                put(filter, domainList)
            }
            cacheById.value = cacheById.value.toMutableMap().apply {
                domainList.forEach { put(it.id, it) }
            }
        }
    }
}
