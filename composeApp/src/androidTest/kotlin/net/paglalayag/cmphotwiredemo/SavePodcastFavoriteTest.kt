package net.paglalayag.cmphotwiredemo

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.DriverAtoms.webClick
import androidx.test.espresso.web.webdriver.Locator
import net.paglalayag.cmphotwiredemo.presentation.PodcastsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SmokeTest {
    @Test
    fun testPackageName() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        assertThat(context.packageName).isEqualTo("net.paglalayag.cmphotwiredemo")
    }
}

class PodcastFavoritesTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testSavePodcastToFavorites() = runComposeUiTest {
        val viewModel = PodcastsViewModel()
        setContent {
            HotwireWebScreenRoot(viewModel)
        }
        onWebView(withContentDescription("web_view_podcast"))
            .withElement(findElement(Locator.ID, "save-favorite-button"))
            .perform(webClick())
    }
}