package com.basar.spacextracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.basar.spacextracker.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : AppCompatActivity() {

    private var _binding: ActivityHostBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}