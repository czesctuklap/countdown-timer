package com.example.timer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import android.app.AlertDialog
import android.widget.EditText

// Definiowanie klasy FragmentForm jako fragmentu interfejsu
class FragmentForm : Fragment() {
    private val duration = 120 // Określenie początkowego czasu trwania w sekundach

    // Nadpisanie metody onCreateView do tworzenia i konfiguracji widoku fragmentu
    override fun onCreateView(
        inflater: LayoutInflater, // Obiekt do tworzenia widoków z plików XML
        container: ViewGroup?, // Kontener, do którego fragment zostanie dołączony
        savedInstanceState: Bundle? // Dane zapisane w stanie fragmentu
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form, container, false) // Tworzenie widoku fragmentu z layoutu fragment_form

        // Pobieranie referencji do elementów interfejsu
        val minutesTextView: TextView = view.findViewById(R.id.form_minutes)
        val secondsTextView: TextView = view.findViewById(R.id.form_seconds)
        val nextBtn: Button = view.findViewById(R.id.next) // Określenie przycisku "Next"

        // Funkcja pomocnicza do wyświetlania dialogu wprowadzania tekstu
        fun showInputDialog(textView: TextView, title: String) {
            val inputField = EditText(requireContext()).apply {
                hint = title // Ustawienie podpowiedzi
                inputType = android.text.InputType.TYPE_CLASS_NUMBER // Określenie typu wejściowego na liczbowy
                filters = arrayOf(android.text.InputFilter.LengthFilter(2)) // Ograniczenie długości wprowadzanych danych do 2 znaków
            }

            AlertDialog.Builder(requireContext()) // Tworzenie okna dialogowego
                .setTitle("Set $title") // Ustawienie tytułu okna
                .setView(inputField) // Dodanie pola wejściowego do okna
                .setPositiveButton("OK") { _, _ -> // Obsługa przycisku "OK"
                    val inputText = inputField.text.toString() // Pobranie wprowadzonego tekstu
                    if (inputText.isNotEmpty()) {
                        textView.text = if (inputText.length == 1) "0$inputText" else inputText // Formatowanie tekstu
                    } else {
                        textView.text = "00" // Ustawienie domyślnej wartości
                    }
                }
                .setNegativeButton("Cancel", null) // Obsługa przycisku "Cancel"
                .show() // Wyświetlenie okna dialogowego
        }

        // Ustawienie obsługi kliknięcia dla TextView minut
        minutesTextView.setOnClickListener {
            showInputDialog(minutesTextView, "Minutes") // Wywołanie dialogu do ustawienia minut
        }

        // Ustawienie obsługi kliknięcia dla TextView sekund
        secondsTextView.setOnClickListener {
            showInputDialog(secondsTextView, "Seconds") // Wywołanie dialogu do ustawienia sekund
        }

        // Ustawienie obsługi kliknięcia przycisku "Next"
        nextBtn.setOnClickListener {
            val minutes = minutesTextView.text.toString().toIntOrNull() ?: 0 // Pobranie liczby minut lub domyślnie 0
            val seconds = secondsTextView.text.toString().toIntOrNull() ?: 0 // Pobranie liczby sekund lub domyślnie 0

            val bundle = Bundle() // Tworzenie obiektu Bundle do przekazania danych

            val totalSeconds = minutes * 60 + seconds // Przeliczenie całkowitej liczby sekund

            val fragment = FragmentMain() // Tworzenie nowego fragmentu FragmentMain
            bundle.putInt("time_left", totalSeconds) // Przekazanie danych do obiektu Bundle
            fragment.arguments = bundle // Ustawienie argumentów fragmentu

            val transaction = fragmentManager?.beginTransaction() // Rozpoczęcie transakcji fragmentu
            transaction?.replace(R.id.nav_container, fragment)?.commit() // Zamiana obecnego fragmentu na nowy i zatwierdzenie transakcji
        }

        return view // Zwrócenie utworzonego widoku
    }
}