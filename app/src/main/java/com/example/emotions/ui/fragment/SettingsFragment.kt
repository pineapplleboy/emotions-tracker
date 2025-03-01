package com.example.emotions.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emotions.databinding.FragmentSettingsBinding
import com.example.emotions.domain.model.NotificationSettings
import com.example.emotions.presentation.SpaceItemDecoration
import com.example.emotions.presentation.adapter.NotificationAdapter
import com.example.emotions.presentation.dpToPx

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var adapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notifications = listOf(
            NotificationSettings(0, "20:00"),
            NotificationSettings(0, "21:00"),
            NotificationSettings(0, "22:30"),
        )

        adapter = NotificationAdapter()
        binding.notificationRecycler.adapter = adapter
        binding.notificationRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(notifications)

        binding.notificationRecycler.addItemDecoration(SpaceItemDecoration(16.dpToPx()))
    }
}