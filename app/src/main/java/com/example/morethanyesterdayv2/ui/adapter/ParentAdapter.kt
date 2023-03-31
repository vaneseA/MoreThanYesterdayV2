package com.example.morethanyesterdayv2.ui.adapter

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.databinding.RecordRvItemBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.dialog.recordDAO
import com.example.morethanyesterdayv2.repository.SelectedDateRepository
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ParentAdapter(
    private val parentList: List<ExerciseEntity>,
    var context: Context,
    private val childList: List<RecordEntity>
) :
    RecyclerView.Adapter<ParentAdapter.Holder>(), AddSetDialogInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            RecordRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val parentItem = parentList[position]
        holder.setData(parentItem, position, childList)

        holder.binding.nestedRV.adapter =
            ChildAdapter(childList.filter { it.exerciseId == parentItem.exerciseId })
        holder.binding.nestedRV.layoutManager = LinearLayoutManager(context)
    }

    override fun getItemCount(): Int = parentList.size

    inner class Holder(val binding: RecordRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val selectedDateActivity = SelectedDateActivity.getInstance()

        fun setData(exerciseEntity: ExerciseEntity, position: Int, childList: List<RecordEntity>) {
            val exerciseId = exerciseEntity.exerciseId
            binding.NameArea.text = exerciseEntity.exerciseName
            binding.TypeArea.text = exerciseEntity.exerciseType
            binding.totalSetArea.text = "총 " + exerciseEntity.totalSet.toString() + "set, "
            binding.totalKgArea.text = "총 " + exerciseEntity.totalKG + "kg, "
            binding.bestKgArea.text = "최고 " + exerciseEntity.bestKg + "kg, "
            binding.totalCountArea.text = "총 " + exerciseEntity.totalCount + "회"

            binding.nestedRV.adapter =
                ChildAdapter(childList.filter { it.exerciseId == exerciseId })
            binding.nestedRV.layoutManager = LinearLayoutManager(context)

            binding.nestedRV.setHasFixedSize(true)
            binding.nestedRV.layoutManager = LinearLayoutManager(itemView.context)
            binding.addSetBtn.setOnClickListener {
                selectedDateActivity?.clickViewEvents(
                    position,
                    exerciseEntity,
                    exerciseId
                )
            }


        }
    }

    override fun onYesButtonClick(id: Int) {
    }

}