package com.dev.weatherapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dev.weatherapp.R
import com.dev.weatherapp.databinding.FragmentDaylistBinding
import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.presentation.adapters.DayListAdapter
import com.dev.weatherapp.presentation.viewModel.MainViewModel
import com.dev.weatherapp.presentation.viewModel.ViewModelFactory
import com.dev.weatherapp.presentation.viewModel.WeatherApp
import javax.inject.Inject

class DayListFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as WeatherApp).component
    }

    private var fragmentContainer : FragmentContainerView? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private lateinit var city: String

    private var _binding: FragmentDaylistBinding? = null
    private val binding: FragmentDaylistBinding
        get() = _binding ?: throw RuntimeException("Binding null")

    private lateinit var dayListAdapter: DayListAdapter

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
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
        _binding = FragmentDaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentContainer = view.findViewById(R.id.fragmentContainer)
        viewModel.loadTemperature(city)
        viewModel.loadCurrentWeather(city)
        setupProgressBar()
        setUpError()
        setupCurrentWeather()
        setUpRecyclerView()
    }

    private fun parseTime(str: String): String {
        val lastIndex = str.length - 5
        return str.substring(lastIndex)
    }

    private fun setupCurrentWeather() {
        viewModel.currentWeather.observe(viewLifecycleOwner) {
            binding.textViewDegree.text = it.temp.toString()
            binding.textViewTime.text = parseTime(it.time.toString())
            binding.textViewCurrentCondition.text = it.textCondition
            Glide.with(this)
                .load("https:" + it.iconCondition)
                .into(binding.imageViewCurrentCondition)
        }
    }

    private fun setUpRecyclerView() {
        dayListAdapter = DayListAdapter()
        binding.rvDayList.adapter = dayListAdapter
        viewModel.dayTemperature.observe(viewLifecycleOwner) {
            dayListAdapter.submitList(it)
        }
        setUpClickListener()
    }

    private fun setupProgressBar() {
        viewModel.isLoaded.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.GONE
                binding.cardViewCurrent.visibility = View.VISIBLE
            } else {
                binding.cardViewCurrent.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpError() {
        viewModel.showError.observe(viewLifecycleOwner) {
            if (it) {
                binding.cardViewCurrent.visibility = View.GONE
                Toast.makeText(context, getString(R.string.Failure), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setUpClickListener() {
        dayListAdapter.onItemClickListener = object : DayListAdapter.OnItemClickListener {
            override fun onItemClick(day: Day) {
                if (isOnePadeMode()) {
                    launchHourListFragment(day)
                } else {
                    launchHourListFragmentVertical(day)
                }
            }
        }
    }

    private fun isOnePadeMode(): Boolean {
        return fragmentContainer == null
    }

    private fun launchHourListFragment(day: Day) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, HourListFragment.newInstance(day))
            .addToBackStack(null)
            .commit()
    }

    private fun launchHourListFragmentVertical(day: Day) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HourListFragment.newInstance(day))
            .commit()
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