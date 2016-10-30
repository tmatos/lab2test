package com.example.tiago.testapp;

/**
 * Created by tiago on 29/10/16.
 */


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.AsyncTask;
import android.widget.TextView;

public class Client extends AsyncTask<Void, Void, Void> {

    String host;
    int port;
    String response = "";
    TextView textResponse;

    String cmd = "";

    Client(String addr, int porta, TextView textResponse) {
        host = addr;
        port = porta;
        this.textResponse = textResponse;
    }

    Client(String addr, int porta, TextView textResponse, String comando) {
        host = addr;
        port = porta;
        this.textResponse = textResponse;
        this.cmd = comando;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(host, port);

            // comunicacao
            try {
                // stream de entrada e saída
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                if(cmd == "1" || cmd == "0")
                {
                    out.println(cmd);
                    out.println("b");
                }
                else if (cmd == "g")
                {
                    out.println("g");
                    //Thread.sleep(100);
                    response = in.readLine();
                    out.println("b");
                }


            }
            catch(Exception classNot)
            {
                System.err.println("dados em formato desconhecido");
            }


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }

    public void ligar_led() {
        cmd = "1";
        this.execute();
    }

    public void desligar_led() {
        cmd = "0";
        this.execute();
    }

    public Integer get_temperatura() {
        cmd = "g";
        this.execute();
        return Integer.parseInt(response);
    }
}
