package com.ros.letscookapp.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ros.letscookapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(var datameals: ArrayList<CategoryModel>, var listener: OnAdapterListener) : RecyclerView.Adapter<CategoryAdapter.HolderFood>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderFood {
        return HolderFood(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))

    }

    override fun getItemCount() = datameals.size

    override fun onBindViewHolder(holder: HolderFood, position: Int) {
        val meals = datameals[position]
        Picasso.get().load(meals.image).into(holder.view.img_meals)
        holder.view.meals_name.text= meals.name
        holder.view.img_meals.setOnClickListener {
            listener.onClick(meals)
        }

    }

    class HolderFood(val view: View) : RecyclerView.ViewHolder(view)

    fun addList(newList: List<CategoryModel>) {
        datameals.clear()
        datameals.addAll(newList)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(category: CategoryModel)
    }
}