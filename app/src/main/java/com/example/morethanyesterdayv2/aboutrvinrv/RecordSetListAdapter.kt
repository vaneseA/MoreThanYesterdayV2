package com.example.morethanyesterdayv2.aboutrvinrv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.databinding.RecordRvItemBinding
import com.example.morethanyesterdayv2.databinding.SetRvItemBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity


class RecordSetListAdapter(

    var context: Context
) :
    RecyclerView.Adapter<RecordSetListAdapter.Holder>(), AddSetDialogInterface {
    var recordList = listOf<RecordEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            SetRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val list = recordList[position]
        holder.setData(list, position)
    }

    override fun getItemCount(): Int = recordList.size

    class Holder(val binding: SetRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val selectedDateActivity = SelectedDateActivity.getInstance()
        var recordEntity: RecordEntity? = null
        var position: Int? = null

        fun setData(recordEntity: RecordEntity, position: Int) {
            binding.recordSetItem.text = recordEntity?.setNo.toString()
            binding.recordKgItem.text = recordEntity?.kg
            binding.recordCountItem.text = recordEntity?.count
            this.recordEntity = recordEntity
            this.position = position
        }

    }

    override fun onYesButtonClick(id: Int) {
    }
}

