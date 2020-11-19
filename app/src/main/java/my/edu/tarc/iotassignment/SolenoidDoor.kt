package my.edu.tarc.iotassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

lateinit var code:String
class SolenoidDoor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solenoid_door)



        val psw: EditText = findViewById(R.id.password)
        val buttonProceed: Button = findViewById(R.id.buttonProceed)

        //retrieving the pass code from firebase
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("DoorPIN")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //storing the correct password into code variable
                code = dataSnapshot.value.toString();
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        buttonProceed.setOnClickListener {
            //assigning user input to a variable
            val password:String = psw.text.toString().trim();
            if(password.isEmpty()){
                psw.error = "Password is required!";
                psw.requestFocus();
            }

            else if(password.length < 6){
                psw.error = "Password must not less than 6 digit!";
                psw.requestFocus();
            }
            else{
                //do password checking
                if(password == code){
                    //door open
                    Toast.makeText(baseContext, "Door Unlocked!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, DisplayRoom::class.java)
                    startActivity(intent)
                }
                else{
                    psw.error = "Password does not match!";
                    psw.requestFocus();
                }

            }}

    }
}