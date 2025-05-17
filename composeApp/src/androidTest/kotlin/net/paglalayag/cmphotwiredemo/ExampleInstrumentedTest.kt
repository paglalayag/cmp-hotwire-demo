package net.paglalayag.cmphotwiredemo

import androidx.test.core.app.ApplicationProvider
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class SmokeTest {
    @Test
    fun testPackageName() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        assertThat(context.packageName).isEqualTo("net.paglalayag.cmphotwiredemo")
    }
}