package com.jetpack.first.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.first.databinding.ItemShoeBinding
import com.jetpack.first.db.data.Shoe

class ShoeAdapter constructor(val context: Context) :
    PagedListAdapter<Shoe, ShoeAdapter.ViewHolder>(ShoeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemShoeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoe = getItem(position)
        holder.apply {
            bind(onCreateListener(shoe!!.id), shoe)
            itemView.tag = shoe
        }
    }

    /**
     * Holder的点击事件
     */
    private fun onCreateListener(id: Long): View.OnClickListener {
        return View.OnClickListener {
            // TODO
        }
    }

    class ViewHolder(private val binding: ItemShoeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Shoe) {
            binding.apply {
                this.listener = listener
                this.shoe = item
                executePendingBindings()
            }
        }
    }
}

private class ShoeDiffCallback : DiffUtil.ItemCallback<Shoe>() {

    override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem == newItem
    }

}