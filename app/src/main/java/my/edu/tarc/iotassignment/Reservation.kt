package my.edu.tarc.iotassignment


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class Reservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        //secondary firebase : sir firebase
        val secondary = FirebaseDatabase.getInstance("https://bait2123-202010-03.firebaseio.com/")

        //primary firebase : our firebase
        val primary: FirebaseDatabase = FirebaseDatabase.getInstance("https://solenoid-lock-f65e8.firebaseio.com/")
        val roomNo: DatabaseReference = primary.getReference("Room")

        //accessingUI element
        val reserveButton: Button = findViewById(R.id.buttonReserve)
        val codeOTP: TextView = findViewById(R.id.code)
        val textViewRoomNo : TextView = findViewById(R.id.textViewRoomNo)
        val textViewNoOfPax : TextView = findViewById(R.id.textViewNoOfPax)
        //initialize code variable
        var code:Int = 0 ;
        var pcode:String ="";


        var selection: String? = intent.getStringExtra("selection")


            roomNo.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (selection == "1")
                    {
                        var roomNo:String = dataSnapshot.child("Room1").child("roomNo").getValue().toString()
                        var noOfPax:String = dataSnapshot.child("Room1").child("noOfPax").getValue().toString()
                        textViewRoomNo.text = roomNo
                        textViewNoOfPax.text = noOfPax
                    }
                    else if(selection == "2")
                    {
                        var roomNo:String = dataSnapshot.child("Room2").child("roomNo").getValue().toString()
                        var noOfPax:String = dataSnapshot.child("Room2").child("noOfPax").getValue().toString()
                        textViewRoomNo.text = roomNo
                        textViewNoOfPax.text = noOfPax
                    }
                    else if(selection == "3")
                    {
                        var roomNo:String = dataSnapshot.child("Room3").child("roomNo").getValue().toString()
                        var noOfPax:String = dataSnapshot.child("Room3").child("noOfPax").getValue().toString()
                        textViewRoomNo.text = roomNo
                        textViewNoOfPax.text = noOfPax
                    }
                    else if(selection == "4")
                    {
                        var roomNo:String = dataSnapshot.child("Room4").child("roomNo").getValue().toString()
                        var noOfPax:String = dataSnapshot.child("Room4").child("noOfPax").getValue().toString()
                        textViewRoomNo.text = roomNo
                        textViewNoOfPax.text = noOfPax
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })



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

            val codePin = primary.getReference("DoorPIN")
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
