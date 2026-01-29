package co.pacastrillon.harrypottercharacter.data.remote

import co.pacastrillon.harrypottercharacter.data.remote.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HpApiService {

    @GET("api/characters")
    suspend fun getCharacters(): List<CharacterDto>

    @GET("api/characters/students")
    suspend fun getStudents(): List<CharacterDto>

    @GET("api/characters/staff")
    suspend fun getStaff(): List<CharacterDto>

    @GET("api/characters/house/{house}")
    suspend fun getByHouse(@Path("house") house: String): List<CharacterDto>

}
