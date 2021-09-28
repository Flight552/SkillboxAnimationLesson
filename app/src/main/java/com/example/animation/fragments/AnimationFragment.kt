package com.example.animation.fragments

import android.animation.*
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.core.animation.addListener
import androidx.lifecycle.lifecycleScope
import com.example.animation.R
import com.example.animation.ViewBindingFragment
import com.example.animation.databinding.FragmentAnimationBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AnimationFragment :
    ViewBindingFragment<FragmentAnimationBinding>(FragmentAnimationBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btAnim1.setOnClickListener {
            createFirstAnimation()
        }
        binding.btAnim2.setOnClickListener {
            createSecondAnimation()
        }
        binding.btAnim3.setOnClickListener {
            createThirdAnimation()
        }

        binding.btAnim4.setOnClickListener {
            createFourthAnimation()
        }
        binding.btAnim5.setOnClickListener {
            createFifthAnimation()
        }
    }

    private fun createFifthAnimation() {
        val animator = AnimatorInflater.loadAnimator(requireContext(), R.animator.animation)
            as AnimatorSet
        animator.setTarget(binding.tvHelloWorld)
        animator.start()
    }

    // ValueAnimator - может анимировать почти все, что угодно
    // практически это создание собственного интерполятора
    private fun createFourthAnimation() {
        val text = "Punks not dead!"
        val animator = ValueAnimator.ofFloat(0.0F, 0.1F)
        animator.addUpdateListener { valueAnimator ->
            binding.tvHelloWorld.text = text.subSequence(
                startIndex = 0,
                endIndex = (valueAnimator.animatedFraction * text.length).toInt()
            )
        }
        animator.setDuration(1000L).start()
        backToNormal()
    }

    private fun backToNormal() {
        lifecycleScope.launch {
            delay(1500L)
            binding.tvHelloWorld.text = "Hello World!!!"
        }
    }

    //AnimationSet - создает последовательность сложных анимаций
    private fun createThirdAnimation() {
        val scaleX = ObjectAnimator.ofFloat(binding.tvHelloWorld, "scaleX", 2.0F)
            .setDuration(1000L)
        val scaleY = ObjectAnimator.ofFloat(binding.tvHelloWorld, "scaleY", 2.0F)
            .setDuration(1000L)

        val scaleX2 = ObjectAnimator.ofFloat(binding.tvHelloWorld, "scaleX", 1.0F)
            .setDuration(1000L)
        val scaleY2 = ObjectAnimator.ofFloat(binding.tvHelloWorld, "scaleY", 1.0F)
            .setDuration(1000L)

        AnimatorSet().apply {
            play(scaleX).with(scaleY)
            play(scaleX2).with(scaleY2)
            play(scaleX2).after(scaleX)

            //ИНТЕРПОЛЯЦИЯ - нахожение неизвестных значений по имеющимся известным значениям
            interpolator = BounceInterpolator()
            start()
        }
    }

    // тоже простой аниматор, но с большими возможностями и большим кол-вом кода
    private fun createSecondAnimation() {
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 2.0F)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 2.0F)

        val obj = ObjectAnimator.ofPropertyValuesHolder(binding.tvHelloWorld, scaleX, scaleY)

        obj.addListener(
            object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) = Unit

                override fun onAnimationEnd(animation: Animator?) {
                    val scaleX2 = PropertyValuesHolder.ofFloat("scaleX", 1.0F)
                    val scaleY2 = PropertyValuesHolder.ofFloat("scaleY", 1.0F)
                    ObjectAnimator
                        .ofPropertyValuesHolder(binding.tvHelloWorld, scaleX2, scaleY2)
                        .setDuration(1000L)
                        .start()
                }

                override fun onAnimationCancel(animation: Animator?) = Unit
                override fun onAnimationRepeat(animation: Animator?) = Unit

            }
        )
        obj.setDuration(1000L).start()
    }

    //самый простой аниматор
    private fun createFirstAnimation() {
        binding.tvHelloWorld
            .animate()
            .scaleX(2.0F)
            .scaleY(2.0F)
            .setDuration(1000L)
            .withEndAction {
                binding.tvHelloWorld
                    .animate()
                    .scaleX(1.0F)
                    .scaleY(1.0F)
                    .setDuration(1000L)
                    .start()
            }.start()
    }
}