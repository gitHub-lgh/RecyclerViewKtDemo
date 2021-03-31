package com.example.recyclerviewkt

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MyItemTouchCallback(val mCallback:ItemTouchResultCallback) : ItemTouchHelper.Callback(){

    /**
     *  ItemTouchHelper.UP    向上
     *  ItemTouchHelper.DOWN  向下
     *  ItemTouchHelper.LEFT  向左
     *  ItemTouchHelper.RIGHT 向右
     *  ItemTouchHelper.START 从右向左
     *  ItemTouchHelper.END   从左向右
     *  如果某个值传0，表示不触发该操作，
     *  此处设置支持上下拖拽和向右滑动
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) = makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.END)


    /**
     * 拖拽操作的回调
     * @param viewHolder 被拖动的ViewHolder
     * @param target     目标位置的ViewHolder
     * @return 如果item发生位置交换，返回true，否则返回false
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        mCallback.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
        return true
    }

    /**
     * 滑动操作的回调
     * @param viewHolder 滑动的ViewHolder
     * @param direction  滑动的方向
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mCallback.onItemDelete(viewHolder.adapterPosition)
    }

    interface ItemTouchResultCallback{
        //拖拽移动
        fun onItemMove(fromPosition:Int,toPosition:Int)
        //滑动删除
        fun onItemDelete(position:Int)
    }
}