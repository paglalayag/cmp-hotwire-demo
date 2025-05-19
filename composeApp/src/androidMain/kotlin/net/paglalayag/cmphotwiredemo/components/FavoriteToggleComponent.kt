package net.paglalayag.cmphotwiredemo.components

import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.paglalayag.cmphotwiredemo.fragments.BoundHotwireWebFragment

class FavoriteToggleComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val fragment: BoundHotwireWebFragment
    get() = bridgeDelegate.destination.fragment as BoundHotwireWebFragment

    override fun onReceive(message: Message) {
        val data = message.data<MessageData>() ?: return

        when(message.component) {
            "favoriteToggle" -> {
                handleFavoriteToggleMessage(message, data)
            } else -> {
                handleUnsupportedMessage(message)
            }
        }
    }

    private fun handleFavoriteToggleMessage(message: Message, data: MessageData) {
        println("bridge message to FavoriteToggle received with event: ${message.event}")
        when(message.event) {
            "connect" -> {
                handleFavoriteToggleConnectMessage(data)
            }
            "toggle" -> {
                handleFavoriteToggleToggleMessage(data)
            } else -> {
                handleFavoriteToggleUnsupportedMessage(message)
            }
        }
    }

    private fun handleUnsupportedMessage(message: Message) {
        println("bridge message received from: ${message.component}")
    }

    private fun handleFavoriteToggleConnectMessage(data: MessageData) {
        println("bridge FavoriteToggle 'connect' message received with data: $data")
        fragment.setEpisodeFromBoundFragment(data.episodeUrl)
    }

    private fun handleFavoriteToggleToggleMessage(data: MessageData) {
        println("bridge FavoriteToggle 'toggle' message received with data: $data")
    }

    private fun handleFavoriteToggleUnsupportedMessage(message: Message) {
        println("bridge FavoriteToggle unsupported message received with event: ${message.event}")
    }
}

@Serializable
data class MessageData(
    @SerialName("episode_url") val episodeUrl: String
)
