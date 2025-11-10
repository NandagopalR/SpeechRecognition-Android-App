package com.nanda.speechmanager.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.nanda.speechmanager.utils.SpeechManager
import kotlinx.coroutines.flow.StateFlow

class SpeechViewModel(activity: Activity) : ViewModel() {

  private val speechManager = SpeechManager(activity)

  val updatedValue: StateFlow<String> = speechManager.updatedValue
  val isListening: StateFlow<Boolean> = speechManager.isListening

  fun startListening() = speechManager.startListening()
  fun stopListening() = speechManager.stopListening()

  override fun onCleared() {
    super.onCleared()
    speechManager.destroy()
  }
}