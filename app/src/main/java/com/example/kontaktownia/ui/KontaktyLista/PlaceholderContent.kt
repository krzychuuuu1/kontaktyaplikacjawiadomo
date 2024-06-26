package com.example.kontaktownia.ui.KontaktyLista

import java.io.File

/**
 *
 * Obiekt odpowiedzialny za wczytywanie kontaktow z pliku
 */
object PlaceholderContent {

    data class kontakt(val imie: String, val nazwisko: String, val telefon: String, val email: String, val miasto: String, val ulica: String, val praca: Boolean)

    fun wczytajkontakty(sciezka: String): MutableList<kontakt> { //Funkcja wczytuje kontakty z pliku
        val plik = File(sciezka) //Otwieranie pliku
        val kontakty = mutableListOf<kontakt>() //Stworzenie listy kontaktow
        with(plik) {
            if (!exists()) { //Sprawdzenie czy plik istnieje
                if (!createNewFile()) { //Tworzenie pliku gdy go nie ma
                    error("Nie udalo sie stworzyc pliku")
                }
            }
        }
        if(plik.canRead() && plik.canWrite()) { //Sprawdzenie czy plik jest odczytywalny i zapisywalny
            println("Plik jest odczytywalny i zapisywalny")
        }
        else { //Nie da sie odczytac pliku
            error("Nie da sie odczytac pliku")
        }
        if (plik.length() == 0L) { //Sprawdzenie czy plik jest pusty
            println("Plik jest pusty")

        }

        else {
            if(plik.length() > 0L) { //Sprawdzenie czy plik nie jest pusty
                println("jestem tu")

                plik.forEachLine { //Wczytywanie linijek z pliku
                    val czesci = it.split(",", limit = 7)
                    val imie = czesci.getOrNull(0) ?: ""
                    val nazwisko = czesci.getOrNull(1) ?: ""
                    val telefon = czesci.getOrNull(2) ?: ""
                    val email = czesci.getOrNull(3) ?: ""
                    val miasto = czesci.getOrNull(4) ?: ""
                    val ulica = czesci.getOrNull(5) ?: ""
                    val praca = czesci.getOrNull(6)?.toBoolean() ?: false
                    kontakty.add(kontakt(imie, nazwisko, telefon, email, miasto, ulica, praca)) //Dodawanie kontaktow do listy
                    println("${imie}, ${nazwisko}, ${telefon}, $email, $miasto, $ulica, $praca") //Wypisanie kontaktow do konsoli
                }

            }



        }

        val set:MutableSet<kontakt> = kontakty.toMutableSet().toSortedSet(compareBy { it.imie }) //Konwersja listy kontaktow na set (Dzieki temu usuwamy duplikaty)
        return set.toMutableList() //Konwersja seta na liste
    }












}