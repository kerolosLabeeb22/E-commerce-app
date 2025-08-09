package com.example.data.di


import com.example.data.dataSource.remote.AuthOnlineDataSourceImpl
import com.example.data.repository.auth.AuthRepositoryImpl
import com.example.data.services.AuthService
import com.example.domain.repository.AuthOnlineDataSource
import com.example.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Singleton
    @Provides
    fun provideAuthOnlineDataSource(AuthService: AuthService): AuthOnlineDataSource {
        return AuthOnlineDataSourceImpl(AuthService)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(onlineDataSource: AuthOnlineDataSource): AuthRepository{
        return AuthRepositoryImpl(onlineDataSource)

    }

}