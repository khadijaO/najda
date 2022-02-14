package com.example.najdaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.najdaapp.Message.Message;
import com.example.najdaapp.contact.UserContact;
import com.example.najdaapp.emergency.UserEmergency;
import com.example.najdaapp.medias.UserMedias;
import com.google.android.material.navigation.NavigationView;

public class ProfilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
MenuItem profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(ProfilActivity.this,drawerLayout,toolbar,R.string.title_slide1,R.string.text_slide2);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
navigationView.setNavigationItemSelectedListener(this);
navigationView.setCheckedItem(R.id.nav_home);
//profile=findViewById(R.id.nav_profile);
//profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//        Intent i = new Intent(ProfilActivity.this, userProfile.class);
//        startActivity(i);
//        return false;
//    }
//});
    }

    @Override
    public void onBackPressed() {// to avoid closing the app with back pressed

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
        super.onBackPressed();}
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
switch (menuItem.getItemId()){
    case R.id.nav_profile:
        Intent i = new Intent(ProfilActivity.this, userProfile.class);
        startActivity(i);
        break;
    case R.id.nav_home:
        Intent m= new Intent(ProfilActivity.this, MainActivityShake.class);
        startActivity(m);
        break;
    case R.id.nav_contact:
        Intent j= new Intent(ProfilActivity.this, UserContact.class);
        startActivity(j);
        break;

    case R.id.nav_call:
        Intent k= new Intent(ProfilActivity.this, UserEmergency.class);
        startActivity(k);
        break;
    case R.id.nav_medias:
        Intent l= new Intent(ProfilActivity.this, MainActivityShake.class);
        startActivity(l);
        break;
    case R.id.nav_message:
        Intent ii= new Intent(ProfilActivity.this, Message.class);
        startActivity(ii);
        break;

    case R.id.nav_service:
        Intent ij= new Intent(ProfilActivity.this, MainActivityFall.class);
        startActivity(ij);
        break;
default:


}
        return true;
    }
}
