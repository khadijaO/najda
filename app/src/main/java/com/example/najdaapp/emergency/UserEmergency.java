package com.example.najdaapp.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.najdaapp.DBSqLite.DatabaseHelper;
import com.example.najdaapp.R;

public class UserEmergency extends AppCompatActivity {
    Button add_emergency,reset_form,manage_emergency;
    EditText number_emergency, name_emergency;
    DatabaseHelper db;
    String numberEmergency, nameEmergency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_emergency);

        add_emergency=findViewById(R.id.addEmergency);
        reset_form=findViewById(R.id.ResetEmergency);
        manage_emergency=findViewById(R.id.manageEmergency);
        number_emergency=findViewById(R.id.emergency_number);
        name_emergency=findViewById(R.id.emergency_name);
        //        ------------------GET default emergency------------------


       db=new DatabaseHelper(this);

     EmergencyModel contact_user=new EmergencyModel( "19","police");
   db.addEmergency(contact_user);
        EmergencyModel r=db.getEmergency("");
        number_emergency.setText(r.getPhoneNo());
        name_emergency.setText(r.getName());
//        ------------------------------




//        ------------------Add emergency------------------

        add_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberEmergency=number_emergency.getText().toString();
                nameEmergency=name_emergency.getText().toString();
                //add the data in DB
                boolean  isAllFieldsChecked = CheckAllFields();
                if(isAllFieldsChecked){
                    EmergencyModel contact_user=new EmergencyModel( numberEmergency,nameEmergency);
                    db.addEmergency(contact_user);
                    showAlert("Contact added , check All Contacts");
                }
            }
        });
//        ------------------------------------------------

 //        ------------------RESET emergency------------------
        reset_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_emergency.setText("");
                number_emergency.setText("");
//                db.deleteAll();

            }
        });
//        ------------------------------------------------

 //        ------------------mANAGE emergency------------------
        manage_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ManageEmergency.class);
                startActivity(i);
            }
        });
//        ------------------------------------------------
    }

    public  boolean CheckAllFields() {
        if (name_emergency.length() == 0) {
            name_emergency.setError(getString(R.string.name_phoneError));
            return false;
        }

        if (number_emergency.length() == 0) {
            number_emergency.setError(getString(R.string.name_phoneError) );
            return false;
        }

        if (!android.util.Patterns.PHONE.matcher(numberEmergency).matches()) {
            number_emergency.setError(getString(R.string.phoneFormatError));
            return false;
        }
        // after all validation return true.
        return true;
    }
      public void showAlert(String message){

    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
}