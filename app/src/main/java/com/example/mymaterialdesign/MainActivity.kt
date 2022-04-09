package com.example.mymaterialdesign

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mymaterialdesign.Adapter.FruitAdapter
import com.example.mymaterialdesign.Data.Fruits
import com.example.mymaterialdesign.Utils.showSnackBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val fruits = mutableListOf(
        Fruits("Apple", R.drawable.apple),
        Fruits("Banana", R.drawable.banana),
        Fruits("Orange", R.drawable.orange),
        Fruits("Watermelon", R.drawable.watermelon),
        Fruits("Pear", R.drawable.pear),
        Fruits("Pineapple", R.drawable.pineapple),
        Fruits("Strawberry", R.drawable.strawberry),
        Fruits("Mango", R.drawable.mango),
        Fruits("Cherry", R.drawable.cherry),
        Fruits("Grape", R.drawable.grape)
    )

    private val fruitList = ArrayList<Fruits>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        findViewById<NavigationView>(R.id.navView).setCheckedItem(R.id.navCall)
        findViewById<NavigationView>(R.id.navView).setNavigationItemSelectedListener {
            findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawers()
            true
        }
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            it.showSnackBar("Data deleted", actionText = "Undo") {
                "Data restored".showToast()
            }
        }
        initFruits()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fruitAdapter = FruitAdapter(this, fruitList)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = fruitAdapter
        findViewById<SwipeRefreshLayout>(R.id.swipeRefresh).setColorSchemeResources(R.color.purple_500)
        findViewById<SwipeRefreshLayout>(R.id.swipeRefresh).setOnRefreshListener {
            refreshFruits(fruitAdapter)
        }
    }

    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            Thread.sleep(1000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                findViewById<SwipeRefreshLayout>(R.id.swipeRefresh).isRefreshing = false
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> "you click the backup".showToast()
            R.id.delete -> "you click the delete".showToast()
            R.id.settings -> "you click the settings".showToast()
            android.R.id.home -> findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(
                GravityCompat.START
            )
        }
        return true
    }
}