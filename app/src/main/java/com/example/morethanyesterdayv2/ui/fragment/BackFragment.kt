package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentBackBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter

class BackFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentBackBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): BackFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return BackFragment().apply {
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

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter


        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "벤치프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "덤벨프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "인클라인벤치프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "딥스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "푸쉬업", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "플라이", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "케이블크로스오버", type = "가슴"))

        return binding.root
    }
}