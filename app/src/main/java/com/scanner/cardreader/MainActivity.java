package com.scanner.cardreader;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Button proceedBtn;
    private ProgressBar progressBar;
    public static String SIM; //Global Variable to define the network carrier : NTC/NCELL



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proceedBtn = (Button) findViewById(R.id.proceedBtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        SimInfo info = new SimInfo();
        info.execute();
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(i);
            }
        });


    }

    private class SimInfo extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            String carrier = tm.getLine1Number().substring(0,3);
            if(carrier.equals("984")){
                SIM = "NTC";
            }
            else {
                SIM = "NCELL";
            }



            return null;
        }
//         Called before background task execution
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            proceedBtn.setVisibility(View.GONE);
        }
//          Called after background task execution
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            proceedBtn.setVisibility(View.VISIBLE);
        }
    }


}