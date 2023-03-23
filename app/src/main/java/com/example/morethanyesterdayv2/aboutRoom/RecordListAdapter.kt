package com.example.morethanyesterdayv2.aboutRoom

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.databinding.RecordRvItemBinding


class RecordListAdapter(
    val exerciseList: List<ExerciseEntity>,
    var context: Context
) :
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
                textNo.text = "${exerciseEntity.no}" + "."
                NameArea.text = exerciseEntity.exerciseName
                TypeArea.text = exerciseEntity.exerciseType

                addSetBtn.setOnClickListener {
                    addSetDialog(
                        it.context,
                        exerciseEntity.exerciseName,
                        exerciseEntity.exerciseType
                    )
                }
            }

        }

        private fun addSetDialog(context: Context, name: String, type: String) {
            val dialogView =
                LayoutInflater.from(context)
                    .inflate(R.layout.custom_add_set_dialog, null)
            val builder = AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(false)

            val alertDialog = builder.show()

            //dialog radius줘서 모서리 둥글게
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            //binding 전까지는 이거
            val dialogExerciseName = alertDialog.findViewById<TextView>(R.id.dialogExerciseName)
            val dialogExerciseType = alertDialog.findViewById<TextView>(R.id.dialogExerciseType)
            val dialogCancleBtn = alertDialog.findViewById<Button>(R.id.dialogCancleBtn)
            val dialogAddBtn = alertDialog.findViewById<Button>(R.id.dialogAddBtn)

            val plusFiveBtn = alertDialog.findViewById<TextView>(R.id.plusFiveBtn)
            val minusFiveBtn = alertDialog.findViewById<TextView>(R.id.minusFiveBtn)
            val plusOneBtn = alertDialog.findViewById<TextView>(R.id.plusOneBtn)
            val minusOneBtn = alertDialog.findViewById<TextView>(R.id.minusOneBtn)


            dialogExerciseName?.text = name
            dialogExerciseType?.text = type

            dialogCancleBtn?.setOnClickListener { alertDialog.dismiss() }
            dialogAddBtn?.setOnClickListener { alertDialog.dismiss() }

            plusFiveBtn?.setOnClickListener {}
            minusFiveBtn?.setOnClickListener {}
            plusOneBtn?.setOnClickListener {}
            minusOneBtn?.setOnClickListener {}


        }
    }

}
