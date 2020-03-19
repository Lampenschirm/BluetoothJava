package com.example.bluetoothjava;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.startActivity;

class ListViewHolder extends RecyclerView.ViewHolder {
    private final String EXTRA_MESSAGE = "com.example.bluetoothjava.MESSAGE";
    private TextView textView;
    private Button button;
    protected ListViewHolder(@NonNull View itemView) {
        super(itemView);
    textView = itemView.findViewById(R.id.BoundedDevice);
    button = itemView.findViewById(R.id.Connect);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO start new Activity to Connect to Bluetoothdevice
            Log.i("Connect","Start new Activity");
            Intent intent =new Intent(v.getContext(),EstablishConnection.class);
            intent.putExtra(EXTRA_MESSAGE,textView.getText());
            startActivity(v.getContext(), intent,null);
        }
    });
    }
    protected void setDeviceNameAddress(String deviceNameAddress){
        textView.setText(deviceNameAddress);
    }

    public String getDeviceNameAddress(){
        return textView.getText().toString();
    }

}
