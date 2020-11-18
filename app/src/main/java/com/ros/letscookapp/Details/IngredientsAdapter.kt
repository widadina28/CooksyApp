package com.ros.letscookapp.Details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ros.letscookapp.R
import kotlinx.android.synthetic.main.item_ingredients.view.*

class IngredientsAdapter(var ingredient: List<IngredientsModel>) : RecyclerView.Adapter<IngredientsAdapter.Holderngredients>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsAdapter.Holderngredients {
        return Holderngredients(LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false))
    }

    override fun getItemCount(): Int = ingredient.size

    override fun onBindViewHolder(holder: IngredientsAdapter.Holderngredients, position: Int) {
        val ingredient = ingredient[position]
        holder.view.tv_ingredients.text = ingredient.ingredient
    }

    class Holderngredients(val view: View) : RecyclerView.ViewHolder(view)
}