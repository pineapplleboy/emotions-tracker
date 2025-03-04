package com.example.emotions.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.emotions.R
import com.example.emotions.databinding.ActivityMainBinding
import com.example.emotions.domain.model.EmotionColor
import com.example.emotions.domain.model.SavedEmotion
import com.example.emotions.ui.fragment.JournalFragment
import com.example.emotions.ui.fragment.SettingsFragment
import com.example.emotions.ui.fragment.StatsFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private lateinit var onTimeSaved: (time: String) -> Unit

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)

            insets
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.item_journal -> {
                    loadFragment(
                        JournalFragment.newInstance(
                            listOf(
                                SavedEmotion(
                                    "1",
                                    "Счастье",
                                    "вчера, 20:30",
                                    EmotionColor.YELLOW,
                                    R.drawable.ic_happinness
                                ),
                                SavedEmotion(
                                    "2",
                                    "Грусть 2",
                                    "вчера, 20:30",
                                    EmotionColor.BLUE,
                                    R.drawable.ic_fatigue
                                ),
                                SavedEmotion(
                                    "3",
                                    "Злоба",
                                    "вчера, 20:30",
                                    EmotionColor.RED,
                                    R.drawable.ic_rage
                                ),
                                SavedEmotion(
                                    "4",
                                    "Спокойствие",
                                    "вчера, 20:30",
                                    EmotionColor.GREEN,
                                    R.drawable.ic_calm
                                )
                            ), null, null
                        )
                    )
                    true
                }

                R.id.item_stats -> {
                    loadFragment(StatsFragment())
                    true
                }

                R.id.item_settings -> {
                    loadFragment(SettingsFragment())
                    true
                }

                else -> false
            }
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.addNotificationPanel).apply {
            isHideable = true
            state = BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        binding.hours.setOnClickListener {
            TimePickerBase.showTimePicker("Напоминание", supportFragmentManager) {
                binding.hours.text = it.hour.toString()
                binding.minutes.text = it.minute.toString()
            }
        }

        binding.minutes.setOnClickListener {
            TimePickerBase.showTimePicker("Напоминание", supportFragmentManager) {
                binding.hours.text = it.hour.toString()
                binding.minutes.text = it.minute.toString()
            }
        }

        binding.saveButton.setOnClickListener {
            onTimeSaved(getTime())
            hideAddNotificationPanel()
        }

        if (savedInstanceState == null) {
            loadFragment(
                JournalFragment.newInstance(
                    listOf(
                        SavedEmotion(
                            "1",
                            "Счастье",
                            "вчера, 20:30",
                            EmotionColor.YELLOW,
                            R.drawable.ic_happinness
                        ),
                        SavedEmotion(
                            "2",
                            "Грусть 2",
                            "вчера, 20:30",
                            EmotionColor.BLUE,
                            R.drawable.ic_fatigue
                        ),
                        SavedEmotion(
                            "3",
                            "Злоба",
                            "вчера, 20:30",
                            EmotionColor.RED,
                            R.drawable.ic_rage
                        ),
                        SavedEmotion(
                            "4",
                            "Спокойствие",
                            "вчера, 20:30",
                            EmotionColor.GREEN,
                            R.drawable.ic_calm
                        )
                    ), null, null
                )
            )
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun showAddNotificationPanel(onTimeSaved: (String) -> Unit) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        this.onTimeSaved = onTimeSaved
    }

    private fun hideAddNotificationPanel() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun getTime(): String {
        return "${binding.hours.text}:${binding.minutes.text}"
    }
}

object TimePickerBase {

    fun showTimePicker(
        title: String,
        fragmentManager: FragmentManager,
        time: Time? = null,
        showSystemTimeFormat: Boolean = false,
        callback: (Time) -> Unit
    ) {

        val clockFormat = TimeFormat.CLOCK_24H

        val builder =
            MaterialTimePicker.Builder()
                .setTheme(R.style.BaseTheme_TimePicker)
                .setTimeFormat(if (showSystemTimeFormat) clockFormat else TimeFormat.CLOCK_12H)
                .setTitleText(title)

        time?.let {
            builder.setHour(it.hour)
            builder.setMinute(it.minute)
        }

        val picker = builder.build()

        picker.addOnPositiveButtonClickListener {
            callback.invoke(Time(picker.minute, picker.hour))
        }

        picker.addOnNegativeButtonClickListener {
            picker.dismissAllowingStateLoss()
        }

        picker.show(fragmentManager, "TimePickerBase")
    }

}

data class Time(
    val minute: Int,
    val hour: Int,
) {
    override fun toString(): String {
        return "$hour:$minute"
    }
}