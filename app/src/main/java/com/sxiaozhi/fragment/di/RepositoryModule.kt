package com.sxiaozhi.fragment.di

import com.sxiaozhi.fragment.api.HomeApi
import com.sxiaozhi.fragment.api.UserApi
import com.sxiaozhi.fragment.repository.HomeRepository
import com.sxiaozhi.fragment.repository.HomeRepositoryImpl
import com.sxiaozhi.fragment.repository.UserRepository
import com.sxiaozhi.fragment.repository.UserRepositoryImpl
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