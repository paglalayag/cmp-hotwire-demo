package net.paglalayag.cmphotwiredemo.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import net.paglalayag.cmphotwiredemo.presentation.PodcastsViewModel

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::PodcastsViewModel)
}