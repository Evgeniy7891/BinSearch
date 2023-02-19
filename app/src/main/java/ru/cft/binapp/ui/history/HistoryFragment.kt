package ru.cft.binapp.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.cft.binapp.R
import ru.cft.binapp.databinding.FragmentHistoryBinding
import ru.cft.binapp.databinding.FragmentMainBinding
import ru.cft.binapp.ui.main.MainViewModel

class HistoryFragment : Fragment() {

    private val viewModel: HistoryViewModel by viewModels()

    lateinit var binding: FragmentHistoryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

}