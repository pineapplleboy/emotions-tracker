package com.example.emotions.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.emotions.R
import com.example.emotions.databinding.ActivityMainBinding
import com.example.emotions.presentation.adapter.EmotionListAdapter
import com.example.emotions.ui.fragment.JournalFragment
import com.example.emotions.ui.fragment.StatsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: EmotionListAdapter
    private lateinit var binding: ActivityMainBinding

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
                    loadFragment(JournalFragment())
                    true
                }
                R.id.item_stats -> {
                    loadFragment(StatsFragment())
                    true
                }
                R.id.item_settings -> {
                    loadFragment(JournalFragment())
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            loadFragment(JournalFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}