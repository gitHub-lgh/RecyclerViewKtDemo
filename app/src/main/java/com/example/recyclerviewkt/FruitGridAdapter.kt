package com.example.recyclerviewkt

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FruitGridAdapter(fruitList:ArrayList<Fruit>) : RecyclerViewBaseAdapter(fruitList) {
    override fun getSubView(parent: ViewGroup, viewType: Int): View {
        return View.inflate(parent.context,R.layout.item_grid_view,null)
    }
}