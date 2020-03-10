package com.example.bluetoothjava;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ListViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private Button button;
    protected ListViewHolder(@NonNull View itemView) {
        super(itemView);
    textView = itemView.findViewById(R.id.BoundedDevice);
    button = itemView.findViewById(R.id.Connect);
    }
    protected void setDeviceNameAddress(String deviceNameAddress){
        textView.setText(deviceNameAddress);
    }

    public String getDeviceNameAddress(){
        return textView.getText().toString();
    }

}
