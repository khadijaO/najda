package com.example.najdaapp.serviceFall;

import static android.Manifest.permission.CALL_PHONE;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


import com.example.najdaapp.DBSqLite.DatabaseHelper;
import com.example.najdaapp.R;
import com.example.najdaapp.emergency.EmergencyModel;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class OnfallService extends Service implements SensorEventListener {
    private static final String TAG="MainActivity";
    private SensorManager sensorManager;
    Sensor accelerometer;
    DatabaseHelper db;
    TextView xValue,yValue,zValue;
    @Override
    public void onCreate() {
        super.onCreate();
        // start the foreground service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());


//        _______________________________________________________
        Log.d(TAG,"onCreate: Initializing Sensor Service");
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.sensorManager.registerListener((SensorEventListener) this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG,"onCreate: Registered accelerometer Listener");


    }

    public OnfallService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground() {
        String NOTIFICATION_CHANNEL_ID = "example.permanence";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_MIN);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("FALL " +
                        "")
                .setContentText("We are there for you")

                // this is important, otherwise the notification will show the way
                // you want i.e. it will show some default notification
                .setSmallIcon(R.drawable.ic_launcher_foreground)

                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG,"onSensorChanged: X: " + sensorEvent.values[0]+"onSensorChanged: Y: "+ sensorEvent.values[1]+"onSensorChanged: Z: "+ sensorEvent.values[2]);


        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(OnfallService.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

//        xValue.setText("xValue: " +sensorEvent.values[0]);
//        yValue.setText("yValue: " +sensorEvent.values[1]);
//        zValue.setText("zValue: " +sensorEvent.values[2]);

        double loX = sensorEvent.values[0];
        double loY = sensorEvent.values[1];
        double loZ = sensorEvent.values[2];

        double loAccelerationReader = Math.sqrt(Math.pow(loX, 2)
                + Math.pow(loY, 2)
                + Math.pow(loZ, 2));

        DecimalFormat precision = new DecimalFormat("0.00");
        double ldAccRound = Double.parseDouble(precision.format(loAccelerationReader));

        if (ldAccRound > 0.3d && ldAccRound < 0.5d) {
            Toast.makeText(this, "FALL DETECTED!!!!!", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(Intent.ACTION_DIAL);
//             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setData(Uri.parse("119"));
//            startActivity(intent);
db=new DatabaseHelper(this);
EmergencyModel r=db.getEmergency("");
String tel=r.getPhoneNo();



//            Toast.makeText(getApplicationContext(), "phone = "+tel, Toast.LENGTH_SHORT).show();
            try{
    Intent i = new Intent(Intent.ACTION_CALL);
    i.setData(Uri.parse("tel:"+tel));
                 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


    if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
        startActivity(i);
    } else {
//        requestPermissions(new String[]{CALL_PHONE}, 1);
    }
}
catch (Exception eh){
    Toast.makeText(this, "can't !!!!"+eh.getMessage(), Toast.LENGTH_LONG).show();

}

            Vibrator v = (Vibrator)getSystemService(OnfallService.VIBRATOR_SERVICE);
v.vibrate(1000);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}