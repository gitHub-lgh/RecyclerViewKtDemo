package com.example.recyclerviewkt

import android.view.View
import android.view.ViewGroup

class FruitStaggerAdapter(fruitList:ArrayList<Fruit>):RecyclerViewBaseAdapter(fruitList) {
    override fun getSubView(parent: ViewGroup, viewType: Int): View {
        return View.inflate(parent.context,R.layout.item_stagger_view,null)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fruitName.text = getRandomLengthString(fruitList[position].name)
        holder.fruitImage.setImageResource(fruitList[position].imageId)
    }

    private fun getRandomLengthString(str:String):String{
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n){
            builder.append(str)
        }
        return builder.toString()
    }
}