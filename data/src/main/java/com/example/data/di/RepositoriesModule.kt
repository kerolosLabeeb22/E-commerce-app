package com.example.data.di


import com.example.data.dataSource.remote.AppOnlineDataSourceImpl
import com.example.data.repository.AppRepositoryImpl
import com.example.data.services.ApiService
import com.example.domain.repository.AppOnlineDataSource
import com.example.domain.repository.AppRepository
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
    fun provideAppOnlineDataSource(apiService: ApiService): AppOnlineDataSource {
        return AppOnlineDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideAppRepository(onlineDataSource: AppOnlineDataSource): AppRepository {
        return AppRepositoryImpl(onlineDataSource)

    }


}