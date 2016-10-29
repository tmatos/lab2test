package com.example.tiago.testapp;

/**
 * Created by tiago on 29/10/16.
 */


import java.io.*;
import java.net.*;
import java.net.Socket;
import java.net.UnknownHostException;

import android.widget.TextView;

/*
 * Implementação do protocolo
 *
 * Author: Tiago Matos Santos
 */
public class Protocol {

    Socket requestSocket;
    DataInputStream in;
    DataOutputStream out;
    String message;

    public Protocol()
    {
        //
    }

    public String getTemp(String host, int porta)
    {
        try
        {
            // criação do socket
            requestSocket = new Socket(host, porta);

            // stream de entrada e saída
            PrintWriter out = new PrintWriter(requestSocket.getOutputStream(), true);
            BufferedReader in =  new BufferedReader( new InputStreamReader(requestSocket.getInputStream()) );

            // comunicacao
            try
            {
                out.println("g");

                //Thread.sleep(100);

                message = in.readLine();

                out.println("b");
            }
            catch(Exception classNot)
            {
                System.err.println("dados em formato desconhecido");
            }
        }
        catch(UnknownHostException unknownHost)
        {
            //System.err.println("Tentativa de conexao a host desconhecido.");
        }
        catch(IOException ioException)
        {
            //ioException.printStackTrace();
        }
        finally
        {
            try
            {
                //in.close();
                //out.close();
                requestSocket.close();
            }
            catch(IOException ioException)
            {
                ioException.printStackTrace();
            }
        }

        return message;
    }

    public void enviarComando(String host, int porta, String cmd)
    {
        try
        {
            // criação do socket
            requestSocket = new Socket(host, porta);

            // stream de entrada e saída
            PrintWriter out = new PrintWriter(requestSocket.getOutputStream(), true);
            BufferedReader in =  new BufferedReader( new InputStreamReader(requestSocket.getInputStream()) );

            // comunicacao
            try
            {
                out.println(cmd);
                out.println("b");
            }
            catch(Exception classNot)
            {
                System.err.println("dados em formato desconhecido");
            }
        }
        catch(UnknownHostException unknownHost)
        {
            //System.err.println("Tentativa de conexao a host desconhecido.");
        }
        catch(IOException ioException)
        {
            //ioException.printStackTrace();
        }
        finally
        {
            try
            {
                //in.close();
                //out.close();
                requestSocket.close();
            }
            catch(IOException ioException)
            {
                //ioException.printStackTrace();
            }
        }
    }

    void sendMessage(String msg)
    {
        try
        {
            out.writeBytes(msg);
            out.flush();
            System.out.println("cliente>" + msg);
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

}
