package com.nanda.speechmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nanda.speechmanager.viewmodel.SpeechViewModel

@Composable
fun SpeechScreen(
  viewModel: SpeechViewModel,
  onRequestPermission: () -> Unit,
) {
  val recognizedText = viewModel.updatedValue.collectAsState().value
  val isListening = viewModel.isListening.collectAsState().value

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(32.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Text(
      text = recognizedText.ifEmpty { "Say something..." },
      fontSize = 20.sp,
      fontWeight = FontWeight.Medium,
      modifier = Modifier.padding(bottom = 32.dp)
    )

    if (isListening) {
      Text(
        text = "🎙 Listening...",
        color = MaterialTheme.colorScheme.primary,
        fontSize = 16.sp
      )
    }

    Spacer(modifier = Modifier.height(24.dp))

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
      Button(
        onClick = { onRequestPermission() },
        enabled = !isListening
      ) {
        Text("Start Listening")
      }

      OutlinedButton(
        onClick = { viewModel.stopListening() },
        enabled = isListening
      ) {
        Text("Stop")
      }
    }
  }
}