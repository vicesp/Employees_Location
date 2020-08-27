package com.example.employees_location.ui.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.employees_location.R;
import com.example.employees_location.ui.employees.EmployeesActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button showEmployeesButton;
    Button addEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        showEmployeesButton=findViewById(R.id.btnShowEmployees);
        addEmployeeButton=findViewById(R.id.btnAddEmployees);
        showEmployeesButton.setOnClickListener(this);
        addEmployeeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnShowEmployees) {
            Intent intent = new Intent(this, EmployeesActivity.class);
            startActivity(intent);
        } else if (i == R.id.btnAddEmployees) {
            Intent intent = new Intent(this, EmployeesActivity.class);
            startActivity(intent);
        }

    }
}
