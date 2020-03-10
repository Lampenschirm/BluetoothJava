package com.example.bluetoothjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothReceiver bluetoothReceiver;
    private static final int LOCATION_PERMISSION_Request=5;
    private BluetoothSocket socket;
    public Set<BluetoothDevice> bluetoothDeviceList;
    private RecyclerView blueToothDeviceListView;
    private RecyclerView.Adapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!bluetoothAdapter.isEnabled()){
            Intent enableBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlueTooth,0);
        }

        bluetoothReceiver = new BluetoothReceiver();
        bluetoothDeviceList = bluetoothAdapter.getBondedDevices();

        blueToothDeviceListView = findViewById(R.id.bluetoothDeviceList);
        blueToothDeviceListView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new ListViewAdapter(bluetoothDeviceList);
        blueToothDeviceListView.setAdapter(recyclerViewAdapter);

        //configure BlueTooth IntentFilter
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        this.registerReceiver(bluetoothReceiver,intentFilter);

        startDiscoveryWithPermission();

    }

    private void startDiscoveryWithPermission() {
        //ask for permission if not activated
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                       LOCATION_PERMISSION_Request);
        }
        else{
            bluetoothAdapter.startDiscovery();
        }
    }

    // callback from requestPermission()
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_Request) {
            if (grantResults[0] != PERMISSION_GRANTED) {
                // Start des Discovery um Devices zu finden
                bluetoothAdapter.startDiscovery();

            }
        }
    }
    private boolean blueToothConnect(@NonNull String macAdress){
        boolean socketResult = false;
        BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(macAdress);
        try{
            socket =remoteDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            Log.d("Socket", "Socket erstellt");
            socketResult =true;
        }
        catch(Exception e){
            Log.e("Socket","Socket konnte nicht erstellt werden");
        }
        return socketResult;
    }
}
