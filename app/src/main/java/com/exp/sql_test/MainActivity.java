package com.exp.sql_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_age;
    Button btn_viewAll, btn_add;
    Switch isActive;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        btn_viewAll = findViewById(R.id.btn_ViewAll);
        btn_add = findViewById(R.id.btn_add);
        isActive = findViewById(R.id.sw_active);
        listView = findViewById(R.id.iv_customer_List);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), isActive.isChecked());
                } catch (Exception E) {
                    Toast.makeText(MainActivity.this, "add Error creating customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(customerModel);

                Toast.makeText(MainActivity.this, "success" + success, Toast.LENGTH_SHORT).show();
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<CustomerModel> everyOne = dataBaseHelper.getEveryone();

                ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this,android.R.layout.simple_list_item_1,everyOne);
                listView.setAdapter(customerArrayAdapter);
                // updated
                //Demo lela version
            }
        });
    }
}