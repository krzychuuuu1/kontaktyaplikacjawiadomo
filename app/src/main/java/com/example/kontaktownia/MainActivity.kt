package com.example.kontaktownia

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kontaktownia.databinding.ActivityMainBinding
import com.example.kontaktownia.ui.AdapterWyswietlaniaListy
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Klasa glowna
 *
 * @constructor Utworzenie glownej klasy
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO https://developer.android.com/training/permissions/requesting




        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ukrycie domyslnego paska akcji
        supportActionBar?.hide()
        //definicja zmiennych wlasnego paska akcji
        val ImageButton = binding.imageButton3
        val MaterialToolbar = binding.materialToolbar
        val textView: EditText= binding.wyszukiwanie
        szukanie(ImageButton, textView)
        val powrotV: ImageButton = binding.back
        //ustawienie sluchacza przyciska powrotu
        powrotV.setOnClickListener {
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.action_podgladKontaktu_to_navigation_kontakty)
        }
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        //definicja menu dolnego
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_wybieranie, R.id.navigation_kontakty, R.id.navigation_dodajkontakt
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        //pokazywanie i ukrywanie paska akcji w zaleznosci od fragmentu
        navController.addOnDestinationChangedListener{
                _, destination, _ -> println(destination.label)
            if(destination.label == "Lista") {
                ImageButton.visibility = View.VISIBLE
                MaterialToolbar.visibility = View.VISIBLE
                powrotV.visibility = View.GONE
            }
            else if(destination.label == "podgladKontaktu") {
                powrotV.visibility = View.VISIBLE
                MaterialToolbar.visibility = View.VISIBLE
            }
            else if(destination.label == "Edycja") {
                powrotV.visibility = View.VISIBLE
                MaterialToolbar.visibility = View.VISIBLE
            }
            else {
                ImageButton.visibility = View.GONE
                MaterialToolbar.visibility = View.GONE
                textView.visibility = View.GONE
                powrotV.visibility = View.GONE
            }
        }


        navView.setupWithNavController(navController)

    }
}