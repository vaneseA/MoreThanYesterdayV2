package com.example.morethanyesterdayv2.aboutRoom

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.aboutrvinrv.RecordEntity
import com.example.morethanyesterdayv2.aboutrvinrv.RecordSetListAdapter
import com.example.morethanyesterdayv2.databinding.RecordRvItemBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity


class RecordListAdapter(
    val exerciseList: List<ExerciseEntity>,
    var context: Context
) :
    RecyclerView.Adapter<RecordListAdapter.Holder>(), AddSetDialogInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            RecordRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = exerciseList[position]
        holder.setData(member, position)
    }

    override fun getItemCount(): Int = exerciseList.size

    inner class Holder(val binding: RecordRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val recordSetListAdapter = RecordSetListAdapter(context)

        private val selectedDateActivity = SelectedDateActivity.getInstance()
        var exerciseEntity: ExerciseEntity? = null
        var mPosition: Int? = null

        fun setData(member: ExerciseEntity, position: Int) {
            binding.textNo.text = exerciseEntity?.no.toString()
            binding.NameArea.text = exerciseEntity?.exerciseName
            binding.TypeArea.text = exerciseEntity?.exerciseType
            binding.addSetBtn.setOnClickListener {
                selectedDateActivity?.clickViewEvents(position, member)
            }
            recordSetListAdapter
            binding.nestedRV.setHasFixedSize(false)
            binding.nestedRV.layoutManager = LinearLayoutManager(context)
            binding.nestedRV.adapter = recordSetListAdapter


            this.exerciseEntity = member
            this.mPosition = position
        }

    }

    override fun onYesButtonClick(id: Int) {
    }
}

