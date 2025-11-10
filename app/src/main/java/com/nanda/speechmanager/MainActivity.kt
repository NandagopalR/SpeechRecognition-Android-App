package com.nanda.speechmanager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.core.content.ContextCompat
import com.nanda.speechmanager.viewmodel.SpeechViewModel

class MainActivity : ComponentActivity() {

  private lateinit var speechViewModel: SpeechViewModel

  private val requestPermissionLauncher =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
      if (isGranted) speechViewModel.startListening()
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    speechViewModel = SpeechViewModel(this)

    setContent {
      MaterialTheme {
        SpeechScreen(
          viewModel = speechViewModel,
          onRequestPermission = { checkPermissionAndStart() }
        )
      }
    }
  }

  private fun checkPermissionAndStart() {
    when {
      ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        == PackageManager.PERMISSION_GRANTED -> speechViewModel.startListening()

      else -> requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }
  }
}