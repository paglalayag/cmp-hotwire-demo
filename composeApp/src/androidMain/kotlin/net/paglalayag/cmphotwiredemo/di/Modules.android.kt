package net.paglalayag.cmphotwiredemo.di

import io.ktor.client.engine.HttpClientEngine
import net.paglalayag.cmphotwiredemo.data.database.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import io.ktor.client.engine.okhttp.OkHttp

actual val platformModule: Module
    get() = module {
        single { DatabaseFactory(androidApplication()) }

        single<HttpClientEngine> { OkHttp.create() }
    }