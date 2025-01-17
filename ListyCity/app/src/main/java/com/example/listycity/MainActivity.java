package com.example.listycity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView citylist;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    String selectedCity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = findViewById(R.id.addButton);
        Button delButton = findViewById(R.id.delButton);
        EditText editText = findViewById(R.id.editTextText);
        Button confirmButton = findViewById(R.id.confirm_button);
        dataList = new ArrayList<>();



        addButton.setOnClickListener(v -> {
            editText.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.VISIBLE);

            confirmButton.setOnClickListener(v1 -> {
                String cityName = editText.getText().toString();

                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                    editText.setText("");
                    editText.setVisibility(View.GONE);
                    confirmButton.setVisibility(View.GONE);}
            });
        });

        citylist = findViewById(R.id.city_list);

        citylist.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
                });
        delButton.setOnClickListener(v -> {
            if (!dataList.isEmpty()) {
                if (selectedCity != null) {
                dataList.remove(selectedCity);
                selectedCity = null;
                cityAdapter.notifyDataSetChanged();
            }}

        });

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        citylist.setAdapter(cityAdapter);

    }
}