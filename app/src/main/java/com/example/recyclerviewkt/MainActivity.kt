package com.example.recyclerviewkt

import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewBaseAdapter.OnItemClickListener{

    private val fruitList = ArrayList<Fruit>()
    private lateinit var mAdapter: RecyclerViewBaseAdapter
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initListener();
    }

    private fun initListener() {
        //设置上拉刷新和下拉加载更多时展示的view
        refreshLayout.setRefreshHeader(ClassicsHeader(this))
        refreshLayout.setRefreshFooter(ClassicsFooter(this))

        //下拉刷新
        refreshLayout.setOnRefreshListener {
            fruitList[0].name = "cherry"
            fruitList[0].imageId = R.drawable.cherry_pic
            mAdapter.notifyDataSetChanged()
            it.finishRefresh(2000)
        }
        //上拉加载更多
        refreshLayout.setOnLoadMoreListener {
            mAdapter.add(0,Fruit("cherry",R.drawable.cherry_pic))
            recyclerView.scrollToPosition(fruitList.size-1)
            it.finishLoadMore(2000)
        }

        addBtn.setOnClickListener {
            val fruit = Fruit("cherry",R.drawable.cherry_pic)
            mAdapter.add(0, fruit)
            recyclerView.scrollToPosition(0)

        }

        updateBtn.setOnClickListener {
            mAdapter.update(0)
        }

        removeBtn.setOnClickListener {
            mAdapter.remove(0)
        }

        clearBtn.setOnClickListener {
            mAdapter.clear()
        }
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        repeat(2) {
            fruitList.add(Fruit("apple", R.drawable.apple_pic))
            fruitList.add(Fruit("banana", R.drawable.banana_pic))
            fruitList.add(Fruit("orange", R.drawable.orange_pic))
            fruitList.add(Fruit("watermelon", R.drawable.watermelon_pic))
            fruitList.add(Fruit("pear", R.drawable.pear_pic))
            fruitList.add(Fruit("strawberry", R.drawable.strawberry_pic))
            fruitList.add(Fruit("mango", R.drawable.mango_pic))
            fruitList.add(Fruit("grape", R.drawable.grape_pic))
            fruitList.add(Fruit("pineapple", R.drawable.pineapple_pic))
        }
        showList(true)
    }

    /**
     * 加载menu布局
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * menu条目的选择事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.listVertical -> showList(true)
            R.id.listHorizontal -> showList(false)
            R.id.gridVertical -> showGrid(true)
            R.id.gridHorizontal -> showGrid(false)
            R.id.staggerVertical -> showStagger(true)
            R.id.staggerHorizontal -> showStagger(false)
            R.id.moreStyleView -> showMoreTypeView()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showMoreTypeView() {
        val intent = Intent(this,MoreTypeActivity::class.java)
        startActivity(intent)
    }

    /**
     * 线性布局
     */
    private fun showList(isVertical: Boolean) {
        val linearLayout = LinearLayoutManager(this)
        if (isVertical) {
            linearLayout.orientation = LinearLayoutManager.VERTICAL
        } else {
            linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        }
        recyclerView.layoutManager = linearLayout
        mAdapter = FruitListAdapter(fruitList)
        mAdapter.setOnItemClickListener(this)
        recyclerView.adapter = mAdapter

        val itemTouchHelperCallback = MyItemTouchCallback(mAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    /**
     *网格布局
     */
    private fun showGrid(isVertical: Boolean) {
        val gridLayout = GridLayoutManager(this, 3)
        gridLayout.orientation =
            if (isVertical) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = gridLayout
        Log.d(TAG, "orientation : " + gridLayout.orientation)
        mAdapter = FruitGridAdapter(fruitList)
        mAdapter.setOnItemClickListener(this)
        recyclerView.adapter = mAdapter
    }

    /**
     * 瀑布流布局
     */
    private fun showStagger(isVertical: Boolean) {
        val orientation =
            if (isVertical) StaggeredGridLayoutManager.VERTICAL else StaggeredGridLayoutManager.HORIZONTAL
        val staggerLayout = StaggeredGridLayoutManager(3, orientation)
        recyclerView.layoutManager = staggerLayout
        mAdapter = FruitStaggerAdapter(fruitList)
        mAdapter.setOnItemClickListener(this)
        recyclerView.adapter = mAdapter
    }

    override fun onItemClick(position: Int) {
        //item点击事件处理
        Toast.makeText(this, "你点击了" + fruitList[position].name + "条目", Toast.LENGTH_SHORT).show()
    }

}