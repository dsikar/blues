package com.sikarsystems.blues;

import java.util.Set;

//import com.example.android.BluetoothChat.DeviceListActivity;
//import com.example.android.BluetoothChat.R;

//import com.example.android.BluetoothChat.R;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	//private mArrayAdapter<String> mPairedDevicesArrayAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    
    // Member fields
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;
    //private ArrayAdapter<String> mNewDevicesArrayAdapter;

	private TextView mStatusView;    

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        
        // TextView for paired devices
        mStatusView = (TextView)findViewById(R.id.device_name);
        
        //if (mBluetoothAdapter != null) {
        if (mBtAdapter != null) {
            // Device supports Bluetooth
            //if (!mBluetoothAdapter.isEnabled()) {
        	if (!mBtAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                
                // Initialize array adapters. One for already paired devices and
                // one for newly discovered devices
                mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_main);
                //mNewDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);                
                
                Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
	             // If there are paired devices
	             if (pairedDevices.size() > 0) {
	                 // Loop through paired devices
	                 for (BluetoothDevice device : pairedDevices) {
	                     // Add the name and address to an array adapter to show in a ListView
	                	 mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                 }
             }
             
            }
        }

        
    }


	void scan_devices() {
	
        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        
        // TextView for paired devices
        mStatusView = (TextView)findViewById(R.id.device_name);
        
        //if (mBluetoothAdapter != null) {
        if (mBtAdapter != null) {
            // Device supports Bluetooth
            //if (!mBluetoothAdapter.isEnabled()) {
        	if (mBtAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                
                // Initialize array adapters. One for already paired devices and
                // one for newly discovered devices
                mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_main);
                //mNewDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);                
                
                Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
	             // If there are paired devices
	             if (pairedDevices.size() > 0) {
	                 // Loop through paired devices
	                 for (BluetoothDevice device : pairedDevices) {
	                     // Add the name and address to an array adapter to show in a ListView
	                	 mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
	                	 mStatusView.setText(device.getName() + "\n" + device.getAddress()); 
	                 } 
	                 //mStatusView.setText(mPairedDevicesArrayAdapter.toString());    
                 }
	             //else {mStatusView.setText("pairedDevices.size() == 0");}
            }
        }
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	mStatusView = (TextView)findViewById(R.id.device_name);
    	Intent serverIntent = null;
        switch (item.getItemId()) {
        case R.id.list_paired_devices:

        	//mStatusView.setText("secure_connect_scan");
        	scan_devices();
            // Launch the DeviceListActivity to see devices and do scan
            //serverIntent = new Intent(this, DeviceListActivity.class);
            //startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
            return true;
        /*
        case R.id.insecure_connect_scan:
        	mStatusView.setText("insecure_connect_scan");
            // Launch the DeviceListActivity to see devices and do scan
            //serverIntent = new Intent(this, DeviceListActivity.class);
            //startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
            return true;
        case R.id.discoverable:
        	mStatusView.setText("discoverable");
            // Ensure this device is discoverable by others
            //ensureDiscoverable();
            return true;
         */
        }
        return false;
    }    
    
}