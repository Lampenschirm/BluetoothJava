package com.example.bluetoothjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EstablishConnection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establish_connection);
        Bundle extras = getIntent().getExtras();
        final ConnectBluetoothDeviceThread connectBluetoothDeviceThread;
        final Button buttonCloseConnection= (Button) findViewById(R.id.buttonCloseConnection);
        final Button buttonWriteData= (Button) findViewById(R.id.buttonWriteData);
        final Button buttonCreateConnection =(Button) findViewById(R.id.buttonCreateConnection);
        String blueToothDevice = null;
        if(extras != null){
            blueToothDevice = extras.get("com.example.bluetoothjava.MESSAGE").toString();
            TextView textView = findViewById(R.id.textView);
            textView.setText(blueToothDevice);

        }
        connectBluetoothDeviceThread = new  ConnectBluetoothDeviceThread(blueToothDevice.substring(blueToothDevice.length()-17));
        connectBluetoothDeviceThread.start();

        buttonCloseConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(connectBluetoothDeviceThread != null){
                   connectBluetoothDeviceThread.close();
               }
            }
        });

        buttonWriteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String writeData = ((EditText)findViewById(R.id.contentWriteData)).getText().toString();
                connectBluetoothDeviceThread.writeData(writeData);
            }
        });

        buttonCreateConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectBluetoothDeviceThread.run();
            }
        });
    }
}
