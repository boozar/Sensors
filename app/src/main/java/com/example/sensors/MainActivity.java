package com.example.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textList;
    SensorManager sensorManager;
    List<Sensor> sensors;
    Sensor sensorLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textList = findViewById(R.id.txtList);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void onBtnLight(View v){
        sensorManager.registerListener(sensorEventListener, sensorLight, sensorManager.SENSOR_DELAY_NORMAL);

    }
    public void onBtnList (View v){
        sensorManager.unregisterListener(sensorEventListener, sensorLight);
        StringBuilder sb = new StringBuilder();
        String str = "";
        int i  = 0;
        for (Sensor sensor : sensors){
            str += "name = "+ sensor.getName()+ ", type = "+ sensor.getType()
                    +"\nvendor = "+ sensor.getVendor()+ ", version = "+ sensor.getVersion()
                    +"\nmax = "+ sensor.getMaximumRange()+ ", resolution = "
                    +sensor.getResolution()+ "\n-------------------------------\n";
//            sb.append("name = ").append(sensor.getName())
//                    .append(", type = ").append(sensor.getType())
//                    .append("\nvendor = ").append(sensor.getVendor())
//                    .append(", version = ").append(sensor.getVersion())
//                    .append("\nmax = ").append(sensor.getMaximumRange())
//                    .append(", resolution = ").append(sensor.getResolution())
//                    .append("\n-------------------------------\n");

            i++;
        }
        textList.setText("Quantity sensors = "+ i +"\n" + str);
    }
    @Override
    protected  void  onPause(){
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener, sensorLight);
    }
    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            textList.setText(String.valueOf(event.values[0]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

}
