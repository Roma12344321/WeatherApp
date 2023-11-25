package com.dev.weatherapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dev.weatherapp.databinding.FragmentHourlistBinding
import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.presentation.viewModel.MainViewModel
import java.lang.RuntimeException

class HourListFragment : Fragment() {

    private var _binding: FragmentHourlistBinding? = null

    private val binding: FragmentHourlistBinding
        get() = _binding ?: throw RuntimeException("FragmentHourlistBinding is null")

    private lateinit var day: Day

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHourlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Day>(KEY_DAY)?.let {
            day = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTemperatureForDay(day)
        viewModel.hourTemperature.observe(viewLifecycleOwner) {
            Log.d("HourListFragment", it.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        private const val KEY_DAY = "day"
        fun newInstance(day: Day): HourListFragment {
            return HourListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_DAY, day)
                }
            }
        }
    }
}