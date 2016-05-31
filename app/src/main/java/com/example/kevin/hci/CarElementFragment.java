package com.example.kevin.hci;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;



import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarElementFragment extends Fragment {

    private int color;
    private String name ;
    private ListView listView;
    private String[] car_element_rows;
    String[] car_element_values;
    private int image;
    private View v;

    String my_data;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice = null;

    final byte delimiter = 33;
    int readBufferPosition = 0;

    public CarElementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            name = bundle.getString("setName");
            ((CarElementActivity) getActivity()).setName(name);
        }
        ((CarElementActivity)getActivity()).setButtonEnable(true);
        super.onCreate(savedInstanceState);



        final Handler handler = new Handler();

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        final class workerThread implements Runnable {

            private String btMsg;

            public workerThread(String msg) {
                btMsg = msg;
            }

            public void run()
            {
                sendBtMsg(btMsg);

                while(!Thread.currentThread().isInterrupted())
                {
                    int bytesAvailable;
                    boolean workDone = false;

                    try {


                        final InputStream mmInputStream;
                        mmInputStream = mmSocket.getInputStream();
                        for (int i = 0; i < mmInputStream.available(); i++) {
                            System.out.println("" + mmInputStream.read());
                        }
                        bytesAvailable = mmInputStream.available();


                        //System.out.println("bytesAvailable"+bytesAvailable);
                        if(bytesAvailable > 0)
                        {

                            byte[] packetBytes = new byte[bytesAvailable];
                            Log.e("Aquarium recv bt", "bytes available");
                            byte[] readBuffer = new byte[1024];
                            mmInputStream.read(packetBytes);

                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes,"US-ASCII");
                                    readBufferPosition = 0;
                                    //System.out.println("salida");
                                    //android.os.SystemClock.sleep(1000);
                                    System.out.println("data"+data);
                                    car_element_values = data.split("--");

                                    //The variable data now contains our full command
                                    /*handler.post(new Runnable() {
                                        public void run() {
                                            System.out.println("salida");
                                            my_data = data;
                                            car_element_values = data.split("--");
                                            for (int i = 0; i<car_element_values.length; i++){
                                                System.out.println("elemento: "+car_element_values[i]);
                                            }
                                            //myLabel.setText("hola");
                                        }
                                    });*/

                                    workDone = true;
                                    break;


                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }

                            if (workDone == true){
                                mmSocket.close();
                                break;
                            }

                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        };



        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                if(device.getName().equals("raspberrypi")) //Note, you will need to change this to match the name of your device
                {
                    Log.e("Aquarium",device.getName());
                    mmDevice = device;
                    break;
                }
            }
        }

        Thread t = new Thread(new workerThread(name));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //android.os.SystemClock.sleep(1000);

    }

    @Override
    public void onResume() {
        if (name != null) ((CarElementActivity) getActivity()).setName(name);
        ((CarElementActivity)getActivity()).setButtonEnable(true);
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_car_element, container, false);


        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        if (fab != null) {
            fab.getBackgroundTintList();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    //setColor(getColor());
                    //setColor(getColor() + 1);
                    //if (getColor() > 2) setColor(0);
                    //Intent myIntent = new Intent(CarElementActivity.this, OptionsActivity.class);
                    //CarElementActivity.this.startActivity(myIntent);
                    ((CarElementActivity) getActivity()).setActualActivityName("home");
                    OptionsFragment fragment2 = new OptionsFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.main_container, fragment2);
                    ft.commit();
                }
            });
        }

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //setColor(bundle.getInt("setColor"));
            name = bundle.getString("setName");
            ((CarElementActivity) getActivity()).setName(name);
            setCar_element_rows(bundle.getStringArray("setCar_element_rows"));
            if (car_element_values == null) {
                setCar_element_values(bundle.getStringArray("setCar_element_values"));
            }else if (car_element_values.length < car_element_rows.length){
                setCar_element_values(bundle.getStringArray("setCar_element_values"));
            }

            ((CarElementActivity)getActivity()).setImage(bundle.getInt("setImage"));
            ((CarElementActivity)getActivity()).setColor(bundle.getInt("setColor"));
            setListViewMy();
        }

        ((CarElementActivity)getActivity()).setButtonEnable(true);

        return v;
    }

    public void setListViewMy() {
        setListView((ListView) v.findViewById(R.id.list_car_elements));
        getListView().setAdapter(new CarElementAdapter(v, getCar_element_rows(), getCar_element_values()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public String[] getCar_element_rows() {
        return car_element_rows;
    }

    public String[] getCar_element_values() {
        return car_element_values;
    }

    public void setCar_element_rows(String[] car_element_rows) {
        this.car_element_rows = car_element_rows;
    }

    public void setCar_element_values(String[] car_element_rows) {
        this.car_element_values = car_element_rows;
    }

    public void sendBtMsg(String msg2send){
        //UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
        UUID uuid = UUID.fromString("94f39d29-7d6d-437d-973b-fba39e49d4ee"); //Standard SerialPortService ID
        try {

            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            if (!mmSocket.isConnected()){
                mmSocket.connect();
            }

            String msg = msg2send;
            //msg += "\n";
            OutputStream mmOutputStream = mmSocket.getOutputStream();
            mmOutputStream.write(msg.getBytes());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
