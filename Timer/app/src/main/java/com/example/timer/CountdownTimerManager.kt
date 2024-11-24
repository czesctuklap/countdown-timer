package com.example.timer

import android.os.CountDownTimer
import android.content.Context
import android.media.RingtoneManager
import android.widget.Toast

// Definicja klasy CountdownTimerManager do zarządzania odliczaniem czasu
class CountdownTimerManager(
    private val context: Context, // Określenie kontekstu aplikacji
    private val updateUI: (String) -> Unit, // Określenie funkcji do aktualizacji UI z pozostałym czasem
    private val onFinish: () -> Unit // Określenie funkcji wywoływanej po zakończeniu odliczania
) {
    private var timer: CountDownTimer? = null // Deklaracja obiektu odliczającego czas
    private var timeLeftInMillis: Long = 0L // Określenie pozostałego czasu w milisekundach
    private var isPaused: Boolean = false // Określenie, czy odliczanie jest wstrzymane

    // Funkcja do rozpoczęcia odliczania od podanego czasu
    fun start(durationInMillis: Long) {
        timeLeftInMillis = durationInMillis // Ustawienie początkowego czasu
        timer = createTimer(timeLeftInMillis) // Utworzenie nowego timera
        timer?.start() // Rozpoczęcie odliczania
    }

    // Funkcja do pauzowania lub wznawiania odliczania
    fun pause() {
        if (isPaused) { // Jeśli odliczanie jest wstrzymane, wznowić odliczanie
            timer = createTimer(timeLeftInMillis) // Utworzenie nowego timera z pozostałym czasem
            timer?.start() // Rozpoczęcie odliczania
            isPaused = false // Zmiana stanu na "nie wstrzymane"
        } else { // Jeśli odliczanie jest w toku, pauzować je
            timer?.cancel() // Anulowanie bieżącego timera
            isPaused = true // Zmiana stanu na "wstrzymane"
        }
    }

    // Funkcja do zatrzymania odliczania i ustawienia pozostałego czasu na 0
    fun stop() {
        timer?.cancel() // Anulowanie bieżącego timera
        timeLeftInMillis = 0L // Ustawienie pozostałego czasu na 0
        onFinish() // Wywołanie funkcji zakończenia
    }

    // Funkcja do dodawania czasu do bieżącego odliczania
    fun addTime(additionalMillis: Long) {
        timer?.cancel() // Anulowanie bieżącego timera
        timeLeftInMillis += additionalMillis // Dodanie czasu
        start(timeLeftInMillis) // Rozpoczęcie odliczania od nowego czasu
    }

    // Funkcja do zmniejszania czasu odliczania
    fun reduceTime(reduceMillis: Long) {
        timer?.cancel() // Anulowanie bieżącego timera
        timeLeftInMillis = (timeLeftInMillis - reduceMillis).coerceAtLeast(0L) // Zmniejszenie czasu, zapewniając, że nie będzie on mniejszy od 0
        start(timeLeftInMillis) // Rozpoczęcie odliczania od nowego czasu
    }

    // Funkcja do tworzenia nowego timera do odliczania
    private fun createTimer(durationInMillis: Long): CountDownTimer {
        return object : CountDownTimer(durationInMillis, 1000L) { // Tworzenie timera na czas w milisekundach, z interwałem 1 sekundy
            // Funkcja wywoływana co sekundę, aby zaktualizować UI
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished // Aktualizacja pozostałego czasu
                val minutes = (millisUntilFinished / 1000) / 60 // Obliczanie minut
                val seconds = (millisUntilFinished / 1000) % 60 // Obliczanie sekund
                updateUI(String.format("%02d : %02d", minutes, seconds)) // Aktualizacja UI z nowym czasem
            }

            // Funkcja wywoływana po zakończeniu odliczania
            override fun onFinish() {
                updateUI("00 : 00") // Ustawienie UI na "00 : 00"
                Toast.makeText(context, "Time's up!", Toast.LENGTH_SHORT).show() // Wyświetlenie komunikatu o końcu czasu
                val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM) // Pobranie domyślnego dźwięku alarmu
                val ringtone = RingtoneManager.getRingtone(context, notification) // Przygotowanie dźwięku alarmu
                ringtone.play() // Odtworzenie dźwięku alarmu
            }
        }
    }

    // Funkcja do sprawdzania, czy odliczanie jest wstrzymane
    fun getIsPaused() : Boolean {
        return this.isPaused // Zwrócenie stanu pauzy
    }
}
