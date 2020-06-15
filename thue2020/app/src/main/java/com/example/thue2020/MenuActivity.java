package com.example.thue2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thue2020.Service.ClientTaxPayer;
import com.example.thue2020.Service.ConvertJson;
import com.example.thue2020.modal.taxPayer;

public class MenuActivity extends AppCompatActivity {

    TextView tvInfomationTaxPayer;
    RelativeLayout btnDeclareTax;
    LinearLayout  btnListDeclareTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvInfomationTaxPayer = findViewById(R.id.tvInfomationTaxPayer);
        btnDeclareTax = findViewById(R.id.btnDeclareTax);
        btnListDeclareTax = findViewById(R.id.btnListDeclareTax);

        SharedPreferences pref = getSharedPreferences("SECURITY", MODE_PRIVATE);
        String token = pref.getString("token", "");

        // lấy về thông tin taxpayer lưu vào máy
        taxPayer tp = ClientTaxPayer.getInfomation(token);
        tvInfomationTaxPayer.setText("\tname:" + tp.getName() + "\tbalance:" + tp.getBalance());
        pref.edit().putString("taxpayer", ConvertJson.toJson(tp)).apply();

        btnDeclareTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, DeclareTaxActivity.class);
                startActivity(intent);
            }
        });

        btnListDeclareTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ListDeclareTaxActivity.class);
                startActivity(intent);
            }
        });
    }
}
