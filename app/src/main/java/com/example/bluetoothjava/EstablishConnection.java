package com.example.bluetoothjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EstablishConnection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establish_connection);
        Bundle extras = getIntent().getExtras();
        ConnectBluetoothDeviceThread connectBluetoothDeviceThread;
        if(extras != null){
            String blueToothDevice = extras.get("com.example.bluetoothjava.MESSAGE").toString();
            TextView textView = findViewById(R.id.textView);
            textView.setText(blueToothDevice);
            connectBluetoothDeviceThread = new ConnectBluetoothDeviceThread(blueToothDevice.substring(blueToothDevice.length()-11));
            connectBluetoothDeviceThread.run();
        }
    }
}
