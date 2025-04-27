package com.example.kotlin2.nesne.tabanli.programlama

fun main() {

    val f = Odev_2Fonksiyonlar()

    val fahrenhiet = f.donustur(23.7)
    println("${fahrenhiet} fahrenhiet")

    val cevre = f.cevreHesapla(10,20)

   val faktoriyel = f.faktoriyelHesapla(7)
    println("${faktoriyel} faktoriyeldir")

    val sayi = f.say("Ataturk")

   val icAci = f.icAcilarToplami(6)
    println("ic acilar toplami = ${icAci}")

    val maas = f.maasHesapla(28)
    println("Maas = ${maas}")

    val ucret = f.ucretHesapla(60)
    println("Odenecek ucret = ${ucret}")
}