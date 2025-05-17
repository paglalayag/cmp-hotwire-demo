package net.paglalayag.cmphotwiredemo.components

import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination

class FavoriteToggleComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    override fun onReceive(message: Message) {
        when(message.component) {
            "favoriteToggle" -> {
               println("bridge message to FavoriteToggle received with event: ${message.event}")
            } else -> {
                println("bridge message received from: ${message.component}")
            }
        }
    }
}
