package com.example.morethanyesterdayv2.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.databinding.ExerciseRvItemBinding
import com.example.morethanyesterdayv2.viewmodel.ExerciseModel

class ExerciseAdapter(private val context: Context) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {
    private lateinit var binding: ExerciseRvItemBinding
    var datas = mutableListOf<ExerciseModel>()


    class ViewHolder(val binding: ExerciseRvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ExerciseRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = datas[position]
            holder.binding.exerciseNameArea.text = currentItem.name
            holder.binding.exerciseTypeArea.text = currentItem.type

    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

