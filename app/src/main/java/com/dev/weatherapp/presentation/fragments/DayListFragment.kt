package com.dev.weatherapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dev.weatherapp.databinding.FragmentDaylistBinding
import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.presentation.viewModel.MainViewModel
import com.dev.weatherapp.presentation.adapter.DayListAdapter

class DayListFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    private lateinit var city: String

    private var _binding: FragmentDaylistBinding? = null
    private val binding: FragmentDaylistBinding
        get() = _binding ?: throw RuntimeException("Binding null")

    private lateinit var dayListAdapter: DayListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTemperature(city)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        dayListAdapter = DayListAdapter()
        binding.rvDayList.adapter = dayListAdapter
        viewModel.dayTemperature.observe(viewLifecycleOwner) {
            dayListAdapter.submitList(it)
        }
        setUpClickListener()
    }

    private fun setUpClickListener() {
        dayListAdapter.onItemClickListener = object : DayListAdapter.OnItemClickListener {
            override fun onItemClick(day: Day) {
                Log.d("DayListFragment",day.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getString(KEY_CITY)?.let {
            city = it
        }
    }


    companion object {

        private const val KEY_CITY = "city"

        fun newInstance(city: String): DayListFragment {
            return DayListFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_CITY, city)
                }
            }
        }
    }
}