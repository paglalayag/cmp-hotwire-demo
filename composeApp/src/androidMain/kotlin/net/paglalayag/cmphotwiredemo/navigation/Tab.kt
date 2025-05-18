package net.paglalayag.cmphotwiredemo.navigation

import androidx.annotation.IdRes
import net.paglalayag.cmphotwiredemo.R

data class Tab(
    val name: String,
    val path: String,
    @IdRes val menuId: Int,
    @IdRes val navigatorHostId: Int
) {
    companion object {
        val all = listOf(
            Tab(
                name = "podcast",
                path = "podcast",
                menuId = R.id.main_nav_host,
                navigatorHostId = R.id.podcast_fragment,
            )
        )
    }
}
