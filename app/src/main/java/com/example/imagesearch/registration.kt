package com.example.imagesearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registration.*

class registration : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar?.hide()

        // Initialize Firebase Auth


        auth = Firebase.auth

    logIn.setOnClickListener(){

        val intent = Intent(this,login::class.java)
        startActivity(intent)

    }


        signUp.setOnClickListener(){
        if(pass.text.isNotEmpty() && email.text.isNotEmpty() && repeatPass.text.isNotEmpty()){


            if(pass.text.toString() == repeatPass.text.toString()){


                auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val openApp = Intent(this,MainActivity::class.java)
                            startActivity(openApp)
                        } else {


                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                        }


                    }


            }else{
                Toast.makeText(this,"passwords are not the same ",Toast.LENGTH_SHORT).show()
            }


        }else{


            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
        }


        }


    }
}