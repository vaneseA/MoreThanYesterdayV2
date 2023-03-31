package com.example.morethanyesterdayv2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.databinding.SetRvItemBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface


class ChildAdapter(
    var recordList: List<RecordEntity>
) :
    RecyclerView.Adapter<ChildAdapter.Holder>(){

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
            binding.recordSetItem.text = recordEntity?.setNo.toString() + "번째 세트"
            binding.recordKgItem.text = recordEntity?.kg + "kg"
            binding.recordCountItem.text = recordEntity?.count + "회"

        }

    }

}