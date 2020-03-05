package com.example.bluetoothjava;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action= intent.getAction();
        Log.i("Test","Test Bluetooth");
        if(action.equals(BluetoothDevice.ACTION_FOUND)){
            //TODO
            Log.i("Device", "Device_Action_Found");
        }
        if(action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)){

            //TODO
            Log.i("Device", "Device Action_Bond_State_Changed");
        }
        if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
            //TODO
            Log.i("Adapter","Adapter Action_Discovery_Finished");
        }
        if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)){
            //TODO
            Log.i("Adapter","Adapter Action_Discovery_Started");
        }

    }
}
