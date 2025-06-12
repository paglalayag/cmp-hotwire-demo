package net.paglalayag.cmphotwiredemo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.paglalayag.cmphotwiredemo.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMPHotwireDemo",
        ) {
            App()
        }
    }
}