package my.edu.tarc.iotassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    //Global variable
    private lateinit var auth: FirebaseAuth
    private lateinit var email1 : TextView
    private lateinit var password1 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //connect to teacher firebase
        //val secondary = FirebaseDatabase.getInstance("https://bait2123-202010-03.firebaseio.com/")

        //connect to personal database
        auth = FirebaseAuth.getInstance()

        val buttonLogin : Button = findViewById(R.id.buttonLogin)
        email1 = findViewById(R.id.editTextEmail)
        password1 = findViewById(R.id.editTextPassword)

        //start of login button
        buttonLogin.setOnClickListener() {
            val email = email1.text.toString()
            val password = password1.text.toString()
            var loginStatus = "true"

            //login validation
            if(email1.text.toString().isEmpty())
            {
                email1.error = "Please enter your email"
                loginStatus = "false"
            }
            if(password1.text.toString().isEmpty())
            {
                password1.error = "Password is required"
                loginStatus = "false"
            }
            if(loginStatus == "true")
            {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success direct user to reservation page
                                startActivity(Intent(this, DisplayRoom::class.java))
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(baseContext, "Login failed. Please try again", Toast.LENGTH_SHORT).show()
                            }
                        }
            }
        }//end of login button

    }
}