package ru.cft.binapp.ui

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.binapp.R
import ru.cft.binapp.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        onInput()
        binding.btnGet.setOnClickListener {
            val bin = binding.etBinNumber.text.toString().toInt()
            initialInfo(bin)
        }
        return binding.root
    }

    private fun initialInfo(bin: Int) {
        viewModel.getInfo(bin)
        lifecycleScope.launchWhenCreated {
            viewModel.result.collect { result ->
                with(binding) {
                    tvNetworkResult.text = result?.scheme
                    tvBrandResult.text = result?.brand
                    tvLengthResult.text = result?.number?.length.toString()
                    tvLuhnResult.text = result?.number?.luhn.toString()
                    tvTypeResult.text = result?.type
                    tvPrepaidResult.text = if (result?.prepaid == true) "Yes" else "No"
                    tvCountryResult.text = result?.country?.name
                    tvBankResult.text = result?.bank?.name+", "+result?.bank?.city
                    tvWebResult.text = result?.bank?.url
                    tvPhoneResult.text = result?.bank?.phone
                }
            }
        }
    }
    private fun onInput() {
        with(binding) {
            etBinNumber.filters = arrayOf<InputFilter>(LengthFilter(8))
            etBinNumber.inputType = InputType.TYPE_CLASS_NUMBER
        }
    }
}