package com.example.emotions.ui.activity

import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emotions.databinding.ActivityLoginBinding
import kotlin.math.cos
import kotlin.math.sin

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.content) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSizeAndMargin(binding.redCircle)
        setSizeAndMargin(binding.blueCircle)
        setSizeAndMargin(binding.greenCircle)
        setSizeAndMargin(binding.yellowCircle)

        binding.main.post {
            val parent = binding.main
            val centerX = parent.width / 2f
            val centerY = parent.height / 2f
            val radius = parent.width / 2f

            animateCircle(binding.blueCircle, centerX, centerY, radius, initialAngle = 0f)
            animateCircle(binding.redCircle, centerX, centerY, radius, initialAngle = 90f)
            animateCircle(binding.greenCircle, centerX, centerY, radius, initialAngle = 180f)
            animateCircle(binding.yellowCircle, centerX, centerY, radius, initialAngle = 270f)
        }

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }

    private fun setSizeAndMargin(view: View) {
        view.post {
            val parent = view.parent as? ConstraintLayout ?: return@post

            val screenWidth = parent.width
            val screenHeight = parent.height

            val layoutParams = view.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.width = (screenWidth * 3f).toInt()
            layoutParams.height = (screenHeight * 3f).toInt()
//
//            layoutParams.width = (screenHeight * 0.5f).toInt()
//            layoutParams.height = (screenHeight * 0.5f).toInt()

            layoutParams.topMargin = screenHeight * (-1)
            layoutParams.bottomMargin = screenHeight * (-1)
            layoutParams.marginEnd = screenWidth * (-1)
            layoutParams.marginStart = screenWidth * (-1)

            view.layoutParams = layoutParams
        }
    }

    private fun animateCircle(
        view: View,
        centerX: Float,
        centerY: Float,
        radius: Float,
        initialAngle: Float = 0f,
        duration: Long = 10000L
    ) {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.addUpdateListener { animation ->
            val angle = (animation.animatedValue as Float + initialAngle) % 360f
            val rad = Math.toRadians(angle.toDouble())
            val x = centerX + radius * cos(rad) - view.width / 2
            val y = centerY + radius * sin(rad) - view.height / 2
            view.x = x.toFloat()
            view.y = y.toFloat()
        }
        animator.duration = duration
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.start()
    }
}