package net.paglalayag.cmphotwiredemo

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.activity.enableEdgeToEdge
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.util.applyDefaultImeWindowInsets
import net.paglalayag.cmphotwiredemo.navigation.Tab

const val baseURL = "https://paglalayag.net"

class MainActivity : HotwireActivity() {
    private val tabs = Tab.all

    override fun navigatorConfigurations() = tabs.map { tab ->
        NavigatorConfiguration(
            name = tab.name,
            startLocation = "$baseURL/${tab.path}",
            navigatorHostId = tab.navigatorHostId
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.main_nav_host).applyDefaultImeWindowInsets()
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener { tab ->
            val selectedTab = tabs.first { it.menuId == tab.itemId }
            showTab(selectedTab)
            true
        }
        showTab(tabs.first())
    }

    private fun showTab(tab: Tab) {
        tabs.forEach {
            val view = findViewById<View>(it.navigatorHostId)
            view.visibility = if (it == tab) View.VISIBLE else View.GONE
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
