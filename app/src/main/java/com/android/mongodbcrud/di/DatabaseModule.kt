package com.android.mongodbcrud.di

import com.android.mongodbcrud.model.BasicInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideRealm() : Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                BasicInfo::class
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }
}