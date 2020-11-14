package my.edu.tarc.iotassignment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DisplayRoom : AppCompatActivity() {
    var myref = FirebaseDatabase.getInstance().getReference("Room")
    var roomNo : String = ""
    var status : Boolean = true
    lateinit var dialog : Dialog
    lateinit var dataSnapshot : DataSnapshot
    var availability : Int = 1
    //internal val intent = Intent(this, Reservation::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_room)

        //Link UI to program
        val textViewRoom1: TextView = findViewById(R.id.textViewRoom1)
        val textViewRoom2: TextView = findViewById(R.id.textViewRoom2)
        val textViewRoom3: TextView = findViewById(R.id.textViewRoom3)
        val textViewRoom4: TextView = findViewById(R.id.textViewRoom4)


        //displaying the rooms status(available or occupied)
        myref.child("Room1").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                roomNo = dataSnapshot.child("roomNo").value.toString()
                textViewRoom1.text = roomNo
                status = dataSnapshot.child("status").value.toString().toBoolean()
                if (status == true) {
                    textViewRoom1.setBackgroundResource(R.color.available_colour)
                    availability = 1
                } else {
                    textViewRoom1.setBackgroundResource(R.color.occupied_colour)
                    availability = 0
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Actions when failed to read value
            }
        })

        myref.child("Room2").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                roomNo = dataSnapshot.child("roomNo").value.toString()
                textViewRoom2.text = roomNo
                status = dataSnapshot.child("status").value.toString().toBoolean()
                if (status == true) {
                    textViewRoom2.setBackgroundResource(R.color.available_colour)
                    availability = 1
                } else {
                    textViewRoom2.setBackgroundResource(R.color.occupied_colour)
                    availability = 0
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Actions when failed to read value
            }
        })

        myref.child("Room3").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                roomNo = dataSnapshot.child("roomNo").value.toString()
                textViewRoom3.text = roomNo
                status = dataSnapshot.child("status").value.toString().toBoolean()
                if (status == true) {
                    textViewRoom3.setBackgroundResource(R.color.available_colour)
                    availability = 1
                } else {
                    textViewRoom3.setBackgroundResource(R.color.occupied_colour)
                    availability = 0
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Actions when failed to read value
            }
        })

        myref.child("Room4").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                roomNo = dataSnapshot.child("roomNo").value.toString()
                textViewRoom4.text = roomNo
                status = dataSnapshot.child("status").value.toString().toBoolean()
                if (status == true) {
                    textViewRoom4.setBackgroundResource(R.color.available_colour)
                    availability = 1
                } else {
                    textViewRoom4.setBackgroundResource(R.color.occupied_colour)
                    availability = 0
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Actions when failed to read value
            }
        })

        //setting onCLickListener to direct user to another page if clicked
        textViewRoom2.setOnClickListener() {
            //1 = available, 0 = occupied
            val intent = Intent(this, Reservation::class.java)
            if(availability == 1)
            {
                startActivity(intent)
            }
            else if(availability == 0)
            {
                //displayOccupiedDialog()
                //startActivity(intent)
                displayToast()
            }
        }

    }//end of onCreate

    fun displayToast() {
        val text = "Room Occupied!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
/*
    private fun displayOccupiedDialog() {
        dialog.setContentView(R.layout.dialog_occupied)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(TRANSPARENT))

         val imageViewOccupied : ImageView = findViewById(R.id.imageViewOccupied)
         val buttonDialogOk : Button = findViewById(R.id.buttonDialogOk)

         buttonDialogOk.setOnClickListener() {
             dialog.dismiss()
         }
        dialog.show()
    }
*/
}

