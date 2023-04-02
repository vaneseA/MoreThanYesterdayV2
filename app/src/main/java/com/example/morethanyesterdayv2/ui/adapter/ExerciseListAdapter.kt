package com.example.morethanyesterdayv2.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.ui.activity.SelectExerciseActivity
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ExerciseListAdapter(private val context: Context, private val selectedDate: String) :
    RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    lateinit var appDatabase: AppDatabase
    val exerciseList = mutableListOf<ExerciseEntity>()
    lateinit var exerciseDAO: ExerciseDAO

    var exerciseDataList = mutableListOf<ExerciseData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: ExerciseData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.exercise_rv_item, parent, false)
        return ViewHolder(view,selectedDate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exerciseDataList[position])
    }

    override fun getItemCount(): Int = exerciseDataList.size


    inner class ViewHolder(view: View, private val selectedDate: String) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.exerciseNameArea)
        private val txtType: TextView = itemView.findViewById(R.id.exerciseTypeArea)

        fun bind(item: ExerciseData,) {
            txtName.text = item.name
            txtType.text = item.type
            var exerciseType = item.type

            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, item, position)
                    addExerciseDialog(
                        position,
                        txtName.text.toString(),
                        exerciseType,
                        selectedDate,
                    )
                }
            }


        }
    }

    fun addExerciseDialog(
        position: Int,
        exerciseName: String,
        exerciseType: String,
        selectedDate: String
    ) {


        AlertDialog.Builder(context)
            .setTitle(selectedDate)
            .setMessage("$exerciseName 을/를 추가하시겠습니까?")
            .setPositiveButton("Yes") { dialog, _ ->
                appDatabase =
                    Room.databaseBuilder(context, AppDatabase::class.java, "room_db")
                        .build()
                exerciseDAO = appDatabase.exerciseDAO()

                //랜덤ID부여
                var exerciseId = UUID.randomUUID().toString()
                val exerciseInfo = ExerciseEntity(selectedDate,exerciseName, exerciseType, exerciseId)
                insertExercise(exerciseInfo)
                notifyDataSetChanged()
                dialog.dismiss()

                // SelectExerciseActivity를 종료하고 SelectedActivity로 이동

                val intent = Intent(context, SelectedDateActivity::class.java)
                intent.putExtra("selectedDate", selectedDate)
                context.startActivity(intent)
                (context as SelectExerciseActivity).finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            } .create().apply {
                // 모서리 둥글게
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                window?.setBackgroundDrawableResource(R.drawable.bg_dialog_rounded)}.show()

    }


    fun insertExercise(entity: ExerciseEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            exerciseDAO.insert(entity)
        }
    }


}

