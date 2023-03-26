package com.example.morethanyesterdayv2.aboutRoom

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.aboutrvinrv.ParentItem
import com.example.morethanyesterdayv2.aboutrvinrv.RecordSetListAdapter
import com.example.morethanyesterdayv2.databinding.RecordRvItemBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.dialog.recordSetList
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity


class RecordListAdapter(
    val exerciseList: List<ExerciseEntity>,
    var context: Context
) :
    RecyclerView.Adapter<RecordListAdapter.ParentViewHolder>(), AddSetDialogInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding =
            RecordRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val parentItem = exerciseList[position]
        holder.setData(parentItem, position)
    }

    override fun getItemCount(): Int = exerciseList.size

    inner class ParentViewHolder(val binding: RecordRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val recordSetListAdapter = RecordSetListAdapter(recordSetList,context)

        private val selectedDateActivity = SelectedDateActivity.getInstance()
        var exerciseEntity: ExerciseEntity? = null
        var position: Int? = null

        fun setData(parentItem: ParentItem, position: Int) {
            binding.textNo.text = exerciseEntity?.no.toString()
            binding.NameArea.text = exerciseEntity?.exerciseName
            binding.TypeArea.text = exerciseEntity?.exerciseType
            binding.addSetBtn.setOnClickListener {
                selectedDateActivity?.clickViewEvents(position, exerciseEntity)
            }

            binding.nestedRV.setHasFixedSize(true)
            binding.nestedRV.layoutManager = LinearLayoutManager(itemView.context)
            val adapter = RecordSetListAdapter(parentItem,mList)
            binding.nestedRV.adapter = adapter


            this.exerciseEntity = exerciseEntity
            this.position = position
        }

    }

    override fun onYesButtonClick(id: Int) {
    }
}

