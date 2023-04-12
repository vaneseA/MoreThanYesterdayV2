package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentCardioBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter

class CardioFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentCardioBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): CardioFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return CardioFragment().apply {
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
        binding = FragmentCardioBinding.inflate(inflater, container, false)

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter

        // exerciseDataList 초기화
        exerciseDataList.clear()
        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "런닝머신", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "로잉머신", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "실내사이클", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "에어로빅", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "줄넘기", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "스텝", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "에르고메터", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "점핑잭", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "에어로밍", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "크로스핏", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "스피닝", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "윗몸일으키기", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "버피", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "로프밀", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "스쿼트점프", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "바운스잭", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "클린앤저크", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "사이드 스텝", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "스키점프", type = "유산소"))
        exerciseDataList.add(ExerciseData(name = "농구", type = "유산소"))
        return binding.root
    }
}