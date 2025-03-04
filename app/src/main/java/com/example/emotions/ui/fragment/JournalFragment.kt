package com.example.emotions.ui.fragment

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emotions.R
import com.example.emotions.databinding.FragmentJournalBinding
import com.example.emotions.domain.model.EmotionColor
import com.example.emotions.domain.model.SavedEmotion
import com.example.emotions.presentation.SpaceItemDecoration
import com.example.emotions.presentation.adapter.EmotionListAdapter
import com.example.emotions.presentation.dpToPx
import com.example.emotions.ui.activity.AddingEmotionActivity
import com.example.emotions.ui.activity.EditingRecordActivity

class JournalFragment : Fragment() {

    private lateinit var savedEmotions: List<SavedEmotion>
    private var rightHalf: EmotionColor? = null
    private var leftHalf: EmotionColor? = null

    companion object {
        private const val ARG_EMOTIONS = "arg_emotions"
        private const val ARG_LEFT = "arg_left"
        private const val ARG_RIGHT = "arg_right"
        fun newInstance(
            emotions: List<SavedEmotion>,
            right: EmotionColor?,
            left: EmotionColor?
        ): JournalFragment {
            return JournalFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_EMOTIONS, ArrayList(emotions))
                    putParcelable(ARG_LEFT, left)
                    putParcelable(ARG_RIGHT, right)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedEmotions = arguments?.getParcelableArrayList(ARG_EMOTIONS) ?: emptyList()
        rightHalf = arguments?.getParcelable(ARG_RIGHT)
        leftHalf = arguments?.getParcelable(ARG_LEFT)
    }

    private var _binding: FragmentJournalBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EmotionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emotionsRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = EmotionListAdapter() {
            val intent = Intent(this.activity, EditingRecordActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
        binding.emotionsRecycler.adapter = adapter
        binding.emotionsRecycler.addItemDecoration(SpaceItemDecoration(8.dpToPx()))

        adapter.submitList(savedEmotions)

        binding.addRecordButton.setOnClickListener {

            val intent = Intent(this.activity, AddingEmotionActivity::class.java)
            startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(this.activity).toBundle()
            )
        }
//
//        val animator = ObjectAnimator.ofFloat(binding.gradientCircle, View.ROTATION, 0f, 360f)
//        animator.duration = 5000
//        animator.repeatCount = ValueAnimator.INFINITE
//        animator.interpolator = android.view.animation.LinearInterpolator()
//        animator.start()

        binding.coloredLeftHalfCircle.setImageResource(getHalfCircle(leftHalf))
        binding.coloredRightHalfCircle.setImageResource(getHalfCircle(rightHalf))
    }

    private fun getHalfCircle(color: EmotionColor?): Int {

        return when (color) {
            EmotionColor.RED -> R.drawable.half_circle_red
            EmotionColor.GREEN -> R.drawable.half_circle_green
            EmotionColor.BLUE -> R.drawable.half_circle_blue
            EmotionColor.YELLOW -> R.drawable.half_circle_yellow
            null -> android.R.color.transparent
        }
    }
}