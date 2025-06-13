package net.paglalayag.cmphotwiredemo

import android.app.Application
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.bridge.KotlinXJsonConverter
import dev.hotwire.core.config.Hotwire
import dev.hotwire.core.turbo.config.PathConfiguration
import dev.hotwire.navigation.config.registerBridgeComponents
import dev.hotwire.navigation.config.registerFragmentDestinations
import dev.hotwire.navigation.fragments.HotwireWebFragment
import net.paglalayag.cmphotwiredemo.components.FavoriteToggleComponent
import net.paglalayag.cmphotwiredemo.di.initKoin
import net.paglalayag.cmphotwiredemo.fragments.BoundHotwireWebFragment
import net.paglalayag.cmphotwiredemo.fragments.FavoritesFragment
import org.koin.android.ext.koin.androidContext

class CMPHotwireDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.registerBridgeComponents(
            BridgeComponentFactory("favoriteToggle", ::FavoriteToggleComponent)
        )

        initKoin {
            androidContext(this@CMPHotwireDemoApplication)
        }

        Hotwire.loadPathConfiguration(
            context = this,
            location = PathConfiguration.Location(
                remoteFileUrl = "$baseURL/assets/json/android_config_v1.json"
            )
        )

        Hotwire.registerFragmentDestinations(
            BoundHotwireWebFragment::class,
            FavoritesFragment::class,
            HotwireWebFragment::class
        )

        Hotwire.config.jsonConverter = KotlinXJsonConverter()
    }
}