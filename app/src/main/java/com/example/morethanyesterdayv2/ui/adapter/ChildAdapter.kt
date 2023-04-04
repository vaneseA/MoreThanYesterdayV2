package com.example.morethanyesterdayv2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.databinding.SetRvItemBinding
import com.example.morethanyesterdayv2.viewmodel.SelectedDateViewModel



class ChildAdapter(
    var recordList: List<RecordEntity>,
    private val viewModel: SelectedDateViewModel,
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
        holder.binding.removeButton.setOnClickListener {
            viewModel.onDeleteRecord(listForNested, holder.binding.root.context)

        }
    }

    override fun getItemCount(): Int = recordList.size

    class Holder(val binding: SetRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var recordEntity: RecordEntity? = null
        fun recordData(recordEntity: RecordEntity, position: Int) {
            var childPosition = (position + 1)

            binding.recordSetItem.text = "${childPosition}번째 세트"
            binding.recordKgItem.text = "${(recordEntity?.kg)}kg"
            binding.recordCountItem.text = "${(recordEntity?.count)}회"

        }
    }
}