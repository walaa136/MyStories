package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.mystory.R

class LoginActivity : AppCompatActivity() {


    private var editTextUsername: EditText?= null
    private var editTextPassword: EditText?= null
    private var buttonLogin: Button?= null
    private var checkboxView: CheckBox?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        connectViews()
        login()
      // ckeckFields()

    }
    private fun connectViews(){
        editTextUsername = findViewById(R.id.etUsername)
        editTextPassword = findViewById(R.id.etPassword)
        buttonLogin = findViewById(R.id.btnLogin)
        checkboxView = findViewById(R.id.checkbox)
    }
    private fun login() {
        val arr: ArrayList<User> = ArrayList()

        arr.add(User("test@test.com", "1234"))
        arr.add(User("t@gmail.com", "12345"))
        arr.add(User("b@test.com", "123456"))

        buttonLogin?.setOnClickListener {
            val username = editTextUsername?.text.toString()// مدخل بواسطة المستخدم
            val password = editTextPassword?.text.toString()// مدخل بواسطة المستخدم


            val user = User(username, password)
            for (userArray in arr) {
                if (userArray.email == user.email && userArray.password == user.password) {
                    //  Toast.makeText(this,"Welcome ${user.email}", Toast.LENGTH_LONG).show()
                        // توقف اللوقن اكتيفيتي
                    finish()// ننهي صفحة تسجيل الدخول وننقل المستخدم للواجهة الرئيسية
                    val i = Intent(this, MainActivity::class.java)
                    i.putExtra("email", userArray.email)
                    startActivity(i)
                    break
                } else {
                    Toast.makeText(this, "Check your data", Toast.LENGTH_LONG).show()

                }
            }
        }
    }


      private fun ckeckFields(){

        buttonLogin?.setOnClickListener {
       if(editTextUsername?.text?.isEmpty() == true) {
           editTextUsername?.setError("Enter your email")// تعطينا اللون الاحمر في الحقل يعني لازم احط قيمة

      }else if(editTextPassword?.text?.isEmpty() == true) {
          editTextPassword?.error = "Enter your password"

        }

       }

   }

}



