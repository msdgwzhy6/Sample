package luyao.sample.mergeadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import luyao.sample.mergeadapter.data.Student
import luyao.sample.mergeadapter.databinding.ItemStudentBinding

/**
 * Created by luyao
 * on 2020/4/3 13:31
 */
class StudentAdapter : ListAdapter<Student, StudentViewHolder>(StudentDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            ItemStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
       holder.bind(getItem(position))
    }
}

class StudentViewHolder(private val binding: ItemStudentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(student: Student) {
        binding.studentIcon.setImageResource(student.iconId)
        binding.studentName.text = student.name
    }
}

class StudentDiffCallBack : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.iconId == newItem.iconId &&
                oldItem.name == newItem.name
    }
}