package com.example.morethanyesterdayv2.AboutRoom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.databinding.RecordRvItemBinding

class RecordListAdapter(val exerciseList: List<ExerciseEntity>) :
    RecyclerView.Adapter<RecordListAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            RecordRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(exerciseList.get(position))
    }

    override fun getItemCount(): Int = exerciseList.size

    class Holder(val binding: RecordRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setMemo(exerciseEntity: ExerciseEntity) {
            with(binding) {
                textNo.text = "${exerciseEntity.no}"+"."
                NameArea.text = exerciseEntity.exerciseName
                TypeArea.text = exerciseEntity.exerciseType

//                val sdf = SimpleDateFormat("yyyy/MM/dd:mm")
//                textDate.text = sdf.format(memo.datetime)
            }

        }
    }
}