package com.example.thue2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

import com.example.thue2020.Service.ClientTaxPayer;
import com.example.thue2020.Service.ConvertJson;
import com.example.thue2020.modal.*;

public class SignUpTaxPayerActivity extends AppCompatActivity {

    EditText etTaxCode, etPassword, etEmail, etStartDay, etEndDay, etTaxAuthorities, etBank, etIdAccountBank, etDescription;
    Button btnNext;
    TextView tvStatusTaxPayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_tax_payer);

        etTaxCode = findViewById(R.id.etTaxCode);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etStartDay = findViewById(R.id.etStartDay);
        etEndDay = findViewById(R.id.etEndDay);
        etTaxAuthorities = findViewById(R.id.etTaxAuthorities);
        etBank = findViewById(R.id.etBank);
        etIdAccountBank = findViewById(R.id.etIdAccountBank);
        etDescription = findViewById(R.id.etDescription);
        btnNext = findViewById(R.id.btnPayerNext);
        tvStatusTaxPayer = findViewById(R.id.tvStatusPayer);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long taxCode;
                try {
                    taxCode = Long.valueOf(etTaxCode.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpTaxPayerActivity.this, "taxCode là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = etPassword.getText().toString();
                if (password.equals("")) {
                    Toast.makeText(SignUpTaxPayerActivity.this, "password không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = etEmail.getText().toString();
                if (email.equals("")) {
                    Toast.makeText(SignUpTaxPayerActivity.this, "email không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                Date startDay;
                try {
                    startDay = Date.valueOf(etStartDay.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpTaxPayerActivity.this, "startDay sai", Toast.LENGTH_SHORT).show();
                    return;
                }

                Date endDay = null;
                String sEndDay = etEndDay.getText().toString();
                try {
                    endDay = Date.valueOf(sEndDay);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!sEndDay.equals("")) Toast.makeText(SignUpTaxPayerActivity.this, "endDay sai", Toast.LENGTH_SHORT).show();
                }

                String taxAuthorities = etTaxAuthorities.getText().toString();
                if (taxAuthorities.equals("")) {
                    Toast.makeText(SignUpTaxPayerActivity.this, "taxAuthorities không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                String bank = etBank.getText().toString();
                if (bank.equals("")) {
                    Toast.makeText(SignUpTaxPayerActivity.this, "bank không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                long idAccountBank;
                try {
                    idAccountBank = Long.valueOf(etIdAccountBank.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpTaxPayerActivity.this, "idAccountBank là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                String description = etDescription.getText().toString();
                if (description.equals("")) {
                    Toast.makeText(SignUpTaxPayerActivity.this, "description không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                people p = ConvertJson.fromJson(getIntent().getStringExtra("people"), people.class);

                taxPayer tp = new taxPayer(p, taxCode, password, email, startDay, endDay, taxAuthorities, bank, idAccountBank, description, 0, null);

                if (ClientTaxPayer.signUp(tp))
                {
                    Toast.makeText(SignUpTaxPayerActivity.this, "đăng ký thành công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
                else tvStatusTaxPayer.setText("không đăng ký được");

            }
        });

        
    }
}
