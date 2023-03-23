package com.example.morethanyesterdayv2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.viewmodel.ExerciseWithSet
import com.example.morethanyesterdayv2.viewmodel.ProfileData

class ProfileAdapter(private val context: Context) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {


    private var exerciseWithSetList = mutableListOf<ExerciseWithSet>()
    var datas = mutableListOf<ProfileData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: ProfileData, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.exercise_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.exerciseNameArea)
        private val txtType: TextView = itemView.findViewById(R.id.exerciseTypeArea)

        fun bind(item: ProfileData) {
            txtName.text = item.name
            txtType.text = item.type

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, item, pos)
                    createDeleteAlterDialog(pos)
                }
            }


        }
    }
    fun createDeleteAlterDialog(currentItem: Int){

        AlertDialog.Builder(context)
            .setTitle("운동 삭제")
            .setMessage("해당 운동을 삭제하시겠습니까?")
            .setPositiveButton("삭제", {
                    dialog , _ ->
//                exerciseWithSetList.remove(currentItem)
//                onDeleteCallBack(currentItem)
                notifyDataSetChanged()
                dialog.dismiss()
            })
            .setNegativeButton("취소",{
                    dialog, _ -> dialog.dismiss()
            }).create().show()
    }

}

