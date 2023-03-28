package com.example.morethanyesterdayv2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.morethanyesterdayv2.aboutRoom.RoomHelper
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.ui.activity.SelectExerciseActivity
import com.example.morethanyesterdayv2.data.ExerciseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseListAdapter(private val context: Context) :
    RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    lateinit var helper: RoomHelper
    val exerciseList = mutableListOf<ExerciseEntity>()
    lateinit var exerciseDAO: ExerciseDAO

    var datas = mutableListOf<ExerciseData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: ExerciseData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

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

        fun bind(item: ExerciseData) {
            txtName.text = item.name
            txtType.text = item.type
            var selectedDate = "1월 24일"
            var exerciseType = item.type

            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, item, position)
                    addExerciseDialog(position,txtName.text.toString(),exerciseType,selectedDate,)
                }
            }


        }
    }
    fun addExerciseDialog(position: Int, exerciseName:String, exerciseType:String, seletedDate:String){
        AlertDialog.Builder(context)
            .setTitle("$seletedDate")
            .setMessage(
            "$exerciseName "
                +"을/를 추가하시겠습니까?")
            .setPositiveButton("Yes", {
                    dialog , _ ->
                helper =
                    Room.databaseBuilder(context, RoomHelper::class.java, "room_db")
                        .build()
                exerciseDAO = helper.exerciseDAO()
                val exerciseInfo = ExerciseEntity(exerciseName,exerciseType)
                insertExercise(exerciseInfo)
                notifyDataSetChanged()
                dialog.dismiss()
                // 현재 액티비티를 종료하고 이전 액티비티로 이동하는 코드
                (context as SelectExerciseActivity).finish()
            })
            .setNegativeButton("No",{
                    dialog, _ -> dialog.dismiss()
            }).create().show()
    }




    fun insertExercise(entity: ExerciseEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            exerciseDAO.insert(entity)
            refreshAdapter()
        }
    }
    fun refreshAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
            exerciseList.clear()
            exerciseList.addAll(exerciseDAO.getAll())


        }
    }


}

