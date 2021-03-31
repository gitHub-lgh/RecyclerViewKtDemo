package com.example.recyclerviewkt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.more_type_list_activity.*
import kotlin.random.Random

class MoreTypeActivity : AppCompatActivity(){

    private val mDatas = ArrayList<ItemWithType>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_type_list_activity)
        initData()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = MoreTypeItemAdapter(mDatas)
        recyclerView.adapter = adapter
    }

    private fun initData() {
        var n:Int
        repeat(3){
            n = (0..2).random()
            mDatas.add(ItemWithType(R.drawable.apple_pic,n))
            n = (0..2).random()
            mDatas.add(ItemWithType(R.drawable.pineapple_pic,n))
            n = (0..2).random()
            mDatas.add(ItemWithType(R.drawable.mango_pic,n))
        }
    }
}