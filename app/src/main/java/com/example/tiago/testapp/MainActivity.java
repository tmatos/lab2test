package com.example.tiago.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView response;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear;

    Button btnTemp;
    Button btnLed;


    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAddress = (EditText) findViewById(R.id.addressEditText);
        editTextPort = (EditText) findViewById(R.id.portEditText);
        buttonConnect = (Button) findViewById(R.id.connectButton);
        buttonClear = (Button) findViewById(R.id.clearButton);
        response = (TextView) findViewById(R.id.responseTextView);

        prefs = this.getSharedPreferences(getString(R.string.chave_arquivo_config), Context.MODE_PRIVATE);
        String hostSalvo  = prefs.getString("host", "192.168.0.102");
        editTextAddress.setText(hostSalvo);

        //////////////////////////////////////////////////////
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //////////////////////////////////////////////////////

        buttonConnect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // validacao
                if( editTextAddress.getText().equals("") )
                {
                    response.setText("Por favor, insira o endereço IP da placa.");
                    return;
                }
                else if( editTextPort.getText().equals("") )
                {
                    response.setText("Por favor, insira o número da porta na placa.");
                    return;
                }


                String host = editTextAddress.getText().toString();
                Integer port = Integer.parseInt(editTextPort.getText().toString());

                Client myClient = new Client(host, port, response);
                myClient.execute();


                // salvar as info de conexao
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString( "host", host);
                editor.commit();

            }
        });

        buttonClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                response.setText("");
            }
        });

        btnTemp = (Button) findViewById(R.id.btnTemp);

        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity_Temperatura();
            }
        });

        btnLed = (Button) findViewById(R.id.btnLed);

        btnLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity_Led();
            }
        });


    }

    private void launchActivity_Temperatura() {
        Intent intent = new Intent(this, Temperatura.class);
        startActivity(intent);
    }

    private void launchActivity_Led() {
        Intent intent = new Intent(this, LED.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
