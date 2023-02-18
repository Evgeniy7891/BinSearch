package ru.cft.binapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.binapp.R
import ru.cft.binapp.databinding.FragmentMainBinding
import ru.cft.binapp.models.BinModel
import java.lang.reflect.Type
import java.util.ArrayList

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, BinModel::class.java).type
    private val key = "key"

    private val viewModel: MainViewModel by viewModels()

    lateinit var binding: FragmentMainBinding

    var pref: SharedPreferences? = null
    private var result = listOf<BinModel>()
    private val history = mutableListOf<BinModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        pref = context?.getSharedPreferences("repo", Context.MODE_PRIVATE)
        initialClick()
        onInput()

        return binding.root
    }

    private fun initialClick() {
        with(binding) {
            btnGet.setOnClickListener {
                val bin = binding.etBinNumber.text.toString().toInt()
                initialInfo(bin)
            }
            btnHistory.setOnClickListener {
                pref?.getString(key, "History empty")?.let{
                    result = gson.fromJson(it, type)
                }
            }
        }
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
                    tvBankResult.text = result?.bank?.name + ", " + result?.bank?.city
                    tvWebResult.text = result?.bank?.url
                    tvPhoneResult.text = result?.bank?.phone

                    if (result != null) {
                        history.add(result)
                        val editor = pref?.edit()
                        val text = gson.toJson(history)
                        editor?.putString(key, text)
                        editor?.apply()
                    }
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