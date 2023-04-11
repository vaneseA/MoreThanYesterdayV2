package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.databinding.FragmentAbsBinding
import com.example.morethanyesterdayv2.databinding.FragmentBackBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter
import com.example.morethanyesterdayv2.viewmodel.SelectExerciseViewModel

class BackFragment: Fragment() {
        private lateinit var exerciseListAdapter: ExerciseListAdapter
        private lateinit var recyclerView: RecyclerView
        private lateinit var binding: FragmentBackBinding
        private lateinit var viewModel: SelectExerciseViewModel
        private lateinit var selectedDate: String

        companion object {
            private const val ARG_SELECTED_DATE = "selectedDate"
            fun newInstance(selectedDate: String): AbsFragment {
                val args = Bundle().apply {
                    putString(ARG_SELECTED_DATE, selectedDate)
                }
                return AbsFragment().apply {
                    arguments = args
                }
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            selectedDate = arguments?.getString(ARG_SELECTED_DATE) ?: ""
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentBackBinding.inflate(inflater, container, false)

            recyclerView = binding.backRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(activity)
            val selectedDate = arguments?.getString("selectedDate") ?: ""
            Log.d("selectedDate111", selectedDate)
            exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
            recyclerView.adapter = exerciseListAdapter

            return binding.root
        }
    }
