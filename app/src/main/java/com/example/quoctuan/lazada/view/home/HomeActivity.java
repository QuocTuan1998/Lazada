package com.example.quoctuan.lazada.view.home;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quoctuan.lazada.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    EditText ed_parent_code;
    Button btn_get_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ed_parent_code = findViewById(R.id.ed_parent_code);
        btn_get_data = findViewById(R.id.btn_get_data);

        btn_get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_parent_code = ed_parent_code.getText().toString(); // get parent code
                // url local virtualBox android is 10.0.2.2
//                String str_url = "http://10.0.2.2/weblazada/loaisanpham.php?maloaicha=" + str_parent_code; // $_GET method
                String str_url = "http://10.0.2.2/weblazada/loaisanpham.php";
                DownloadJSON downloadJSON = new DownloadJSON();
                downloadJSON.execute(str_url,str_parent_code);
            }
        });
    }
    public class DownloadJSON extends AsyncTask<String,Void,String> {

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

                connection.connect(); // connect

                InputStream inputStream = connection.getInputStream(); // get inputStream
                InputStreamReader reader = new InputStreamReader(inputStream); // read inputStream
                BufferedReader bufferedReader = new BufferedReader(reader);  // read line
                String line = ""; // var use to save string when buff read line
                StringBuilder data = new StringBuilder(); // var used to save line
                while ((line = bufferedReader.readLine()) != null) { // still read
                    data.append(line); // save line
                }
                Log.d("output_JSON",data.toString()); // test
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
