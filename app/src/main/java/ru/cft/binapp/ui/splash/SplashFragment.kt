package ru.cft.binapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.cft.binapp.R
import ru.cft.binapp.databinding.FragmentMainBinding
import ru.cft.binapp.databinding.FragmentSplachBinding
import ru.cft.binapp.utils.startAnim

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplachBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplachBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startAnim(binding.splashAnim)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }, DELAY_OPEN_NEXT_SCREEN)
    }

    companion object {
        private const val DELAY_OPEN_NEXT_SCREEN = 2500L
    }
}