package com.example.quoctuan.lazada.view.home;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quoctuan.lazada.R;
import com.example.quoctuan.lazada.model.home.DownloadData;
import com.example.quoctuan.lazada.presenter.home.PresenterDownloadDataLogic;

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
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,ViewDownloadDataImp{

    EditText ed_parent_code;
    Button btn_get_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ed_parent_code = findViewById(R.id.ed_parent_code);
        btn_get_data = findViewById(R.id.btn_get_data);
        btn_get_data.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String str_parent_code = ed_parent_code.getText().toString(); // get parent code
        // url local virtualBox android is 10.0.2.2
//                String str_url = "http://10.0.2.2/weblazada/loaisanpham.php?maloaicha=" + str_parent_code; // $_GET method
        String str_url = "http://10.0.2.2/weblazada/loaisanpham.php";
        PresenterDownloadDataLogic presenterDownloadDataLogic = new PresenterDownloadDataLogic(
                HomeActivity.this,
                str_url,str_parent_code);
        presenterDownloadDataLogic.downloadData();
        DownloadData download_data = new DownloadData();
        download_data.execute(str_url, str_parent_code);

    }

    @Override
    public void DownloadDataSuccess(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DownloadDataFailure() {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }
}

