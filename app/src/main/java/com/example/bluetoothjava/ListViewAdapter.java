package com.example.bluetoothjava;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListViewAdapter  extends RecyclerView.Adapter<ListViewHolder> {


   private List<String> bluetoothDeviceArray;

    protected ListViewAdapter(Set<BluetoothDevice> bluetoothDeviceSet){
        bluetoothDeviceArray = new ArrayList<String>();
        for(BluetoothDevice bluetoothDevice: bluetoothDeviceSet){
            bluetoothDeviceArray.add(bluetoothDevice.getName()+bluetoothDevice.getAddress());
        }
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        String deviceNameAddress = bluetoothDeviceArray.get(position);
        holder.setDeviceNameAddress(deviceNameAddress);
    }

    @Override
    public int getItemCount() {
        return bluetoothDeviceArray.size();
    }


}
