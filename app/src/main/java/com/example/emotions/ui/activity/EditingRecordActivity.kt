package com.example.emotions.ui.activity

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emotions.R
import com.example.emotions.databinding.ActivityEditingRecordBinding
import com.example.emotions.domain.model.Emotion
import com.example.emotions.domain.model.EmotionColor
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.Date
import java.util.Locale

class EditingRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditingRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEditingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emotionId = intent.getIntExtra("emotionId", 0)

        val emotions = listOf(
            Emotion("Ярость", "Сильное чувство гнева, сопровождающееся агрессией", EmotionColor.RED, R.drawable.ic_rage),
            Emotion("Напряжение", "Ощущение стресса и внутреннего давления", EmotionColor.RED, R.drawable.ic_stress),
            Emotion("Возбуждение", "Состояние повышенной активности и бодрствования", EmotionColor.YELLOW, R.drawable.ic_excitement),
            Emotion("Восторг", "Интенсивное чувство радости и восхищения", EmotionColor.YELLOW, R.drawable.ic_delight),
            Emotion("Зависть", "Желание иметь то, что есть у других, с оттенком недовольства", EmotionColor.RED, R.drawable.ic_envy),
            Emotion("Беспокойство", "Чувство тревоги и волнения о чём-либо", EmotionColor.RED, R.drawable.ic_anxiety),
            Emotion("Уверенность", "Ощущение внутренней силы и веры в свои способности", EmotionColor.YELLOW, R.drawable.ic_confidence),
            Emotion("Счастье", "Общее состояние удовлетворения и радости", EmotionColor.YELLOW, R.drawable.ic_happinness),
            Emotion("Выгорание", "Чувство эмоционального и физического истощения", EmotionColor.BLUE, R.drawable.ic_burnout),
            Emotion("Усталость", "Ощущение, что необходимо отдохнуть", EmotionColor.BLUE, R.drawable.ic_fatigue),
            Emotion("Спокойствие", "Состояние внутреннего равновесия и умиротворённости", EmotionColor.GREEN, R.drawable.ic_calm),
            Emotion("Удовлетворённость", "Чувство довольства достигнутым", EmotionColor.GREEN, R.drawable.ic_satisfaction),
            Emotion("Депрессия", "Состояние подавленности, потери интереса и энергии", EmotionColor.BLUE, R.drawable.ic_depression),
            Emotion("Апатия", "Безразличие к окружающему, отсутствие мотивации", EmotionColor.BLUE, R.drawable.ic_apathy),
            Emotion("Благодарность", "Чувство признательности за что-то хорошее", EmotionColor.GREEN, R.drawable.ic_gratitude),
            Emotion("Защищённость", "Ощущение безопасности и комфорта", EmotionColor.GREEN, R.drawable.ic_security)
        )

        val emotion = emotions[emotionId]

        binding.card.card.setBackgroundResource(getBackgroundDrawable(emotion.color))
        binding.card.timeDate.text = getDateTime()
        binding.card.emotionType.text = emotion.type
        binding.card.emotionType.setTextColor(ContextCompat.getColor(this, getTextColor(emotion.color)))

        val answers = arrayOf(
            mutableListOf(
                "Приём пищи", "Встреча с друзьями", "Тренировка", "Хобби", "Отдых", "Поездка"
            ),
            mutableListOf(
                "Один", "Друзья", "Семья", "Коллеги", "Партнёр", "Питомцы"
            ),
            mutableListOf(
                "Дом", "Работа", "Школа", "Транспорт", "Улица"
            )
        )

        val questionViews = arrayOf(
            binding.firstQuestion, binding.secondQuestion, binding.thirdQuestion
        )

        for(i in 0..2) {
            val chipGroup = questionViews[i].findViewById<ChipGroup>(R.id.chipGroup)
            chipGroup.removeAllViews()

            for (answer in answers[i]) {
                val chip = LayoutInflater.from(chipGroup.context)
                    .inflate(R.layout.answer_chip, chipGroup, false) as Chip

                chip.text = answer
                chipGroup.addView(chip)
            }

            val addChip = LayoutInflater.from(chipGroup.context)
                .inflate(R.layout.answer_chip, chipGroup, false) as Chip

            addChip.text = "+"

            addChip.setOnClickListener {
                val inputField = EditText(this)
                inputField.hint = "Введите свой ответ"
                AlertDialog.Builder(this)
                    .setTitle("Введите новый вариант ответа")
                    .setView(inputField)
                    .setPositiveButton("Добавить") { _, _ ->
                        val newAnswer = inputField.text.toString()
                        if (newAnswer.isNotBlank()) {
                            val newChip = (LayoutInflater.from(chipGroup.context)
                                .inflate(R.layout.answer_chip, chipGroup, false) as Chip).apply {
                                text = newAnswer
                            }
                            chipGroup.addView(newChip)
                            newChip.isChecked = true

                            chipGroup.removeView(addChip)
                            chipGroup.addView(addChip)
                        }
                    }
                    .setNegativeButton("Отмена") {_, _ ->
                        addChip.isChecked = false
                    }
                    .show()
            }

            chipGroup.addView(addChip)
        }
    }

    private fun getBackgroundDrawable(color: EmotionColor): Int {
        return when(color) {
            EmotionColor.GREEN -> R.drawable.gradient_card_green
            EmotionColor.BLUE -> R.drawable.gradient_card_blue
            EmotionColor.YELLOW -> R.drawable.gradient_card_yellow
            EmotionColor.RED -> R.drawable.gradient_card_red
        }
    }

    private fun getTextColor(color: EmotionColor): Int {
        return when(color) {
            EmotionColor.GREEN -> R.color.light_green
            EmotionColor.BLUE -> R.color.sky_blue
            EmotionColor.YELLOW -> R.color.honey_yellow
            EmotionColor.RED -> R.color.scarlet_red
        }
    }

    private fun getDateTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return "сегодня, ${dateFormat.format(Date())}"
    }
}