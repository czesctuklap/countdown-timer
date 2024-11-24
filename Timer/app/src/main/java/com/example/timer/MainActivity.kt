package com.example.timer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

// Określenie klasy MainActivity jako punktu wejściowego aplikacji
class MainActivity : AppCompatActivity() {
    // Nadpisanie metody onCreate do inicjalizacji aktywności
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Wywołanie metody nadrzędnej onCreate w celu inicjalizacji
        enableEdgeToEdge() // Włączenie trybu pełnoekranowego
        setContentView(R.layout.activity_main) // Ustawienie widoku aktywności na layout activity_main

        // Dodanie fragmentu FragmentForm do kontenera o ID nav_container
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_container, FragmentForm()) // Zamiana zawartości kontenera na FragmentForm
            .commit() // Potwierdzenie transakcji fragmentu
    }
}