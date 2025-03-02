package com.example.emotions.ui.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
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

class JournalFragment : Fragment() {

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
        adapter = EmotionListAdapter()
        binding.emotionsRecycler.adapter = adapter
        binding.emotionsRecycler.addItemDecoration(SpaceItemDecoration(8.dpToPx()))

        val savedEmotions = listOf(
            SavedEmotion("1", "Счастье", "вчера, 20:30", EmotionColor.YELLOW, R.drawable.ic_happinness),
            SavedEmotion("2", "Грусть 2", "вчера, 20:30", EmotionColor.BLUE, R.drawable.ic_fatigue),
            SavedEmotion("3", "Злоба", "вчера, 20:30", EmotionColor.RED, R.drawable.ic_rage),
            SavedEmotion("4", "Спокойствие", "вчера, 20:30", EmotionColor.GREEN, R.drawable.ic_calm)
        )

        adapter.submitList(savedEmotions)

        binding.addRecordButton.setOnClickListener{

            val intent = Intent(this.activity, AddingEmotionActivity::class.java)
            startActivity(intent)
        }

        val animator = ObjectAnimator.ofFloat(binding.gradientCircle, View.ROTATION, 0f, 360f)
        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = android.view.animation.LinearInterpolator()
        animator.start()
    }
}