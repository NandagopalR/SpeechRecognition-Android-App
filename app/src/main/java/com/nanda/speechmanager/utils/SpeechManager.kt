package com.nanda.speechmanager.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SpeechManager(private val activity: Activity) {

  private var speechRecognizer: SpeechRecognizer? = null

  private val _updatedValue = MutableStateFlow("")
  val updatedValue: StateFlow<String> = _updatedValue.asStateFlow()

  private val _isListening = MutableStateFlow(false)
  val isListening: StateFlow<Boolean> = _isListening.asStateFlow()

  fun startListening() {
    if (speechRecognizer == null) {
      speechRecognizer = SpeechRecognizer.createSpeechRecognizer(activity)
      speechRecognizer?.setRecognitionListener(object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) {
          _isListening.value = true
        }

        override fun onBeginningOfSpeech() {}

        override fun onRmsChanged(rmsdB: Float) {}

        override fun onBufferReceived(buffer: ByteArray?) {}

        override fun onEndOfSpeech() {
          _isListening.value = false
        }

        override fun onError(error: Int) {
          _isListening.value = false
        }

        override fun onResults(results: Bundle?) {
          val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
          _updatedValue.value = matches?.firstOrNull() ?: ""
        }

        override fun onPartialResults(partialResults: Bundle?) {
          val matches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
          if (!matches.isNullOrEmpty()) {
            _updatedValue.value = matches[0]
          }
        }

        override fun onEvent(
          eventType: Int,
          params: Bundle?,
        ) {
        }
      })
    }

    val intent = getRecognizerIntent()
    speechRecognizer?.startListening(intent)
  }

  private fun getRecognizerIntent() = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
    putExtra(
      RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    )
    putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
    putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
  }

  fun stopListening() {
    _isListening.value = false
    speechRecognizer?.stopListening()
  }

  fun destroy() {
    speechRecognizer?.destroy()
    speechRecognizer = null
  }
}