package com.example.thue2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.thue2020.Service.ClientDeclateTax;
import com.example.thue2020.Service.ConvertJson;
import com.example.thue2020.modal.*;

public class ListDeclareTaxActivity extends AppCompatActivity {

    ListView lvDeclareTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_declare_tax);

        lvDeclareTax = findViewById(R.id.lvDeclareTax);

        SharedPreferences pref = getSharedPreferences("SECURITY", MODE_PRIVATE);
        String token = pref.getString("token", "");
        final ArrayList<declareTax> declareTaxes = ClientDeclateTax.getList(token);

        lvDeclareTax.setAdapter(new CustomListAdapter(this, declareTaxes));

        lvDeclareTax.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object o = lvDeclareTax.getItemAtPosition(position);
                declareTax dt = (declareTax)o;
                Intent intent = new Intent(ListDeclareTaxActivity.this, DetailDeclareTax.class);
                intent.putExtra("declareTax", ConvertJson.toJson(dt));
                startActivity(intent);
            }
        });
    }
}
