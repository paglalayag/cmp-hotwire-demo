package net.paglalayag.cmphotwiredemo

import android.app.Application
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.config.Hotwire
import dev.hotwire.navigation.config.registerBridgeComponents
import net.paglalayag.cmphotwiredemo.components.FavoriteToggleComponent

class CMPHotwireDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.registerBridgeComponents(
            BridgeComponentFactory("favoriteToggle", ::FavoriteToggleComponent)
        )
    }
}