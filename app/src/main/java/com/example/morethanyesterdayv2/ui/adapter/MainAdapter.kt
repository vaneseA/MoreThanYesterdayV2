package com.example.morethanyesterdayv2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.databinding.MainRvItemBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface


class MainAdapter(
    private val setList: List<ExerciseEntity>,
    var context: Context
) :
    RecyclerView.Adapter<MainAdapter.Holder>(), AddSetDialogInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            MainRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val setItem = setList[position]
        holder.setData(setItem, position)
    }

    override fun getItemCount(): Int = setList.size

    inner class Holder(val binding: MainRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(exerciseEntity: ExerciseEntity, position: Int) {
            binding.mainExerciseName.text = exerciseEntity.exerciseName
            binding.mainExerciseType.text = exerciseEntity.exerciseType
            binding.mainTotalSet.text = "총 ${exerciseEntity.totalSet}set, "
            binding.mainTotalKg.text = "총 ${exerciseEntity.totalKG}kg, "
            binding.mainBestKg.text = "최고 ${exerciseEntity.bestKg}kg, "
            binding.mainTotalCount.text = "총 ${exerciseEntity.totalCount}회"
        }
    }

    override fun onYesButtonClick(id: Int) {
    }
}