package com.example.morethanyesterdayv2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.morethanyesterdayv2.AboutRoom.RoomHelper
import com.example.morethanyesterdayv2.AboutRoom.RoomMemo
import com.example.morethanyesterdayv2.AboutRoom.RoomMemoDAO
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.viewmodel.ExerciseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExerciseListAdapter(private val context: Context) :
    RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    lateinit var helper: RoomHelper
    val memoList = mutableListOf<RoomMemo>()
    lateinit var memoDAO: RoomMemoDAO

    var datas = mutableListOf<ExerciseData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: ExerciseData, pos: Int)
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

        fun bind(item: ExerciseData) {
            txtName.text = item.name
            txtType.text = item.type
            var seletedDate = "1월 24일"

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, item, pos)
                    createDeleteAlterDialog(pos,txtName.text.toString(),seletedDate)
                }
            }


        }
    }
    fun createDeleteAlterDialog(currentItem: Int, exerciseName:String, seletedDate:String){
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
                memoDAO = helper.roomMemoDao()
                val memo = RoomMemo(exerciseName)
                insertMemo(memo)



                notifyDataSetChanged()
                dialog.dismiss()
            })
            .setNegativeButton("No",{
                    dialog, _ -> dialog.dismiss()
            }).create().show()
    }

    fun insertMemo(memo: RoomMemo) {
        CoroutineScope(Dispatchers.IO).launch {
            memoDAO.insert(memo)
            refreshAdapter()
        }
    }
    fun refreshAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
            memoList.clear()
            memoList.addAll(memoDAO.getAll())


        }
    }
//    private fun addExerciseDialog(currentItem: Int, exerciseName:String) {
//
//        // custom_dialog를 뷰 객체로 반환
//        val dialogView = LayoutInflater.from(context).inflate(R.layout.custom_mylike_dialog, null)
//        // 대화상자 생성
//        val builder = android.app.AlertDialog.Builder(context)
//            .setView(dialogView)
//            .setTitle("$exerciseName")
//            .setMessage("해당 운동을 삭제하시겠습니까?")
//        // 대화상자 띄움
//        val alertDialog = builder.show()
//
//
//    }


}

