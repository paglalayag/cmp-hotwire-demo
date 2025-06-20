package net.paglalayag.cmphotwiredemo.components

import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.KotlinXJsonConverter
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.paglalayag.cmphotwiredemo.domain.PodcastsAction
import net.paglalayag.cmphotwiredemo.fragments.BoundHotwireWebFragment

class FavoriteToggleComponent(
    name: String,
    private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
    private val fragment: BoundHotwireWebFragment
    get() = bridgeDelegate.destination.fragment as BoundHotwireWebFragment

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
                val connectMessageData = message.data<ConnectMessageData>() ?: return
                handleFavoriteToggleConnectMessage(connectMessageData)
            }
            "toggle" -> {
                val toggleMessageData = message.data<ToggleMessageData>() ?: return
                handleFavoriteToggleToggleMessage(toggleMessageData)
            }
            "setFavorite" -> {
                val toggleMessageData = message.data<ToggleMessageData>() ?: return
                handleFavoriteToggleSetFavoriteMessage(toggleMessageData)
            } else -> {
                handleFavoriteToggleUnsupportedMessage(message)
            }
        }
    }

    private fun handleUnsupportedMessage(message: Message) {
        println("bridge message received from: ${message.component}")
    }

    private fun handleFavoriteToggleConnectMessage(data: ConnectMessageData) {
        println("bridge FavoriteToggle 'connect' message received with data: $data")

        fragment.setEpisodeFromBoundFragment(data)

        replyWithFavoriteStatus(data.episodeUrl)
    }

    private fun handleFavoriteToggleToggleMessage(data: ToggleMessageData) {
        println("bridge FavoriteToggle 'toggle' message received with data: $data")

        fragment.toggleEpisodeFromBoundFragment(data)
    }

    private fun handleFavoriteToggleSetFavoriteMessage(data: ToggleMessageData) {
        println("bridge FavoriteToggle 'setFavorite' message received with data: $data")
    }

    private fun handleFavoriteToggleUnsupportedMessage(message: Message) {
        println("bridge FavoriteToggle unsupported message received with event: ${message.event}")
    }

    private fun replyWithFavoriteStatus(episodeUrl: String) {
        val responseData = SetFavoriteResponseData(
            episodeUrl = episodeUrl,
            isFavorite = fragment.checkIfEpisodeIsFavorite(episodeUrl)
        )

        val response = KotlinXJsonConverter().toJson(responseData)
        println("replying to bridge with ${responseData.isFavorite}")
        replyTo("connect", jsonData = response)
    }
}

@Serializable
data class ConnectMessageData(
    @SerialName("episode_url") val episodeUrl: String,
    @SerialName("episode_duration") val episodeDuration: String
)

@Serializable
data class ToggleMessageData(
    @SerialName("episode_url") val episodeUrl: String
)

@Serializable
data class SetFavoriteResponseData(
    @SerialName("episode_url") val episodeUrl: String,
    @SerialName("is_favorite") val isFavorite: Boolean
)