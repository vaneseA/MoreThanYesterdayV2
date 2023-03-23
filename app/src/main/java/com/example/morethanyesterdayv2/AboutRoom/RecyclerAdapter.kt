package com.example.morethanyesterdayv2.AboutRoom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.databinding.RecordRvItemBinding
import java.text.SimpleDateFormat

class RecyclerAdapter(val memoList: List<RoomMemo>) : RecyclerView.Adapter<RecyclerAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            RecordRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(memoList.get(position))
    }

    override fun getItemCount(): Int = memoList.size

    class Holder(val binding: RecordRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setMemo(memo:RoomMemo){
            with(binding){
                NameArea.text = "${memo.no}"
                TypeArea.text = memo.content

//                val sdf = SimpleDateFormat("yyyy/MM/dd:mm")
//                textDate.text = sdf.format(memo.datetime)
            }

        }
    }
}