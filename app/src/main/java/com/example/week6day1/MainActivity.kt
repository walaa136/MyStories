package com.example.week6day1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var textViewEmail:TextView?= null
    private  var drawerLayout:DrawerLayout?= null
    private var toolbarView:androidx.appcompat.widget.Toolbar?= null
    private var navigationView:NavigationView?= null
    private var recyclerView:RecyclerView?= null
    private var buttonAddStory:FloatingActionButton?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = intent.getStringExtra("email")//Key المفتاح


        connectViews()
        textViewEmail?.text = email
        setSupportActionBar(toolbarView)// اكشن بار اللي احنا سويناها
        supportActionBar?.setDisplayHomeAsUpEnabled(true)// يظهر سهم الى الخلف


        setupDrawer()
         try {
            updateEmailInHeader(email!!)

        } catch (e: NullPointerException){
        }

        drawerClicks()
        openAddStoryActivity()
        displayStories() // نربط الريسايكلر


    }

    private fun updateEmailInHeader(email:String){// استقبل الايميل داخل الواجهة
        val headerView = navigationView?.getHeaderView(0)// يعطيني وصول للناف للايميل
        val textViewEmail = headerView?.findViewById<TextView>(R.id .tvEmail)//احتاج الايميل ابغى احدث قيمته
        textViewEmail?.text = email// اطبع لي الايميل
    }
    private fun setupDrawer(){// يتحكم باغلاق القائمة الجانبية
        val toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()// المزامنة الخاصة بها متى افتح متى اغلق
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // علامة الثلاث خطوط  نحول السهم الى ثلاث خطوط
        return when(item.itemId){
            android.R.id.home -> {
                drawerLayout?.openDrawer(GravityCompat.START)// ينفتح من اليسار
                true
            }
            else -> true
        }
    }


    private fun connectViews(){
        textViewEmail = findViewById(R.id.tvEmail)
        drawerLayout = findViewById(R.id.drawer)
        toolbarView = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navView)
        recyclerView = findViewById(R.id.StoriesRecyclerView)
        buttonAddStory = findViewById(R.id.btnAddStory)
        
    }
    private fun drawerClicks(){
        // تحديد اي عنصر احنا نبغى
        navigationView?.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    drawerLayout?.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.logout ->{
                    finish()
                    val i = Intent(this,LoginActivity::class.java)
                    startActivity(i)
                    true
                }
                else -> true
            }
        }
    }
    private fun openAddStoryActivity(){

        buttonAddStory?.setOnClickListener {
            val i = Intent(this,AddStoryActivity::class.java)
            startActivity(i)

        }

    }
    private fun displayStories(){// تعرض القصص
        val storiesArray = ArrayList<Story>()
        storiesArray.add(
            Story(getString(R.string.story1_title)// ناخذ القصة من السترينق
            ,getString(R.string.story1_subtitle),getString(R.string.story1_desc)))
        storiesArray.add(Story("This is my second story","This is second subtitle"
            ,"Welcome to my story I will show you how I learnt"))
        storiesArray.add(Story("This is my third story","This is third subtitle"
            ,"Welcome to my story I will show you how I learnt"))



        val customAdapter = CustomAdapter(storiesArray,this)// ليش استخدمت الكونتيكست في هذا الكلاس ؟ عشان نعرف احنا في اي واجهة ننتقل من واجهة الاكتيفيتي الى واجهة التفاصيل
        recyclerView?.adapter = customAdapter


        if(intent.getStringExtra("title") != null){
            val title = intent.getStringExtra("title")
            val subTitle = intent.getStringExtra("subtitle")
            val desc = intent.getStringExtra("desc")

            val newStory = Story(title!!,subTitle!!,desc!!)


            storiesArray.add(newStory)

            customAdapter.notifyDataSetChanged()
        }
    }
}