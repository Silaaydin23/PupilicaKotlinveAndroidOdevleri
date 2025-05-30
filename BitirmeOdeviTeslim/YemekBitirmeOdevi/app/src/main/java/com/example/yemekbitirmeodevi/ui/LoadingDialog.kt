package com.example.yemekbitirmeodevi.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.airbnb.lottie.LottieAnimationView
import com.example.yemekbitirmeodevi.R

class LoadingDialog(context: Context) : Dialog(context) {

    private lateinit var animationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.loading_layout)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)

        animationView = findViewById(R.id.loadingAnimation)
        setupAnimation()
    }

    private fun setupAnimation() {
        animationView.apply {
            setAnimation(R.raw.loading_animation)
            playAnimation()
        }
    }

    companion object {
        private var instance: LoadingDialog? = null

        fun show(context: Context) {
            if (instance?.isShowing == true) {
                return
            }
            instance = LoadingDialog(context)
            instance?.show()
        }

        fun dismiss() {
            instance?.dismiss()
            instance = null
        }
    }
} 