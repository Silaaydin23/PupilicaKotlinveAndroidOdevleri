package com.example.yemekbitirmeodevi.utils

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.yemekbitirmeodevi.ui.LoadingDialog
import kotlin.random.Random

fun Fragment.showLoadingWithDelay(action: () -> Unit) {
    LoadingDialog.show(requireContext())
    
    // 1-2 saniye arası rastgele bir süre beklet
    val delayMillis = Random.nextLong(1000, 2000)
    
    Handler(Looper.getMainLooper()).postDelayed({
        LoadingDialog.dismiss()
        action.invoke()
    }, delayMillis)
}

fun AppCompatActivity.showLoadingWithDelay(action: () -> Unit) {
    LoadingDialog.show(this)
    
    // 1-2 saniye arası rastgele bir süre beklet
    val delayMillis = Random.nextLong(1000, 2000)
    
    Handler(Looper.getMainLooper()).postDelayed({
        LoadingDialog.dismiss()
        action.invoke()
    }, delayMillis)
} 