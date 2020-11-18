package com.ros.letscookapp.Details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ros.letscookapp.R
import kotlinx.android.synthetic.main.item_tags.view.*

class TagsAdapter(var tags: List<TagsModel>) : RecyclerView.Adapter<TagsAdapter.HolderTags>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsAdapter.HolderTags {
        return HolderTags(LayoutInflater.from(parent.context).inflate(R.layout.item_tags, parent, false))
    }

    override fun getItemCount(): Int = tags.size

    override fun onBindViewHolder(holder: TagsAdapter.HolderTags, position: Int) {
        val tags = tags[position]
        holder.view.tv_tags.text = tags.tags
    }

    class HolderTags(val view: View) : RecyclerView.ViewHolder(view)

}