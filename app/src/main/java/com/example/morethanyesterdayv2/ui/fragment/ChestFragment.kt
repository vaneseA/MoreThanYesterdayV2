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
import com.example.morethanyesterdayv2.data.ChestExerciseData
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentAbsBinding
import com.example.morethanyesterdayv2.databinding.FragmentBackBinding
import com.example.morethanyesterdayv2.databinding.FragmentChestBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter
import com.example.morethanyesterdayv2.viewmodel.SelectExerciseViewModel

class ChestFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentChestBinding
    private lateinit var viewModel: SelectExerciseViewModel
    private lateinit var selectedDate: String

    companion object {
        fun newInstance(selectedDate: String): ChestFragment {
            val fragment = ChestFragment()
            val args = Bundle()
            args.putString("selectedDate", selectedDate)
            fragment.arguments = args
            return fragment
        }
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        selectedDate = arguments?.getString(ARG_SELECTED_DATE) ?: ""
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChestBinding.inflate(inflater, container, false)

        // RecyclerView 설정
        recyclerView = binding.chestRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = exerciseListAdapter

        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "벤치프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "덤벨프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "인클라인벤치프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "딥스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "푸쉬업", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "플라이", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "케이블크로스오버", type = "가슴"))

        // 어댑터에 데이터 설정
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList
        return binding.root
    }
}
