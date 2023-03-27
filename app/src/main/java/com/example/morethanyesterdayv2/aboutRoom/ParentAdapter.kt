package com.example.morethanyesterdayv2.aboutRoom

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.aboutrvinrv.ChildAdapter
import com.example.morethanyesterdayv2.databinding.RecordRvItemBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity


class ParentAdapter(
    private val parentList: List<ExerciseEntity>,
    var context: Context
) :
    RecyclerView.Adapter<ParentAdapter.Holder>(), AddSetDialogInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            RecordRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val parentItem = parentList[position]
        holder.setData(parentItem, position)
    }

    override fun getItemCount(): Int = parentList.size

    inner class Holder(val binding: RecordRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var childAdapter: ChildAdapter
        private val selectedDateActivity = SelectedDateActivity.getInstance()

        fun setData(exerciseEntity: ExerciseEntity, position: Int) {
            binding.NameArea.text = exerciseEntity.exerciseName
            binding.TypeArea.text = exerciseEntity.exerciseType
            binding.totalSetArea.text = "총 " + exerciseEntity.totalSet.toString() + "set, "
            binding.totalKgArea.text = "총 " + exerciseEntity.totalKG + "kg, "
            binding.bestKgArea.text = "최고 " + exerciseEntity.bestKg + "kg, "
            binding.totalCountArea.text = "총 " + exerciseEntity.totalCount + "회"


            binding.addSetBtn.setOnClickListener {
                selectedDateActivity?.clickViewEvents(position, exerciseEntity)
            }

            childAdapter = ChildAdapter(exerciseEntity.recordList, context)
            binding.nestedRV.setHasFixedSize(true)
            binding.nestedRV.layoutManager = LinearLayoutManager(itemView.context)
            binding.nestedRV.adapter = childAdapter
        }
    }

    override fun onYesButtonClick(id: Int) {
    }
}