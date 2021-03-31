package com.example.recyclerviewkt

import android.telecom.Connection
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

abstract class RecyclerViewBaseAdapter(val fruitList:ArrayList<Fruit>) : RecyclerView.Adapter<RecyclerViewBaseAdapter.MyViewHolder>(),
    MyItemTouchCallback.ItemTouchResultCallback {

    private val TAG = "RecyclerViewBaseAdapter"
    private lateinit var mListener: OnItemClickListener

    abstract fun getSubView(parent: ViewGroup, viewType: Int):View

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val fruitName : TextView = itemView.findViewById(R.id.fruit_name)
        val fruitImage : ImageView = itemView.findViewById(R.id.fruit_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = getSubView(parent,viewType)
        val viewHolder = MyViewHolder(itemView)
        viewHolder.itemView.setOnClickListener {
            //整个item的点击事件处理
            //在这里直接处理或通知到外面处理
            val position = viewHolder.adapterPosition
            //Toast.makeText(parent.context,"你点击了" + fruitList[position].name + "条目",Toast.LENGTH_SHORT).show()
            mListener.onItemClick(position)
        }
        viewHolder.fruitImage.setOnClickListener {
            //图片的点击事件处理
            val position = viewHolder.adapterPosition
            //Log.d(TAG,"position : " + position)
            Toast.makeText(parent.context,"你点击了" + fruitList[position].name + "图片",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun getItemCount() = fruitList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit:Fruit = fruitList[position]
        holder.fruitName.text = fruit.name
        holder.fruitImage.setImageResource(fruit.imageId)
    }

    /**
     * 增加条目
     */
    fun add(position: Int,fruit: Fruit){
        fruitList.add(position,fruit)
        notifyItemInserted(position)
        //notifyDataSetChanged()
    }

    /**
     * 更新条目
     */
    fun update(position: Int){
        fruitList[position].name = "这是经过修改的数据"
        fruitList[position].imageId = R.drawable.grape_pic
        notifyItemChanged(position)
    }

    /**
     * 删除条目
     */
    fun remove(position: Int){
        fruitList.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * 清空条目
     */
    fun clear(){
        fruitList.clear()
        notifyDataSetChanged()
    }

    /**
     * 注册item点击事件
     */
    fun setOnItemClickListener(listener:OnItemClickListener){
        this.mListener = listener
    }

    /**
     * item点击事件的回调接口
     */
    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }

    /**
     * 拖拽移动item
     */
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(fruitList,fromPosition,toPosition)
        notifyItemMoved(fromPosition,toPosition)
    }

    /**
     * 向右滑动删除item
     */
    override fun onItemDelete(position: Int) {
        fruitList.removeAt(position)
        notifyItemRemoved(position)
    }
}
