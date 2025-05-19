package net.paglalayag.cmphotwiredemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import net.paglalayag.cmphotwiredemo.components.PlayBack
import net.paglalayag.cmphotwiredemo.core.presentation.DarkRed

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            PlayBack(
                backgroundColor = DarkRed,
                duration = 2203000,
                audioFile = "https://paglalayag.net/assets/audio/mark-paglalayag-audio.mp3",
            )
        }
    }
}
