package co.pacastrillon.harrypottercharacter.data.di

import co.pacastrillon.harrypottercharacter.data.remote.HpApiService
import co.pacastrillon.harrypottercharacter.data.repository.CharactersRepositoryImpl
import co.pacastrillon.harrypottercharacter.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersDataModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(api: HpApiService): CharactersRepository =
        CharactersRepositoryImpl(api)
}