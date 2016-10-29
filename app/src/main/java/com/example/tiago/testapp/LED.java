package com.example.tiago.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LED extends AppCompatActivity {

    SharedPreferences prefs;

    Button btnLigar;
    Button btnDesligar;

    TextView txtResposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);

        prefs = this.getPreferences(Context.MODE_PRIVATE);

        btnLigar = (Button) findViewById(R.id.btnLigar);
        btnDesligar = (Button) findViewById(R.id.btnDesligar);
        txtResposta = (TextView) findViewById(R.id.txtResposta);

        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String host = prefs.getString("host", "192.168.0.103");
                Integer port = prefs.getInt("port", 33);

                String response = "";

                Client cliente = new Client(host, port, txtResposta);
                cliente.ligar_led();

            }
        });
    }
}