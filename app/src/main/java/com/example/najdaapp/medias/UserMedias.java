package com.example.najdaapp.medias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.najdaapp.MainActivity;
import com.example.najdaapp.Message.Message;
import com.example.najdaapp.ProfilActivity;
import com.example.najdaapp.R;
import com.example.najdaapp.contact.UserContact;
import com.example.najdaapp.emergency.UserEmergency;
import com.example.najdaapp.userProfile;
import com.google.android.material.navigation.NavigationView;

public class UserMedias extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_medias);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(UserMedias.this,drawerLayout,toolbar,R.string.title_slide1,R.string.text_slide2);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_medias);
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
                Intent i = new Intent(UserMedias.this, userProfile.class);
                startActivity(i);
                break;
            case R.id.nav_home:
                Intent m= new Intent(UserMedias.this, MainActivity.class);
                startActivity(m);
                break;
            case R.id.nav_contact:
                Intent j= new Intent(UserMedias.this, UserContact.class);
                startActivity(j);
                break;

            case R.id.nav_call:
                Intent k= new Intent(UserMedias.this, UserEmergency.class);
                startActivity(k);
                break;
            case R.id.nav_medias:
                Intent l= new Intent(UserMedias.this, UserMedias.class);
                startActivity(l);
                break;
            case R.id.nav_message:
                Intent ii= new Intent(UserMedias.this, Message.class);
                startActivity(ii);
                break;
            default:


        }
        return true;
    }
        }
