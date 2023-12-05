package com.sxiaozhi.somking.di

import com.sxiaozhi.somking.api.HomeApi
import com.sxiaozhi.somking.api.UserApi
import com.sxiaozhi.somking.repository.HomeRepository
import com.sxiaozhi.somking.repository.HomeRepositoryImpl
import com.sxiaozhi.somking.repository.UserRepository
import com.sxiaozhi.somking.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


/**
 * RepoRepository binding to use in tests.
 *
 * Hilt will inject a [RepoRepositoryImpl] instead of a [RepoRepository].
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesUserRepository(userApi: UserApi): UserRepository {
        return UserRepositoryImpl(userApi)
    }

    @Provides
    @ViewModelScoped
    fun providesHomeRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepositoryImpl(homeApi)
    }

}