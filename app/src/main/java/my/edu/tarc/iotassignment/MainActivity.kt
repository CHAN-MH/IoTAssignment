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
        auth = FirebaseAuth.getInstance()

        val buttonLogin : Button = findViewById(R.id.buttonLogin)
        email1 = findViewById(R.id.editTextEmail)
        password1 = findViewById(R.id.editTextPassword)

        //Creating data for room1
        var roomNo : String = "R01"
        var noOfPax : Int = 2
        var status : Boolean = true
        //true = available, false = occupied
        var database = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("Room").child("Room1")
        val room1 = Room()
        room1.setRoomNo(roomNo)
        room1.setNoOfPax(noOfPax)
        room1.setStatus(status)
        myRef.setValue(room1);

        //Creating data for room2
        roomNo  = "R02"
        noOfPax  = 2
        status  = false
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Room").child("Room2")
        val room2 = Room()
        room2.setRoomNo(roomNo)
        room2.setNoOfPax(noOfPax)
        room2.setStatus(status)
        myRef.setValue(room2);

        //Creating data for room3
        roomNo  = "R03"
        noOfPax  = 4
        status  = true
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Room").child("Room3")
        val room3 = Room()
        room3.setRoomNo(roomNo)
        room3.setNoOfPax(noOfPax)
        room3.setStatus(status)
        myRef.setValue(room3);

        //Creating data for room4
        roomNo  = "R04"
        noOfPax  = 4
        status  = false
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Room").child("Room4")
        val room4 = Room()
        room4.setRoomNo(roomNo)
        room4.setNoOfPax(noOfPax)
        room4.setStatus(status)
        myRef.setValue(room4);

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