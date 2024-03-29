package com.dev.weatherapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.dev.weatherapp.R
import com.dev.weatherapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("Binding null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weatherButton.setOnClickListener {
            val text = binding.editText.text.toString().trim()
            if (text.isNotEmpty()) {
                launchDayListFragment(text)
            } else {
                Toast.makeText(context, "Вы ничего не ввели", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchDayListFragment(city: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DayListFragment.newInstance(city))
            .addToBackStack(null)
            .commit()
    }
}