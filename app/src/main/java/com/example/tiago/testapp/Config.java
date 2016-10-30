package com.example.tiago.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tiago on 29/10/16.
 */

public class Config {

    private SharedPreferences prefs;
    public String host;
    public Integer port;

    Config(Activity activity)
    {
        prefs = activity.getSharedPreferences( activity.getString(R.string.chave_arquivo_config), Context.MODE_PRIVATE);
        host = prefs.getString("host", "192.168.0.103");
        port = prefs.getInt("port", 33);
    }
}
