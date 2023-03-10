package ru.cft.binapp.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import ru.cft.binapp.R
import ru.cft.binapp.databinding.FragmentMainBinding
import ru.cft.binapp.utils.startAnim

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Cannot access view")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        initialClick()
        onInput()

        return binding.root
    }

    private fun initialClick() {
        with(binding) {
            btnGet.setOnClickListener {
                val bin = etBinNumber.text.toString()
                if (bin != "") {
                    initialInfo(bin.toInt())
                    btnGet.isLoading = true
                }
            }
            btnHistory.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_historyFragment)
            }
            tvPhoneResult.setOnClickListener {
                goToPhone(tvPhoneResult.text.toString())
            }
            tvWebResult.setOnClickListener {
                goToUrl("https://+${tvWebResult.text.toString()}")
            }
        }
    }

    private fun initialInfo(bin: Int) {
        viewModel.getInfo(bin)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            delay(3000)
            viewModel.result.collect { result ->
                if (result != null) {
                    viewModel.saveInfo(result)
                    with(binding) {
                        btnGet.isLoading = false
                        startAnim(anim1)
                        startAnim(anim2)
                        startAnim(anim3)
                        startAnim(anim4)
                        startAnim(anim5)
                        startAnim(anim6)
                        startAnim(anim7)
                        tvNetworkResult.text = result.scheme
                        tvBrandResult.text = result.brand
                        tvLengthResult.text = result.number?.length.toString()
                        tvLuhnResult.text = result.number?.luhn.toString()
                        tvTypeResult.text = result.type
                        tvPrepaidResult.text = if (result.prepaid == true) "Yes" else "No"
                        tvCountryResult.text = result.country?.name
                        tvBankResult.text = result.bank?.name
                        tvWebResult.text = result.bank?.url
                        tvPhoneResult.text = result.bank?.phone
                        viewModel.deleteInfo()
                    }
                } else {
                    binding.btnGet.isLoading = false
                    viewModel.deleteInfo()
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

    private fun goToUrl(url: String) {
        val uri = Uri.parse(url)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun goToPhone(number: String) {
        val uri = Uri.fromParts("tel", number, null)
        startActivity(Intent(Intent.ACTION_DIAL, uri))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
