package com.example.bluetoothjava;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.util.UUID;

public class ConnectBluetoothDeviceThread extends Thread {
    private  BluetoothSocket hc06BluetoothDeviceSocket;
    private static final UUID HC06 = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    OutputStream outputStream;
    InputStream inputStream;

    public ConnectBluetoothDeviceThread(String deviceAdress)  {
        BluetoothSocket tmp;
        BluetoothDevice hc06BluetoothDevice;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        hc06BluetoothDevice = bluetoothAdapter.getRemoteDevice(deviceAdress);
        try {
            tmp = hc06BluetoothDevice.createRfcommSocketToServiceRecord(HC06);
            hc06BluetoothDeviceSocket =tmp;
        }
        catch (Exception e){
            Log.e("Connection", e.toString());
        }
        try{
            inputStream = hc06BluetoothDeviceSocket.getInputStream();
        }catch(Exception e){
            Log.e("Read", "could not get InputStream");
        }
        try{
            outputStream = hc06BluetoothDeviceSocket.getOutputStream();
        }catch (Exception e){
            Log.e("Write", "could not get OutputStream" );
        }

    }
    public void run(){
        try {
            hc06BluetoothDeviceSocket.connect();
        }catch (Exception e){
            Log.e("Connection", e.toString());
        }

    }
    public void close(){
        try {
            hc06BluetoothDeviceSocket.close();
        }catch (Exception e){
            Log.e("Connection", e.toString());
        }
    }
    public String readData(){
        BufferedReader bufferedReader;

        bufferedReader =new BufferedReader( new InputStreamReader(inputStream));
        try {
            return bufferedReader.readLine().toString();
        }catch(Exception e){
            Log.e("Read", "could not read data");
        }
        return null;
    }

    public void writeData(String message){

        try{
            outputStream.write(message.getBytes());
        }catch(Exception e){
            Log.e("Write","Writing doesn not work");
        }


    }
}
