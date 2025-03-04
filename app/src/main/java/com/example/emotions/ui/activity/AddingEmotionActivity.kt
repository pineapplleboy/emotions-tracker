package com.example.emotions.ui.activity

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emotions.R
import com.example.emotions.databinding.ActivityAddingEmotionBinding
import com.example.emotions.domain.model.Emotion
import com.example.emotions.domain.model.EmotionColor
import com.example.emotions.presentation.dpToPx
import com.otaliastudios.zoom.ZoomLayout
import kotlin.math.max
import kotlin.math.sqrt


private const val NORMAL_SIZE_DP = 112
private const val EXPANDED_SIZE_DP = 152

private const val NORMAL_TEXT_SIZE = 10f
private const val EXPANDED_TEXT_SIZE = 16f

class AddingEmotionActivity : AppCompatActivity() {
    private var currentScaledView: View? = null
    private var currentScaledViewId: Int = 0
    private lateinit var gridLayout: GridLayout

    private lateinit var binding: ActivityAddingEmotionBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddingEmotionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
        }

        with(binding) {
            val emotions = listOf(
                Emotion(
                    "Ярость",
                    "Сильное чувство гнева, сопровождающееся агрессией",
                    EmotionColor.RED,
                    R.drawable.ic_rage
                ),
                Emotion(
                    "Напряжение",
                    "Ощущение стресса и внутреннего давления",
                    EmotionColor.RED,
                    R.drawable.ic_stress
                ),
                Emotion(
                    "Возбуждение",
                    "Состояние повышенной активности и бодрствования",
                    EmotionColor.YELLOW,
                    R.drawable.ic_excitement
                ),
                Emotion(
                    "Восторг",
                    "Интенсивное чувство радости и восхищения",
                    EmotionColor.YELLOW,
                    R.drawable.ic_delight
                ),
                Emotion(
                    "Зависть",
                    "Желание иметь то, что есть у других, с оттенком недовольства",
                    EmotionColor.RED,
                    R.drawable.ic_envy
                ),
                Emotion(
                    "Беспокойство",
                    "Чувство тревоги и волнения о чём-либо",
                    EmotionColor.RED,
                    R.drawable.ic_anxiety
                ),
                Emotion(
                    "Уверенность",
                    "Ощущение внутренней силы и веры в свои способности",
                    EmotionColor.YELLOW,
                    R.drawable.ic_confidence
                ),
                Emotion(
                    "Счастье",
                    "Общее состояние удовлетворения и радости",
                    EmotionColor.YELLOW,
                    R.drawable.ic_happinness
                ),
                Emotion(
                    "Выгорание",
                    "Чувство эмоционального и физического истощения",
                    EmotionColor.BLUE,
                    R.drawable.ic_burnout
                ),
                Emotion(
                    "Усталость",
                    "Ощущение, что необходимо отдохнуть",
                    EmotionColor.BLUE,
                    R.drawable.ic_fatigue
                ),
                Emotion(
                    "Спокойствие",
                    "Состояние внутреннего равновесия и умиротворённости",
                    EmotionColor.GREEN,
                    R.drawable.ic_calm
                ),
                Emotion(
                    "Удовлетворённость",
                    "Чувство довольства достигнутым",
                    EmotionColor.GREEN,
                    R.drawable.ic_satisfaction
                ),
                Emotion(
                    "Депрессия",
                    "Состояние подавленности, потери интереса и энергии",
                    EmotionColor.BLUE,
                    R.drawable.ic_depression
                ),
                Emotion(
                    "Апатия",
                    "Безразличие к окружающему, отсутствие мотивации",
                    EmotionColor.BLUE,
                    R.drawable.ic_apathy
                ),
                Emotion(
                    "Благодарность",
                    "Чувство признательности за что-то хорошее",
                    EmotionColor.GREEN,
                    R.drawable.ic_gratitude
                ),
                Emotion(
                    "Защищённость",
                    "Ощущение безопасности и комфорта",
                    EmotionColor.GREEN,
                    R.drawable.ic_security
                )
            )

            gridLayout = emotionsGrid
            generateEmotionsGrid(this.root.context, emotions, gridLayout)

            zoomLayout.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_MOVE) {
                    updateClosestCircle(emotions, zoomLayout)
                }
                false
            }

            imageButton.setOnClickListener {
                val intent = Intent(this@AddingEmotionActivity, EditingRecordActivity::class.java)
                intent.putExtra("emotionId", currentScaledViewId)
                startActivity(
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(this@AddingEmotionActivity)
                        .toBundle()
                )
            }

            goBackButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun updateClosestCircle(emotions: List<Emotion>, zoomLayout: ZoomLayout) {

        val displayMetrics = Resources.getSystem().displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        val centerX = zoomLayout.panX + (gridLayout.width - screenWidth) / 2
        val centerY = zoomLayout.panY + (gridLayout.height - screenHeight) / 2

        val columnCount = gridLayout.columnCount
        val rowCount = gridLayout.rowCount
        val cellWidth = gridLayout.width / 3.0 / columnCount
        val cellHeight = gridLayout.height / 3.0 / rowCount

        val colIndex = (columnCount / 2 - centerX / cellWidth).toInt().coerceIn(0, columnCount - 1)
        val rowIndex = (rowCount / 2 - centerY / cellHeight).toInt().coerceIn(0, rowCount - 1)

        val closestIndex = rowIndex * columnCount + colIndex

        if (closestIndex in 0 until gridLayout.childCount) {

            val closestView = gridLayout.getChildAt(closestIndex)

            if (closestView != currentScaledView) {
                currentScaledView?.let { scaleDownCircle(it) }
                scaleUpCircle(closestView)

                changeButtonInfo(emotions[closestIndex])

                currentScaledView = closestView
                currentScaledViewId = closestIndex
            }
        }
    }

    private fun scaleUpCircle(view: View) {
        val circle = view.findViewById<View>(R.id.emotionCircle)
        val currentSize = circle.width.toFloat()
        val targetSize = EXPANDED_SIZE_DP.dpToPx()

        if (currentSize >= targetSize) return

        animateCircle(circle, currentSize, targetSize)

        val text = view.findViewById<TextView>(R.id.emotionText)
        animateFont(text, text.textSize, spToPx(EXPANDED_TEXT_SIZE))
    }

    private fun scaleDownCircle(view: View) {
        val circle = view.findViewById<View>(R.id.emotionCircle)
        val currentSize = circle.width.toFloat()
        val targetSize = NORMAL_SIZE_DP.dpToPx()

        if (currentSize <= targetSize) return

        animateCircle(circle, currentSize, targetSize)

        val text = view.findViewById<TextView>(R.id.emotionText)
        animateFont(text, text.textSize, spToPx(NORMAL_TEXT_SIZE))
    }

    private fun changeButtonInfo(emotion: Emotion) = with(binding) {

        infoPanel.visibility = VISIBLE
        defaultInfoPanel.visibility = GONE

        emotionName.text = emotion.type
        emotionName.setTextColor(getEmotionColor(emotion.color))
        emotionDescription.text = emotion.description
    }

    private fun animateCircle(circle: View, fromSize: Float, toSize: Int) {
        val animator = ValueAnimator.ofFloat(fromSize, toSize.toFloat()).apply {
            duration = 300
            addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Float
                val layoutParams = circle.layoutParams as ViewGroup.LayoutParams
                layoutParams.width = animatedValue.toInt()
                layoutParams.height = animatedValue.toInt()
                circle.layoutParams = layoutParams
            }
        }
        animator.start()
    }

    private fun animateFont(text: TextView, fromSize: Float, toSize: Float) {
        val animator = ValueAnimator.ofFloat(fromSize, toSize).apply {
            duration = 300
            addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Float
                text.setTextSize(TypedValue.COMPLEX_UNIT_PX, animatedValue)
            }
        }
        animator.start()
    }

    private fun generateEmotionsGrid(
        context: Context,
        emotions: List<Emotion>,
        gridLayout: GridLayout
    ) {
        val size = emotions.size
        val rootSize = sqrt(size.toDouble()).toInt()
        val gridSize = max(rootSize, 2)

        val gridWidth = gridSize * 112.dpToPx() + ((gridSize - 1) * 8.dpToPx())

        gridLayout.setPadding(gridWidth, gridWidth, gridWidth, gridWidth)

        gridLayout.columnCount = gridSize
        gridLayout.rowCount = gridSize

        gridLayout.removeAllViews()

        emotions.forEach { emotion ->
            val emotionView = createEmotionView(context, emotion)
            gridLayout.addView(emotionView)
        }
    }

    private fun createEmotionView(context: Context, emotion: Emotion): View {
        val view = LayoutInflater.from(context).inflate(R.layout.emotion_circle, null)

        val textView = view.findViewById<TextView>(R.id.emotionText)
        textView.text = emotion.type

        val circleView = view.findViewById<View>(R.id.emotionCircle)
        val drawable = circleView.background as GradientDrawable
        drawable.setColor(getEmotionColor(emotion.color))

        val params = GridLayout.LayoutParams().apply {
            setGravity(android.view.Gravity.CENTER)
        }
        view.layoutParams = params

        return view
    }

    private fun getEmotionColor(color: EmotionColor): Int {
        return when (color) {
            EmotionColor.BLUE -> getColor(R.color.sky_blue)
            EmotionColor.RED -> getColor(R.color.scarlet_red)
            EmotionColor.YELLOW -> getColor(R.color.honey_yellow)
            EmotionColor.GREEN -> getColor(R.color.light_green)
        }
    }

    private fun Context.spToPx(sp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp,
            resources.displayMetrics
        )
    }

}