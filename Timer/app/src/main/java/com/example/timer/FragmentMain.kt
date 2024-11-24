package com.example.timer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// Definicja klasy FragmentMain jako fragmentu zarządzającego odliczaniem
class FragmentMain : Fragment() {
    private lateinit var timerManager: CountdownTimerManager // Deklaracja obiektu zarządzającego odliczaniem

    // Nadpisanie metody onCreateView do tworzenia i konfiguracji widoku fragmentu
    override fun onCreateView(
        inflater: LayoutInflater, // Obiekt do tworzenia widoków z plików XML
        container: ViewGroup?, // Kontener, do którego fragment zostanie dołączony
        savedInstanceState: Bundle? // Dane zapisane w stanie fragmentu
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false) // Tworzenie widoku fragmentu z layoutu fragment_main

        // Pobieranie referencji do elementów interfejsu
        val countdownTextView: TextView = view.findViewById(R.id.countdown)
        val stopButton: Button = view.findViewById(R.id.stop)
        val startButton: Button = view.findViewById(R.id.start)
        val pauseButton: Button = view.findViewById(R.id.pause)
        val firstIncrease: Button = view.findViewById(R.id.increaseFirstDigit)
        val secondIncrease: Button = view.findViewById(R.id.increaseSecondDigit)
        val thirdIncrease: Button = view.findViewById(R.id.increaseThirdDigit)
        val fourthIncrease: Button = view.findViewById(R.id.increaseFourthDigit)
        val firstDecrease: Button = view.findViewById(R.id.decreaseFirstDigit)
        val secondDecrease: Button = view.findViewById(R.id.decreaseSecondDigit)
        val thirdDecrease: Button = view.findViewById(R.id.decreaseThirdDigit)
        val fourthDecrease: Button = view.findViewById(R.id.decreaseFourthDigit)

        val totalSeconds = arguments?.getInt("time_left") ?: 0 // Pobieranie początkowego czasu z argumentów fragmentu
        val initialTimeInMillis = totalSeconds * 1000L // Przeliczenie czasu na milisekundy

        // Inicjalizacja obiektu CountdownTimerManager do zarządzania odliczaniem
        timerManager = CountdownTimerManager(
            requireContext(), // Kontekst aplikacji
            updateUI = { time -> countdownTextView.text = time }, // Funkcja aktualizacji UI z bieżącym czasem
            onFinish = { countdownTextView.text = "00 : 00" } // Funkcja obsługująca zakończenie odliczania
        )

        // Ustawienie obsługi kliknięcia przycisku "Start"
        startButton.setOnClickListener { timerManager.start(initialTimeInMillis) }

        // Ustawienie obsługi kliknięcia przycisku "Pause"
        pauseButton.setOnClickListener {
            timerManager.pause() // Pauzowanie lub wznawianie odliczania
            pauseButton.text = if (timerManager.getIsPaused()) "Resume" else "Pause" // Aktualizacja tekstu przycisku
        }

        // Ustawienie obsługi kliknięcia przycisku "Stop"
        stopButton.setOnClickListener { timerManager.stop() } // Zatrzymanie odliczania

        // Ustawienie obsługi przycisków do zwiększania czasu
        firstIncrease.setOnClickListener { timerManager.addTime(600000L) }
        secondIncrease.setOnClickListener { timerManager.addTime(60000L) }
        thirdIncrease.setOnClickListener { timerManager.addTime(10000L) }
        fourthIncrease.setOnClickListener { timerManager.addTime(1000L) }

        // Ustawienie obsługi przycisków do zmniejszania czasu
        firstDecrease.setOnClickListener { timerManager.reduceTime(600000L) }
        secondDecrease.setOnClickListener { timerManager.reduceTime(60000L) }
        thirdDecrease.setOnClickListener { timerManager.reduceTime(10000L) }
        fourthDecrease.setOnClickListener { timerManager.reduceTime(1000L) }

        return view // Zwrócenie utworzonego widoku
    }
}

