package com.example.week6day1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
//                    الخاصية الثانية                        الخاصية الاولى
class CustomAdapter(val storiesList:ArrayList<Story>,val context: Context )
    :RecyclerView.Adapter<CustomAdapter.DataHolder>(){

    //  الدالة الخاصة عندي بانشاء الخلية الاساسية
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {

       val dataHolder = DataHolder(LayoutInflater.from(parent.context).inflate(
           // بما انه مانرث من كلاس الاكتيفيتي قلنا له راح نستخدم الاداة اللي تستخدم لانشاء الواجهات اللي هي الانفليتير
           //inflater  هو اللي راح ينشئ الواجهة لان هذا الكلاس ليس اكتيفيتي مافيه طريقة اعرضها الا باستخدام هذا الكلاس
           R.layout.custom_layout,parent,false))
        return dataHolder

    }

    //
    override fun onBindViewHolder(holder: DataHolder, position: Int) {// تحددلي في كل خلية ايش لازم ينعرض
        val story = storiesList[position] // كل ستوري ليست عنده قصة مختلفة
        holder.storyTitle.text = story.title
        holder.storySubTitle.text = story.subtitle
        holder.storyLetter.text = story.title.first().toString()


        generateColors(holder, position) // هذي الدالة تاخذ هولدير وبوسيشن
        //itemview تختصر جميع العناصر داخل الخلية
        // التفاعل مع الريسايكل
        holder.itemView.setOnClickListener {


        val i = Intent(context, StoryDetailsActivity::class.java)//context اللي مررناه
            // الاشياء اللي نعرضها في الواجهة الجديدة
        i.putExtra("title", story.title)
        i.putExtra("desc", story.description)
        context.startActivity(i)
    }

    }

    //
    private fun generateColors(holder: DataHolder,position: Int){// الدالة تنشئ الالوان بشكل تلقائي
        val r = Random() //   يعطينا الوان عشوائية من رقم عشوائي كلاس راندوم
        val red = r.nextInt(255+position) // 255 اعلى قيمة في الالوان اخر قيمة في الهيكساديسيمال
        val green = r.nextInt(255-position+1) //nextInt  تعطينا رقم عشوائي اللي موجود في الراندوم
        val blue = r.nextInt(255+position+1)

        holder.cardViewLetter.setCardBackgroundColor(Color.rgb(red,green,blue))// استدعاء الدائرة

    }

    // تحديد عدد الخلايا التي سيتم عرضها
    override fun getItemCount(): Int {
       return storiesList.count()
    }
// كلاس يرث من ريسايكل فيو
    class DataHolder(item: View):RecyclerView.ViewHolder(item){ // كلاس داخلي يربط لي العناصر
    //  تعرض عناصر الخلايا
        val storyTitle:TextView = item.findViewById(R.id.tvTitle)// item خاصية الخلايا
        val storySubTitle:TextView = item.findViewById(R.id .tvSubtitle) //R  تدل على اللي موجودة في الريسورس res
        val storyLetter:TextView = item.findViewById(R.id .tvStoryLetter)
        val cardViewLetter:CardView = item.findViewById(R.id .circle)


    }
}