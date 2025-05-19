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
                handleFavoriteToggleMessage(message)
            } else -> {
                handleUnsupportedMessage(message)
            }
        }
    }

    private fun handleFavoriteToggleMessage(message: Message) {
        println("bridge message to FavoriteToggle received with event: ${message.event}")
        when(message.event) {
            "connect" -> {
                handleFavoriteToggleConnectMessage(message)
            }
            "toggle" -> {
                handleFavoriteToggleToggleMessage(message)
            } else -> {
                handleFavoriteToggleUnsupportedMessage(message)
            }
        }
    }

    private fun handleUnsupportedMessage(message: Message) {
        println("bridge message received from: ${message.component}")
    }

    private fun handleFavoriteToggleConnectMessage(message: Message) {
        println("bridge FavoriteToggle 'connect' message received with data: ${message.jsonData}")
    }

    private fun handleFavoriteToggleToggleMessage(message: Message) {
        println("bridge FavoriteToggle 'toggle' message received with data: ${message.jsonData}")
    }

    private fun handleFavoriteToggleUnsupportedMessage(message: Message) {
        println("bridge FavoriteToggle unsupported message received with event: ${message.event}")
    }
}
