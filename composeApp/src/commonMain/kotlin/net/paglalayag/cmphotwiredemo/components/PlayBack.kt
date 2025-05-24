@file:OptIn(ExperimentalBasicSound::class)
package net.paglalayag.cmphotwiredemo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import app.lexilabs.basic.sound.ExperimentalBasicSound
import cmp_hotwire_demo.composeapp.generated.resources.Res
import cmp_hotwire_demo.composeapp.generated.resources.pause_icon
import cmp_hotwire_demo.composeapp.generated.resources.play_icon
import net.paglalayag.cmphotwiredemo.presentation.TimeAndEmitPlay
import org.jetbrains.compose.resources.vectorResource


@Composable
fun PlayBack(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    duration: Long,
    playingIndex: Int = -1,
    currentIndex: Int = -1,
    audioFile: String,
    updatePlayIndex: (index: Int) -> Unit = {}
) {

    val coroutineScope = rememberCoroutineScope()
    val timeAndEmit = remember {
        TimeAndEmitPlay(coroutineScope)
    }

    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    LaunchedEffect(key1 = playingIndex) {
        println("playingIndex [ $playingIndex ] currentIndex [ $currentIndex ]")
        if(playingIndex != currentIndex) {
            timeAndEmit.pause()
        }
    }

    DisposableEffect(lifecycleOwner) {

        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    println("ON_PAUSE")
                    timeAndEmit.pause()
                }
                Lifecycle.Event.ON_STOP -> {
                    // Ensure audio is stopped when app is completely in background
                    println("ON_STOP")
                    timeAndEmit.pause()
                }
                else -> {
                    println("event ${event.name}")
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            println("onDisposed")
            timeAndEmit.pause()
        }
    }

    LaunchedEffect(duration) {
        println("TIME AND EMIT SET $audioFile")
        if(audioFile.isNotBlank()) {
            timeAndEmit.initAudioController(
                playbackDuration = duration,
                audioFile = audioFile
            )
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(100f),
                color = backgroundColor.copy(alpha = 0.2f)
            )
            .padding(horizontal = 8.dp)
            .padding(top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        IconButton(
            modifier = Modifier
                .background(color = Color.White, RoundedCornerShape(100f)),
            onClick = {
                updatePlayIndex(currentIndex)
                timeAndEmit.playAndPause()
            }
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp),
                imageVector = if (timeAndEmit.state.value == TimeAndEmitPlay.PlayerState.PLAY) vectorResource(resource = Res.drawable.pause_icon ) else vectorResource(resource = Res.drawable.play_icon),
                contentDescription = "Play back button",
                tint = backgroundColor
            )
        }

        LinearProgressIndicator(
            modifier = Modifier.weight(1f),
            progress = {
                (timeAndEmit.currentTime.value / duration.toFloat()).coerceIn(0.0F..1.0F)

            },
            trackColor = Color.LightGray,
            color = backgroundColor.copy(alpha = 0.6f),
            strokeCap = StrokeCap.Square
        )

        Text(
            text = "${formatTime(timeAndEmit.currentTime.value)}/${formatTime(duration)}"
        )
    }
}

fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60

    val minutesStr = if (minutes < 10) "0$minutes" else "$minutes"
    val secondsStr = if (seconds < 10) "0$seconds" else "$seconds"

    return "$minutesStr:$secondsStr"
}