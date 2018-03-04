package com.example.quoctuan.lazada.model.home;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by quoctuan on 04/03/2018.
 */

public class DownloadData extends AsyncTask<String,Void,String>{

    StringBuilder data;
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]); // get URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // push url in network
            connection.setRequestMethod("POST"); // $_POST method
            connection.setDoOutput(true); // open output
            connection.setDoInput(true); // open input

            Uri.Builder uri = new Uri.Builder();
            uri.appendQueryParameter("maloaicha",strings[1]);
            String data_post = uri.build().getEncodedQuery();

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
            writer.write(data_post);
            writer.flush();
            writer.close();

            outputStreamWriter.close();
            outputStream.close();
            connection.connect(); // connect

            InputStream inputStream = connection.getInputStream(); // get inputStream
            InputStreamReader reader = new InputStreamReader(inputStream); // read inputStream
            BufferedReader bufferedReader = new BufferedReader(reader);  // read line
            String line = ""; // var use to save string when buff read line
            data = new StringBuilder(); // var used to save line
            while ((line = bufferedReader.readLine()) != null) { // still read
                data.append(line); // save line
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.toString();
    }
}
