package luyao.sample.mergeadapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import luyao.sample.mergeadapter.data.LoadState
import luyao.sample.mergeadapter.databinding.ItemStateBinding
import kotlin.math.acos

class FootAdapter : ListAdapter<LoadState, StateViewHolder>(StateDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        return StateViewHolder(
            ItemStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class StateViewHolder(private val binding: ItemStateBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(state: LoadState) {
        when (state) {
            LoadState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.stateText.visibility = View.GONE
            }
            LoadState.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.stateText.visibility = View.GONE
            }
            LoadState.End -> {
                binding.progressBar.visibility = View.GONE
                binding.stateText.visibility = View.VISIBLE
                binding.stateText.text = "No more students !"
            }
        }
    }
}

class StateDiffCallBack : DiffUtil.ItemCallback<LoadState>(){
    override fun areItemsTheSame(oldItem: LoadState, newItem: LoadState): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LoadState, newItem: LoadState): Boolean {
       return oldItem == newItem
    }

}