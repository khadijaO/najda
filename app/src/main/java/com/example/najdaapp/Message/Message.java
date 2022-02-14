package com.example.najdaapp.Message;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.najdaapp.DBSqLite.DatabaseHelper;
import com.example.najdaapp.MainActivity;
import com.example.najdaapp.R;
import com.example.najdaapp.contact.ContactModel;
import com.example.najdaapp.contact.UserContact;
import com.example.najdaapp.emergency.UserEmergency;
import com.example.najdaapp.userProfile;
import com.google.android.material.navigation.NavigationView;


public class Message extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Button add_message,reset_form;
    EditText greeting, body;
    String greeting_text, body_text;

     DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(Message.this,drawerLayout,toolbar,R.string.title_slide1,R.string.text_slide2);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_message);
//-----------------------------ADD MESSAGE--------------------------

        db=new DatabaseHelper (this);

        add_message=findViewById(R.id.addMessage);
        greeting=findViewById(R.id.greeting);
        body=findViewById(R.id.body);

        add_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                greeting_text=greeting.getText().toString();
                body_text=body.getText().toString();
                boolean  isAllFieldsChecked = CheckAllFields();
                if(isAllFieldsChecked){
                    MessageModule messageModule=new MessageModule(greeting_text,body_text,true,true);
                    db.addMessageGPS(messageModule);
                    Toast.makeText(getApplicationContext(), "Message Added successfully!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        reset_form=findViewById(R.id.ResetMessage);
        reset_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add the data in DB
//                relation_contact.setText("");
                greeting.setText("");
                body.setText("");
            }
        });
       MessageModule defaultmsg=new MessageModule("hi","help",true,true);
      db.addMessageGPS(defaultmsg);
        MessageModule msg=db.getMessage(true);
        greeting.setText(msg.getSalutation());
        body.setText(msg.getBody());
//        Toast.makeText(this, "greeting "+msg.getSalutation()+" , "+msg.getBody(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();}
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                Intent i = new Intent(Message.this, userProfile.class);
                startActivity(i);
                break;
            case R.id.nav_home:
                Intent m= new Intent(Message.this, MainActivity.class);
                startActivity(m);
                break;
            case R.id.nav_contact:
                Intent j= new Intent(Message.this, UserContact.class);
                startActivity(j);
                break;

            case R.id.nav_call:
                Intent k= new Intent(Message.this, UserEmergency.class);
                startActivity(k);
                break;
            case R.id.nav_medias:
                Intent l= new Intent(Message.this, com.example.najdaapp.medias.UserMedias.class);
                startActivity(l);
                break;
            case R.id.nav_message:
                Intent ii= new Intent(Message.this, Message.class);
                startActivity(ii);
                break;
            default:


        }
        return true;
    }


    public  boolean CheckAllFields() {
        if (greeting.length() == 0) {
            greeting.setError(getString(R.string.name_phoneError));
            return false;
        }

        if (body.length() == 0) {
            body.setError(getString(R.string.name_phoneError) );
            return false;
        }


        // after all validation return true.
        return true;
    }
}