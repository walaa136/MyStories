package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.mystory.R

class AddStoryActivity : AppCompatActivity() {

    private var editTextTitle: EditText? = null
    private var editTextSubTitle: EditText? = null
    private var editTextDescription: EditText? = null
    private var buttonAddStory: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_story)


        connectViews()
        fieldValidation()
    }

    private fun connectViews() {
        editTextTitle = findViewById(R.id.etTitle)
        editTextSubTitle = findViewById(R.id.etSubTitle)
        editTextDescription = findViewById(R.id.etDescription)
        buttonAddStory = findViewById(R.id.btnAdd)
    }

    private fun fieldValidation() {
        buttonAddStory?.setOnClickListener {
            val title = editTextTitle?.text.toString()
            val subTitle = editTextSubTitle?.text.toString()
            val desc = editTextDescription?.text.toString()



            when {
                title.isEmpty() -> {
                    editTextTitle?.error = getString(R.string.enter_title)

                }
                subTitle.isEmpty() -> {
                    editTextSubTitle?.error = getString(R.string.enter_sub_title)
                }
                desc.isEmpty() -> {
                    editTextDescription?.error = getString(R.string.enter_description)

                }
                else -> {
                    this.finish()
                    val i = Intent(this, MainActivity::class.java)
                    i.putExtra("title", title)
                    i.putExtra("subtitle", subTitle)
                    i.putExtra("desc", desc)
                    startActivity(i)

                }
            }
        }

    }
}
