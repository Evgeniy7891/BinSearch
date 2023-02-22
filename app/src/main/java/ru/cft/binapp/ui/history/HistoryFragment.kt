package ru.cft.binapp.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.cft.binapp.R
import ru.cft.binapp.databinding.FragmentHistoryBinding
import ru.cft.binapp.databinding.FragmentMainBinding
import ru.cft.binapp.ui.main.MainViewModel

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private val viewModel: HistoryViewModel by viewModels()

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Cannot access view")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        initialClick()
        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        lifecycleScope.launch {
            val list = viewModel.getInfo()
            val adapter = HistoryAdapter(list)
            binding.recyclerview.adapter = adapter
        }
    }
    private fun initialClick() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_mainFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}