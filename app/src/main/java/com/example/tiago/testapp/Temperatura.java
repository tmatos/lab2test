package com.example.tiago.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class Temperatura extends AppCompatActivity {

    SharedPreferences prefs;
    String host;
    Integer port;

    TextView txtMsg;
    TextView txtTemp;

    Handler handler;

    Client cli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        prefs = this.getSharedPreferences(getString(R.string.chave_arquivo_config), Context.MODE_PRIVATE);
        host = prefs.getString("host", "192.168.0.103");
        port = prefs.getInt("port", 33);

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        txtTemp = (TextView) findViewById(R.id.txtTemp);

        cli = new Client(host, port, txtMsg, "g");

        handler = new Handler();

        // Start the initial runnable task by posting through the handler
        handler.post(runnableCode);

    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            Log.d("Handlers", "Chamados na thread principal");

            cli = new Client(host, port, txtTemp, "g");
            cli.execute();
            cli = null;
            //Integer i =cli.get_temperatura();
            //txtTemp.setText( cli.get_temperatura().toString() + " graus Celsius" );

            // Repeat this the same runnable code block again another 5 seconds
            handler.postDelayed(runnableCode, 5000);

        }
    };

}
