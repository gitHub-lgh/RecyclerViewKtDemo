package com.example.recyclerviewkt

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MoreTypeItemAdapter(val itemList:ArrayList<ItemWithType>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object{
        @JvmStatic
        val TYPE_BIG_IMAGE = 0
        @JvmStatic
        val TYPE_THREE_IMAGE = 1
        @JvmStatic
        val TYPE_RIGHT_IMAGE = 2
    }

    inner class BigImageViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView:ImageView = itemView.findViewById(R.id.fruit_image)
    }

    inner class ThreeImageViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView:ImageView = itemView.findViewById(R.id.fruit_image)
    }

    inner class RightImageViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView:ImageView = itemView.findViewById(R.id.fruit_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_BIG_IMAGE -> {
                BigImageViewHolder(View.inflate(parent.context,R.layout.item_big_image_view,null))
            }
            TYPE_THREE_IMAGE -> {
                val itemView = View.inflate(parent.context,R.layout.item_three_image_view,null)
                ThreeImageViewHolder(itemView)
            }
            TYPE_RIGHT_IMAGE -> {
                val itemView = View.inflate(parent.context,R.layout.item_right_image_view,null)
                RightImageViewHolder(itemView)
            }
            else -> {
                val itemView = View.inflate(parent.context,R.layout.item_right_image_view,null)
                RightImageViewHolder(itemView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imageId = itemList[position].imageId
        when(getItemViewType(position)){
            TYPE_BIG_IMAGE -> {
                (holder as BigImageViewHolder).imageView.setImageResource(imageId)
            }
            TYPE_THREE_IMAGE -> {
                (holder as ThreeImageViewHolder).imageView.setImageResource(imageId)
            }
            TYPE_RIGHT_IMAGE -> {
                (holder as RightImageViewHolder).imageView.setImageResource(imageId)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].type
    }

}