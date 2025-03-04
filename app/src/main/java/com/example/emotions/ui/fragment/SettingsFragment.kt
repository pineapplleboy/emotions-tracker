package com.example.emotions.ui.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emotions.R
import com.example.emotions.databinding.FragmentSettingsBinding
import com.example.emotions.domain.model.NotificationSettings
import com.example.emotions.presentation.SpaceItemDecoration
import com.example.emotions.presentation.adapter.NotificationAdapter
import com.example.emotions.presentation.dpToPx
import com.example.emotions.ui.activity.MainActivity
import com.google.android.material.materialswitch.MaterialSwitch

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

        val notifications = mutableListOf(
            NotificationSettings("20:00"),
            NotificationSettings("21:00"),
            NotificationSettings("22:30"),
            NotificationSettings("22:30"),
            NotificationSettings("22:30"),
            NotificationSettings("22:30"),
            NotificationSettings("22:30")
        )

        adapter = NotificationAdapter { notification ->
            notifications.remove(notification)
            adapter.submitList(notifications.toList())
            adapter.notifyDataSetChanged()
            binding.notificationRecycler.scrollToPosition(notifications.size - 1)
        }

        binding.notificationRecycler.adapter = adapter
        binding.notificationRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(notifications)

        binding.notificationRecycler.addItemDecoration(SpaceItemDecoration(16.dpToPx()))

        binding.appCompatButton.setOnClickListener {
            (activity as? MainActivity)?.showAddNotificationPanel {
                notifications.add(NotificationSettings(it))
                adapter.submitList(notifications.toList())
                adapter.notifyDataSetChanged()
                binding.notificationRecycler.scrollToPosition(notifications.size - 1)
            }
        }

        changeSwitchColors(binding.fingerprintSwitch)
        changeSwitchColors(binding.notificationsSwitch)
    }

    private fun changeSwitchColors(view: MaterialSwitch) {
        view.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                view.trackTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green_switch
                    )
                )
            } else {
                view.trackTintList = ColorStateList.valueOf(Color.WHITE)
            }
        }
    }
}