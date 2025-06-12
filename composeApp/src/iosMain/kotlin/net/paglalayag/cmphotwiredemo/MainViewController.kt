package net.paglalayag.cmphotwiredemo

import androidx.compose.ui.window.ComposeUIViewController
import net.paglalayag.cmphotwiredemo.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }