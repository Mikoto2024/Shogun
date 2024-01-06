package com.lolideveloper.shogun.Core

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun getContext(app: Application): Context {
        return app.applicationContext
    }
}

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

}

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

}