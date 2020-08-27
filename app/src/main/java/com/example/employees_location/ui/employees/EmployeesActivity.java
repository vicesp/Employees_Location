package com.example.employees_location.ui.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.employees_location.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

public class EmployeesActivity extends AppCompatActivity {

    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        setContentView(R.layout.activity_employees);

        tv=findViewById(R.id.textView);
        btn=findViewById(R.id.button);
        final String URL="https://dl.dropboxusercontent.com/s/5u21281sca8gj94/getFile.json?dl=0”";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncHttpClient().get(URL, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response=new String(responseBody);

                        Gson gson = new Gson();
                        Model responseObj = gson.fromJson(response, Model.class);
                        String data=responseObj.getData().getFile();
                        tv.setText(data);

                        DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(data);

                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setTitle("EmployeesList");
                        request.setDescription("Downloading");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"game-of-life");
                        downloadmanager.enqueue(request);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        tv.setText("Error");

                    }
                });
            }
        });
    }
}
