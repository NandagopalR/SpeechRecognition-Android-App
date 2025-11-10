# SpeechRecognition-Android-App
Speech Recognition is a simple and lightweight Android application that listens to spoken words and converts them into text in real-time. It uses Android’s built-in SpeechRecognizer API to provide accurate and fast speech-to-text conversion.

# ✨ Features

🎙️ Real-time Speech Recognition – Converts voice input to text instantly.

🔁 Continuous Listening Mode – Automatically restarts listening after detecting speech (optional).

📱 Clean & Minimal UI – Simple interface built using Jetpack Compose (or XML, if applicable).

🔔 Live Updates – Displays recognized text dynamically on screen.

🚫 Error Handling – Gracefully handles silence, cancellation, and network errors.

⚙️ Permission Handling – Requests microphone permission at runtime.

# 🧠 Tech Stack

Language: Kotlin

Architecture: MVVM (ViewModel + StateFlow / LiveData)

UI Framework: Jetpack Compose

Speech Engine: Android SpeechRecognizer API

Dependency Injection: Hilt (if applicable)

Coroutines / Flows: For reactive updates

# 🚀 Getting Started
# Prerequisites

Android Studio Giraffe or later

Minimum SDK: 21 (Lollipop)

Internet and microphone permissions

# Setup

Clone the repository:

git clone https://github.com/yourusername/SpeechRecognition.git


Open in Android Studio.

Build and run on an Android device.

# 🧩 Permissions

Make sure the following permissions are added to your AndroidManifest.xml:

<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.INTERNET" />

# 📸 Screenshots
Listening	Recognized Text
🎤	📝

<p align="center">
  <img alt="old_1" src="https://github.com/user-attachments/assets/a7bcda0e-9473-4fc8-9543-e03bc59dcf57" width="30%">
  <img alt="old_2" src="https://github.com/user-attachments/assets/31013e3f-ed57-4280-b6a8-05daef6c19bf" width="30%">
  <img alt="old_2" src="https://github.com/user-attachments/assets/207b7dc5-595d-48db-a0f3-bbaf7d694285" width="30%">
</p>

# 💬 Example Use Case

Voice input for forms and chat apps

Hands-free commands

Accessibility support for text entry

# 🧾 License

This project is licensed under the Apache 2.0 License – see the LICENSE
 file for details.

# 🙌 Contributing

Contributions are welcome!
If you’d like to report a bug, suggest a feature, or submit a pull request, feel free to open an issue on GitHub.
