package com.example.recyclerviewkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FruitListAdapter(fruitList: ArrayList<Fruit>) : RecyclerViewBaseAdapter(fruitList) {

    override fun getSubView(parent: ViewGroup, viewType: Int) = View.inflate(parent.context, R.layout.item_list_view, null)
}
