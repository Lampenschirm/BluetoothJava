package com.example.bluetoothjava;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ListViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    protected ListViewHolder(@NonNull View itemView) {
        super(itemView);
    textView = itemView.findViewById(R.id.BoundedDevice);
    }
    protected void setDeviceNameAdress(String deviceNameAdress){
        textView.setText(deviceNameAdress);
    }

    public String getDeviceNameAdress(){
        return textView.getText().toString();
    }

}
