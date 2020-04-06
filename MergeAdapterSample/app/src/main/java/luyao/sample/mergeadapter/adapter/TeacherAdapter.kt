package luyao.sample.mergeadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import luyao.sample.mergeadapter.data.Teacher
import luyao.sample.mergeadapter.databinding.ItemTeacherBinding

/**
 * Created by luyao
 * on 2020/4/3 13:18
 */
class TeacherAdapter : ListAdapter<Teacher, TeacherViewHolder>(TeacherDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        return TeacherViewHolder(
            ItemTeacherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class TeacherViewHolder(private val binding: ItemTeacherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(teacher: Teacher) {
        binding.teacherName.text = teacher.name
    }
}

class TeacherDiffCallback : DiffUtil.ItemCallback<Teacher>() {
    override fun areItemsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
        return oldItem.name == newItem.name
    }
}