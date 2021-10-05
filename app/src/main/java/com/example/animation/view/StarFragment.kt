package com.example.animation.view


import android.os.Bundle
import android.view.View
import com.example.animation.ViewBindingFragment
import com.example.animation.databinding.FragmentCustomBinding

class StarFragment : ViewBindingFragment<FragmentCustomBinding>(FragmentCustomBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.counter.counter = 3
        binding.counter.addListener(binding.starView::setNumberOfPoint)
    }
}