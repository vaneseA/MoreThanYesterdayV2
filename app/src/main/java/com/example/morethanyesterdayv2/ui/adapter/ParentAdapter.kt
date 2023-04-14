package com.example.morethanyesterdayv2.ui.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.databinding.RecordRvItemBinding
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.repository.SelectedDateRepository
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity
import com.example.morethanyesterdayv2.viewmodel.SelectedDateViewModel

class ParentAdapter(
    private val parentList: List<ExerciseEntity>,
    var context: Context,
    application: Application,
    private val viewModel: SelectedDateViewModel,
    private val childList: List<RecordEntity>
) :
    RecyclerView.Adapter<ParentAdapter.Holder>(), AddSetDialogInterface {
    private val selectedRepository: SelectedDateRepository
    private var exerciseDAO: ExerciseDAO
    private var recordDAO: RecordDAO

    init {
        val appDatabase = AppDatabase.getDatabase(context.applicationContext as Application)
        selectedRepository = SelectedDateRepository(application)
        exerciseDAO = appDatabase.exerciseDAO()
        recordDAO = appDatabase.recordDAO()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            RecordRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val parentItem = parentList[position]
        holder.setData(parentItem, position, childList)

    }

    override fun getItemCount(): Int = parentList.size

    inner class Holder(val binding: RecordRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val selectedDateActivity = SelectedDateActivity.getInstance()
        fun setData(exerciseEntity: ExerciseEntity, position: Int, childList: List<RecordEntity>) {
            val exerciseId = exerciseEntity.exerciseId



            binding.totalSetArea.text = "총 ${exerciseEntity.totalSet}set, "
            binding.NameArea.text = exerciseEntity.exerciseName
            binding.TypeArea.text = exerciseEntity.exerciseType
            binding.totalKgArea.text = "총 ${exerciseEntity.totalKg}kg, "
            binding.bestKgArea.text = "최고 ${exerciseEntity.maxKg}kg, "
            binding.totalCountArea.text = "총 ${exerciseEntity.totalCount}회"


            selectedRepository.getExerciseSetListLiveDataFromRecordById(exerciseId)
                .observe(itemView.context as LifecycleOwner) { childExerciseSetList ->
                    binding.nestedRV.adapter = ChildAdapter(childExerciseSetList, viewModel )
                    binding.nestedRV.layoutManager = LinearLayoutManager(context)
                    binding.nestedRV.setHasFixedSize(true)
                }


            binding.addSetBtn.setOnClickListener {
                selectedDateActivity?.showAddSetDialog(
                    position,
                    exerciseEntity,
                    exerciseId,
                    exerciseEntity.selectedDate
                )
            }

            binding.menuBtn.setOnClickListener{
                val popupMenu = PopupMenu(itemView.context, binding.menuBtn)
                popupMenu.menuInflater.inflate(R.menu.menu_popup, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.action_delete -> {

                            selectedDateActivity?.showDeleteDialog(
                                position,
                                exerciseEntity,
                                exerciseId,
                                exerciseEntity.selectedDate,
                                exerciseEntity.exerciseName,
                                exerciseEntity.exerciseType
                            )

                            true
                        }
                        R.id.action_move -> {
                            // 처리할 내용
                            Toast.makeText(
                                itemView.context,
                                "구현 준비 중...",
                                Toast.LENGTH_LONG).show()
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
            }

        }

    }

    override fun onYesButtonClick(id: Int) {
    }

}