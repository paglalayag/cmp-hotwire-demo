package net.paglalayag.cmphotwiredemo.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import net.paglalayag.cmphotwiredemo.presentation.PodcastsViewModel
import org.koin.core.module.dsl.singleOf
import net.paglalayag.cmphotwiredemo.data.repository.DefaultPodcastRepository
import net.paglalayag.cmphotwiredemo.domain.PodcastRepository
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import net.paglalayag.cmphotwiredemo.core.data.HttpClientFactory
import net.paglalayag.cmphotwiredemo.data.database.DatabaseFactory
import net.paglalayag.cmphotwiredemo.data.database.PodcastDatabase
import net.paglalayag.cmphotwiredemo.data.network.KtorRemotePodcastDataSource
import net.paglalayag.cmphotwiredemo.data.network.RemotePodcastDataSource
import org.koin.dsl.bind

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::PodcastsViewModel)

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<PodcastDatabase>().podcastDao }
    singleOf(::DefaultPodcastRepository).bind<PodcastRepository>()

    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemotePodcastDataSource).bind<RemotePodcastDataSource>()

}