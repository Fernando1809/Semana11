package com.ugb.miprimerproyecto;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class enviarDatosAmigos extends AsyncTask<String, String, String> {
    Context context;
    utilidades uc = new utilidades();
    String resp;

    public enviarDatosAmigos(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    HttpURLConnection urlConnection;
    @Override
    protected String doInBackground(String... parametros) {
        String jsonResponse = null;
        String jsonDatos = parametros[0];
        BufferedReader bufferedReader;

        try{
            URL url = new URL(uc.url_mto);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setRequestProperty("Accept","application/json");

            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(),"UTF-8"));
            writer.write(jsonDatos);
            writer.close();

            InputStream inputStream = urlConnection.getInputStream();
            if(inputStream==null){
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            resp = bufferedReader.toString();

            String line;
            StringBuffer stringBuffer = new StringBuffer();
            while ( (line=bufferedReader.readLine())!=null ){
                stringBuffer.append(line+"\n");
            }
            if( stringBuffer.length()==0 ){
                return null;
            }
            jsonResponse = stringBuffer.toString();
            return jsonResponse;
        }catch (Exception e){
            Log.d("ENVIANDO", "Error: "+ e.getMessage());
        }
        return null;
    }
}
