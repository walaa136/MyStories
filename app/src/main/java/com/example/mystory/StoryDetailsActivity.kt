package com.example.mystory

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.mystory.R
import java.util.*

class StoryDetailsActivity : AppCompatActivity() {

    private var toolbarView:Toolbar?= null
    private var textViewStoryDesc:TextView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_details)


        connectViews()
        setSupportActionBar(toolbarView) // اظهر التول بار
        supportActionBar?.setDisplayHomeAsUpEnabled(true)// الدالة تعرض زر الرجوع

        //Receive Variables
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("desc")

        supportActionBar?.title  = title// نعرض التايتل
        //هذي الدالة تسمح لي استمع للضغطة اللي ضغطتها على النافيجيشن هو زر الرجوع
        toolbarView?.setNavigationOnClickListener {
            onBackPressed()//  عشان نرجه للصفحة اللي قبل الدالة تستدعى عند الضغط على زر الرجوع على الجوال
        }


        textViewStoryDesc?.text = description //  عرض تفاصيل القصة
        textViewStoryDesc?.movementMethod = ScrollingMovementMethod()  //  احدد الموفينق الخاص بالتكست
        // ميزة السكرول اذا كان النص يخرج عن الصفحة
    }




    private fun connectViews(){
        toolbarView = findViewById(R.id.toolbar)
        textViewStoryDesc = findViewById(R.id.tvDesc)

    }


}