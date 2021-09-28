package com.example.animation.fragments

import android.os.Bundle
import android.view.View
import com.example.animation.ViewBindingFragment
import com.example.animation.databinding.FragmentAnimationBinding
import com.example.animation.databinding.FragmentMotionBinding

class MotionFragment : ViewBindingFragment<FragmentMotionBinding>(FragmentMotionBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startMotion()
    }

    private fun startMotion() {
        binding.btMotion.setOnClickListener {
            binding.layoutMotion.transitionToEnd()
        }
    }
}