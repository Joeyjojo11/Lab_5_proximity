package com.example.jdolan.lab_5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener  {

    private SensorManager mSensorManager;
    private Sensor mProximity;
    int i = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //create variable to add listener to
        Button button = (Button) findViewById(R.id.button);     // Go/Stop Button


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // initializeSensor();
        button.setText("Go");
        button.setTag(0);//do this when you first initialize the button. You can even specify this in Xml with android:tag="0"
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) findViewById(R.id.button);
                button.setText("Go");
                final int status = (Integer) view.getTag();
                switch (status) {
                    
                    case 0:
                        button.setText("Go");
                        view.setTag(1); //pause
                        i=1;
                        break;

                    case 1:
                        button.setText("Stop");
                        view.setTag(0); //pause
                        i=0;
                        //callSensor();
                        break;
                }
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] == 0 & i == 0) {
                //near
                TextView tv2 = (TextView) findViewById(R.id.textView);
                tv2.setText("Near");
            } else {
                //far
                TextView tv2 = (TextView) findViewById(R.id.textView);
                tv2.setText("Far");
            }
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



}
