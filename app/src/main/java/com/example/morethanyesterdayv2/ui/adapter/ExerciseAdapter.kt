package com.example.morethanyesterdayv2.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.databinding.ExerciseRvItemBinding
import com.example.morethanyesterdayv2.viewmodel.ExerciseModel

    class ExerciseAdapter(private val context: Context) :
        RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

        var datas = mutableListOf<ExerciseModel>()

        interface OnItemClickListener {
            fun onItemClick(v: View, data: ExerciseAdapter, pos: Int)
        }

        private var listener: OnItemClickListener? = null
        fun setOnItemClickListener(listener: OnItemClickListener) {
            this.listener = listener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseAdapter.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view: View = inflater.inflate(R.layout.exercise_rv_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ExerciseAdapter.ViewHolder, position: Int) {
            holder.bind(datas[position])
        }

        override fun getItemCount(): Int = datas.size


        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            private val txtName: TextView = itemView.findViewById(R.id.exerciseNameArea)
            private val txtType: TextView = itemView.findViewById(R.id.exerciseTypeArea)

            fun bind(item: ExerciseModel) {
                txtName.text = item.name
                txtType.text = item.type


//                val pos = adapterPosition
//                if (pos != RecyclerView.NO_POSITION) {
//                    itemView.setOnClickListener {
//                        listener?.onItemClick(itemView, item, pos)
//                    }
//                }


            }
        }

    }

