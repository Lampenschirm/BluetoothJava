package com.example.bluetoothjava;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Set;

public class ListViewAdapter  extends RecyclerView.Adapter<ListViewHolder> {

    private final Set<BluetoothDevice> bluetoothDeviceSet;

    protected ListViewAdapter(Set<BluetoothDevice> bluetoothDeviceSet){
        this.bluetoothDeviceSet = bluetoothDeviceSet;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        BluetoothDevice[] bluetoothDeviceArray = (BluetoothDevice[])bluetoothDeviceSet.toArray();
        String deviceNameAddress = bluetoothDeviceArray[position].getName() + bluetoothDeviceArray[position].getAddress();
        holder.setDeviceNameAddress(deviceNameAddress);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
