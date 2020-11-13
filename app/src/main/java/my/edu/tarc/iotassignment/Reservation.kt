package my.edu.tarc.iotassignment


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class Reservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)


        //accessingUI element
        val reserveButton: Button = findViewById(R.id.buttonReserve)
        val codeOTP: TextView = findViewById(R.id.code)
        //initialize code variable
        var code:Int = 0 ;
        var pcode:String ="";

        reserveButton.setOnClickListener {
            //allow user to press once only
            reserveButton.isEnabled = false;

            //random function fo 6 digit pin
            code = (Math.random()*1000000).toInt()
            pcode = String.format("%06d", code);
            codeOTP.text = "Your room pin is $pcode"

            //the code is then saved to the firebase
            // Write a message to the database

            // Write a message to the database
            val database = FirebaseDatabase.getInstance()
            val codePin = database.getReference("DoorPIN")
            codePin.setValue(code.toString())

            //changing message in the button
            reserveButton.text = "SUCCESSFULLY BOOKED"
        }

        //open the door action
        val door: TextView = findViewById(R.id.openthedoor)
        door.setOnClickListener {
            startActivity(Intent(this, SolenoidDoor::class.java))
        }



    }
}
