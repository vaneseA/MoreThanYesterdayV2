package com.example.morethanyesterdayv2.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.databinding.SetRvItemBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.dialog.recordDAO
import com.example.morethanyesterdayv2.ui.activity.SelectExerciseActivity
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChildAdapter(
    var recordList: List<RecordEntity>,
    var recordDAO: RecordDAO
) :
    RecyclerView.Adapter<ChildAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            SetRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listForNested = recordList[position]
        holder.recordData(listForNested, position)
    }

    override fun getItemCount(): Int = recordList.size

    class Holder(val binding: SetRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var recordEntity: RecordEntity? = null
        fun recordData(recordEntity: RecordEntity, position: Int) {
            var childPosition = (position + 1)
            binding.recordSetItem.text = "${childPosition}번째 세트"
            binding.recordKgItem.text = "${(recordEntity?.kg)}kg"
            binding.recordCountItem.text = "${(recordEntity?.count)}회"
            binding.removeButton.setOnClickListener {
                recordEntity?.let {
                    deleteRecord(it)
                    (binding.root.context as? SelectedDateActivity)?.finish()
                    val intent = Intent(binding.root.context, SelectedDateActivity::class.java)
                    intent.putExtra("selectedDate", recordEntity.selectedDate)
                    binding.root.context.startActivity(intent)
                }
            }

        }

        private fun deleteRecord(record: RecordEntity) {
            CoroutineScope(Dispatchers.IO).launch {
                recordDAO.delete(record)
            }
        }

    }
}