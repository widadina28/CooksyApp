package com.ros.letscookapp.Meals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ros.letscookapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_meals.view.*

class MealsAdapter(var datameals: ArrayList<MealsModel>, var listener: OnAdapterListenerMeals) :
RecyclerView.Adapter<MealsAdapter.HolderMeals>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsAdapter.HolderMeals {
        return HolderMeals(
            LayoutInflater.from(parent.context).inflate(R.layout.item_meals, parent, false)
        )
    }

    override fun getItemCount() = datameals.size

    override fun onBindViewHolder(holder: MealsAdapter.HolderMeals, position: Int) {
        val meals = datameals[position]
        Picasso.get().load(meals.image).into(holder.view.iv_meals)
        holder.view.meals_name2.text = meals.mealName
        holder.view.cv_meals.setOnClickListener {
            listener.onClick(meals)
        }
    }

    interface OnAdapterListenerMeals {
        fun onClick(meals: MealsModel)
    }

    fun addList(newList: List<MealsModel>) {
        datameals.clear()
        datameals.addAll(newList)
        notifyDataSetChanged()
    }

    class HolderMeals(val view: View) : RecyclerView.ViewHolder(view)
}