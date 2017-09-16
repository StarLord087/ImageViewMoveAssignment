package com.example.shekhar.imageviewmoveassignment;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import static android.R.attr.gravity;

public class MainActivity extends AppCompatActivity {

    float x;
    float y;
    float z;
    public static final String TAG = "Main Activity";
    ImageView imageView;
    Sensor acceloSensor;
    SensorEventListener sensorEventListener;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageview);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        acceloSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {


                    x += sensorEvent.values[0];

                    y += sensorEvent.values[1];

                    z += sensorEvent.values[2];

                    Log.d(TAG, "onSensorChanged: X" + x);
                    Log.d(TAG, "onSensorChanged: Y" + y);
                    Log.d(TAG, "onSensorChanged: Z" + z);


                    imageView.setX((-x));
                    imageView.setY((y));


                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, acceloSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
