package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentLowerBodyBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter

class LowerBodyFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentLowerBodyBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): LowerBodyFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return LowerBodyFragment().apply {
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
        binding = FragmentLowerBodyBinding.inflate(inflater, container, false)

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter


        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "런지", type = "하체"))
        exerciseDataList.add(ExerciseData(name = "레그컬", type = "하체"))
        exerciseDataList.add(ExerciseData(name = "스쿼트", type = "하체"))


        return binding.root
    }
}