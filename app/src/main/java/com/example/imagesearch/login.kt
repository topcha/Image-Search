package com.example.imagesearch

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

   private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        auth = Firebase.auth

        supportActionBar?.hide()




        signIn.setOnClickListener(){

if(emaili.text.isNotEmpty() && passwordi.text.isNotEmpty()){


    auth.signInWithEmailAndPassword(emaili.text.toString(), passwordi.text.toString())
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
         val openApp = Intent(this,MainActivity::class.java)
                startActivity(openApp)


            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()

            }

            // ...
        }





}else{

}




        }




        register.setOnClickListener(){

    val intentt = Intent(this, registration::class.java)
    startActivity(intentt)


}


    }
}