package com.example.morethanyesterdayv2.aboutRoom

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
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

            val alertDialog = builder.show()

            //binding 전까지는 이거
            val dialogExerciseName = alertDialog.findViewById<TextView>(R.id.dialogExerciseName)
            val dialogExerciseType = alertDialog.findViewById<TextView>(R.id.dialogExerciseType)
            dialogExerciseType
            dialogExerciseType

            dialogExerciseName?.text = name
            dialogExerciseType?.text = type
        }
    }

}
