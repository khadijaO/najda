package com.example.najdaapp;

import static android.Manifest.permission.CALL_PHONE;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.najdaapp.contact.ContactUser;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class StartActivity extends AppCompatActivity {
Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btn1=findViewById(R.id.button1);
        btn2=findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), ContactUser.class);
                startActivity(i);
                //timer duration
//                long duration= TimeUnit.MINUTES.toMillis(1);
////timer countdown
//                new CountDownTimer(duration, 1000) {
//                    @Override
//                    public void onTick(long l) {
////                    convert to sec and min
//                        String sDuration=String.format(Locale.ENGLISH,"%02d","%02d",
//                                TimeUnit.MILLISECONDS.toMinutes(1),
//                                TimeUnit.MILLISECONDS.toSeconds(1)-
//                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));
////                show the values
//                        Toast.makeText(getApplicationContext(), "il vous reste  "+sDuration, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        Toast.makeText(getApplicationContext(), "rah saliiit", Toast.LENGTH_SHORT).show();
//                    }
//                }.start();


            }
        });
    }
}