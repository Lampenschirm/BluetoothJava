package com.example.bluetoothjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class EstablishConnection extends AppCompatActivity implements CoordinateChangedListener{
    ConnectBluetoothDeviceThread connectBluetoothDeviceThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establish_connection);
        Bundle extras = getIntent().getExtras();

        final Button buttonCloseConnection=  findViewById(R.id.buttonCloseConnection);
        final Button buttonWriteData= findViewById(R.id.buttonWriteData);
        final Button buttonCreateConnection = findViewById(R.id.buttonCreateConnection);
        String blueToothDevice = null;
        if(extras != null){
            blueToothDevice = Objects.requireNonNull(extras.get("com.example.bluetoothjava.MESSAGE"),"Messag from Main Activity must be not nul").toString();
            TextView textView = findViewById(R.id.textView);
            textView.setText(blueToothDevice);

        }
        connectBluetoothDeviceThread = new  ConnectBluetoothDeviceThread(Objects.requireNonNull(blueToothDevice,"BluetoothDevice Object is null").substring(blueToothDevice.length()-17));
        connectBluetoothDeviceThread.start();
        Toast.makeText(this, "Connection will be started", Toast.LENGTH_SHORT).show();

        buttonCloseConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(connectBluetoothDeviceThread != null){
                   connectBluetoothDeviceThread.close();
                   Toast.makeText(EstablishConnection.this,"Connection will be closed",Toast.LENGTH_SHORT).show();
               }
            }
        });

        buttonWriteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String writeData = ((EditText)findViewById(R.id.contentWriteData)).getText().toString();
                connectBluetoothDeviceThread.writeData(writeData);
                Toast.makeText(EstablishConnection.this, "Data is written", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCreateConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectBluetoothDeviceThread.start();
                Toast.makeText(EstablishConnection.this, "Connection will be started", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCustomObject circle =  findViewById(R.id.Circle);
        circle.setOnTouchListener( circle);
    }

    @Override
    public void onChangedCoordinates(int originX, int originY, int newX, int newY) {
        String changedCoordinates = Integer.toString(newX - originX) + ';' + Integer.toString(newY - originY) +"\n";
        connectBluetoothDeviceThread.writeData(changedCoordinates);
        Log.i("Coordinates", "Changed Value: " + changedCoordinates);
    }
}
