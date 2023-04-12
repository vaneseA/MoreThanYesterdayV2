package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentCustomBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter

class CustomFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentCustomBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): CustomFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return CustomFragment().apply {
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
        binding = FragmentCustomBinding.inflate(inflater, container, false)

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter
        binding.customAddBtn.setOnClickListener {
            // 저장 완료 메시지를 띄우고 다이얼로그를 닫음
            Toast.makeText(
                requireContext(),
                "구현 준비중.",
                Toast.LENGTH_LONG
            ).show()
        }
        // exerciseDataList 초기화
        exerciseDataList.clear()
        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "커스텀", type = "커스텀"))
        return binding.root
    }
}