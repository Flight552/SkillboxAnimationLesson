package com.example.animation.fragments

import android.os.Bundle
import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.example.animation.ViewBindingFragment
import com.example.animation.databinding.FragmentLottieBinding

class Lottie: ViewBindingFragment<FragmentLottieBinding>(FragmentLottieBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createLottie()
    }

    private fun createLottie() {
        binding.lottie.setAnimation("logo.json")
        binding.lottie.playAnimation()
        binding.lottie.repeatCount = LottieDrawable.INFINITE
    }
}