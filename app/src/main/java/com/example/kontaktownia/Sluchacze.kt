package com.example.kontaktownia
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent.kontakt
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent.wczytajkontakty
import java.io.File

fun dodaj(kontakt: kontakt, view: View): Int { //Funkcja ktora wykonuje dodanie do pliku danego kontaktu
    val kontakty = mutableListOf<kontakt>() //definicja listy tymczasowej (buforu z pliku)
    val sciezka = view.context.filesDir.toString()+"/kontakty.txt" //sciezka do pliku
    if (File(sciezka).length() != 0L) {   //sprawdzenie czy plik nie jest pusty
        wczytajkontakty(sciezka).forEach {
            kontakty.add(it)
            println(it)
        }
    }

    if (!kontakty.contains(kontakt)) {  //sprawdzenie czy nie ma juz takiego kontaktu
        if(kontakty.add(kontakt)) {  //dodanie kontaktu do bufora
            val plik = File(sciezka) //otwarcie pliku
            val wyjscie = kontakty.joinToString(separator = "\n") { "${it.imie},${it.nazwisko},${it.telefon}" } //konwersja bufora do stringa
            plik.writeText(wyjscie) //zapis do pliku
            return 1 //zwrocenie informacji o pomyslnym dodaniu
        }
        else {
            return 0 //zwrocenie informacji o niepowodzeniu dodania
        }
    }

    else {
        return 2 //zwrocenie informacji o niepowodzeniu dodania z powodu duplikatu
    }
}
fun sluchacz(imie: EditText, nazwisko:EditText, telefon: EditText, knefel: Button): View.OnClickListener { //Sluchacz na przycisk
        return View.OnClickListener {
            val dane = zbierzdane(imie,nazwisko,telefon) //Pobranie danych
            if (dane != null) { //Sprawdzenie czy dane sa poprawne
                //Wszystkie Toasty sa tylko do testow
                var text = "Dotarlem do zbierania danych Dane To: ${dane.imie} ${dane.nazwisko} ${dane.telefon}"
                var toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                toast.show()
                if (dodaj(dane,knefel) == 1) {
                    text = "Dodalem do listy kontaktow ${knefel.context.filesDir}"
                    toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                    toast.show()
                }
                if (dodaj(dane,knefel) == 2) {
                    text = "Nie Dodalem do pliku poniewaz juz istnieje ten kontakt"
                    toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                    toast.show()
                }
                else {
                    text = "Nie Dodalem do pliku"
                    toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                    toast.show()
                }

            }
            else {
                val text = "Wprowadz poprawnie Dane"
                val toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                toast.show()
            }

        }
    }

fun sprawdz(imie: String, nazwisko: String, telefon:String): Boolean { //Funkcja do sprawdzania danych
    if(imie.isEmpty() || nazwisko.isEmpty() || telefon.isEmpty()) { //Sprawdzenie czy dane sa puste

        return false
    }
    if (!telefon.matches(Regex("\\d+"))) { //Sprawdzenie czy numer telefonu jest poprawny
        return false
    }
    else {  //Jezeli dane sa poprawne
        return true
    }
}
fun zbierzdane(imie: EditText, nazwisko: EditText, telefon: EditText): kontakt? { //Funkcja do zbierania danych
    val imied = imie.text.toString() //Pobranie danych imienia
    val nazwiskod = nazwisko.text.toString() //Pobranie danych nazwiska
    val telefond = telefon.text.toString() //Pobranie danych telefonu
    if (!sprawdz(imied,nazwiskod,telefond)) { //Sprawdzenie czy dane sa poprawne
        return null
    }
    return kontakt(imied, nazwiskod, telefond) //Zwracanie danych
}
@SuppressLint("SetTextI18n")
fun cyfra(button: List<Button>, text: TextView) { //Sluchacz na cyfry wybierania
    button.forEach{
        it.setOnClickListener {
            val to = it as Button
            val tekst = to.text.toString()
            text.setText(text.text.toString()+tekst)
        }
    }
}
fun dzwon(button: ImageButton, text: TextView) { //Funkcja do zadzwonienia na numer
    button.setOnClickListener {

        val numer = text.text.toString()
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:${numer}")
        startActivity(button.context,intent,null)

    }
}
