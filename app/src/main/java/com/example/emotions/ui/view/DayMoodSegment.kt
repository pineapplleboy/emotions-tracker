package com.example.emotions.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.example.emotions.R
import com.example.emotions.databinding.DayMoodSegmentBinding

class DayMoodSegment @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: DayMoodSegmentBinding =
        DayMoodSegmentBinding.inflate(LayoutInflater.from(context), this, true)

    private var greenPercent: Float = 0.0f
    private var bluePercent: Float = 0.0f
    private var redPercent: Float = 0.0f
    private var yellowPercent: Float = 0.0f

    private var period: String = ""
    private var amount: Int = 0

    init {
        context.withStyledAttributes(attrs, R.styleable.DayMoodSegment) {
            redPercent = getFloat(R.styleable.DayMoodSegment_redPercent, 0f)
            yellowPercent = getFloat(R.styleable.DayMoodSegment_yellowPercent, 0f)
            greenPercent = getFloat(R.styleable.DayMoodSegment_greenPercent, 0f)
            bluePercent = getFloat(R.styleable.DayMoodSegment_bluePercent, 0f)

            amount = getInt(R.styleable.DayMoodSegment_amount, 0)
            period = getString(R.styleable.DayMoodSegment_period) ?: ""
        }

        updateWeights()
        changeText()
    }

    private fun updateWeights() {

        updateSingleColor(binding.red, binding.redText, redPercent)
        updateSingleColor(binding.green, binding.greenText, greenPercent)
        updateSingleColor(binding.blue, binding.blueText, bluePercent)
        updateSingleColor(binding.yellow, binding.yellowText, yellowPercent)

        if (greenPercent <= 0 && redPercent <= 0 && bluePercent <= 0 && yellowPercent <= 0) {
            binding.gray.visibility = VISIBLE
        } else {
            binding.gray.visibility = INVISIBLE
        }

        requestLayout()
    }

    private fun updateSingleColor(
        view: View,
        textView: TextView,
        percent: Float
    ) {
        if (percent <= 0) {
            view.visibility = GONE
            textView.text = ""
        } else {
            view.visibility = VISIBLE
            view.layoutParams = (view.layoutParams as LayoutParams).apply {
                verticalWeight = percent
            }
            textView.text = "${(percent * 100).toInt()}%"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun changeText() {
        binding.period.text = period
        binding.amount.text = amount.toString()
    }

    fun setMoodValues(red: Float, yellow: Float, green: Float, blue: Float) {
        redPercent = red
        yellowPercent = yellow
        greenPercent = green
        bluePercent = blue
        updateWeights()
    }

    fun setAmount(amount: Int) {
        this.amount = amount
        changeText()
    }
}