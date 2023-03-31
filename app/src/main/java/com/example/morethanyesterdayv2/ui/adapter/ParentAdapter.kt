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
import com.example.morethanyesterdayv2.repository.SelectedDateRepository
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity


class ParentAdapter(
    private val parentList: List<ExerciseEntity>,
    application: Application,
    var context: Context
) :
    RecyclerView.Adapter<ParentAdapter.Holder>(), AddSetDialogInterface {
    private val selectedDateRepository = SelectedDateRepository(application)

    private val _recordListLiveData = MutableLiveData<List<RecordEntity>>()
    val recordListLiveData: LiveData<List<RecordEntity>> = _recordListLiveData

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
        private val selectedDateActivity = SelectedDateActivity.getInstance()

        fun setData(exerciseEntity: ExerciseEntity, position: Int) {
            binding.NameArea.text = exerciseEntity.exerciseName
            binding.TypeArea.text = exerciseEntity.exerciseType
            binding.totalSetArea.text = "총 " + exerciseEntity.totalSet.toString() + "set, "
            binding.totalKgArea.text = "총 " + exerciseEntity.totalKG + "kg, "
            binding.bestKgArea.text = "최고 " + exerciseEntity.bestKg + "kg, "
            binding.totalCountArea.text = "총 " + exerciseEntity.totalCount + "회"
            binding.nestedRV.setHasFixedSize(true)
            binding.nestedRV.layoutManager = LinearLayoutManager(itemView.context)
            val adapter = ChildAdapter(exerciseEntity.recordList)
            binding.nestedRV.adapter = adapter

            binding.nestedRV.setOnClickListener {
                val intent = Intent(context, SelectedDateActivity::class.java)
                intent.putExtra("selectedDate", exerciseEntity.selectedDate)
                context.startActivity(intent)
            }

            binding.addSetBtn.setOnClickListener {
                selectedDateActivity?.clickViewEvents(position, exerciseEntity)
            }


        }
    }

    override fun onYesButtonClick(id: Int) {
    }
}