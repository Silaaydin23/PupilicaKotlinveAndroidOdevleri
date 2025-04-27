package com.example.kotlin2.nesne.tabanli.programlama

import androidx.collection.emptyLongSet

class Odev_2Fonksiyonlar {

    fun donustur(celcius:Double) : Double {
        var fahrenhiet = celcius * 1.8 + 32
        return fahrenhiet
    }


    fun cevreHesapla(x:Int,y:Int){
        var cevre = 2 * (x+y)
        println("Cevre = ${cevre}")
    }


    fun faktoriyelHesapla(sayi:Int) : Int{
        var sonuc = 1
        for(i in sayi downTo 1){
            sonuc *= i
        }
        return sonuc
    }


    fun say(kelime:String){
        var sayi = 0
        for(i in kelime.lowercase()){
            if(i == 'a'){
                sayi += 1
            }
        }
        println("${sayi} tane a harfi vardir.")
    }


    fun icAcilarToplami(kenarSayisi:Int) : Int{
        var toplam = (kenarSayisi-2) * 180
        return toplam
    }


    fun maasHesapla(gunSayisi:Int) : Int{
        var toplamSaat = gunSayisi * 8
        var calismaUcret = toplamSaat * 10
        var mesaiUcreti = 0
        if(toplamSaat > 160){
            mesaiUcreti = (toplamSaat - 160) * 20}
        var toplamMaas = calismaUcret + mesaiUcreti
        return toplamMaas
    }


    fun ucretHesapla(kota:Int) : Int{
        var kotaAsimi = 0
        if(kotaAsimi > 50)
            kotaAsimi = kotaAsimi * 4
        var ucret = kotaAsimi + 100
        return ucret
    }
}