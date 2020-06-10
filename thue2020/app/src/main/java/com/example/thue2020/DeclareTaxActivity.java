package com.example.thue2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thue2020.Service.ClientDeclateTax;
import com.example.thue2020.modal.declareTax;

import java.sql.Date;

public class DeclareTaxActivity extends AppCompatActivity {

    EditText etTaxPeriod, etTimes, etFax, etTotalIncome, etYourSefl,
            etDependentPerson, etCharity, etInsurrance;
    Button btnDeclareTaxNext;
    TextView tvStatusDeclareTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declare_tax);

        etTaxPeriod = findViewById(R.id.etTaxPeriod);
        etTimes = findViewById(R.id.etTimes);
        etFax = findViewById(R.id.etFax);
        etTotalIncome = findViewById(R.id.etTotalIncome);
        etYourSefl = findViewById(R.id.etYourSefl);
        etDependentPerson = findViewById(R.id.etDependentPerson);
        etCharity = findViewById(R.id.etCharity);
        etInsurrance = findViewById(R.id.etInsurrance);

        btnDeclareTaxNext = findViewById(R.id.btnDeclareTaxNext);
        tvStatusDeclareTax  = findViewById(R.id.tvStatusDeclareTax);

        btnDeclareTaxNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                declareTax dt = new declareTax();

                try {
                    dt.setTaxPeriod(Date.valueOf(etTaxPeriod.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    tvStatusDeclareTax.setText("taxperiod sai");
                    return;
                }

                try {
                    dt.setTimes(Byte.valueOf(etTimes.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    tvStatusDeclareTax.setText("times sai");
                    return;
                }

                dt.setFax(etFax.getText().toString());

                try {
                    dt.setTotalIncome(Long.valueOf(etTotalIncome.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    tvStatusDeclareTax.setText("totalIncome sai");
                    return;
                }

                dt.setDateCreate(new Date(System.currentTimeMillis()));
                dt.setPaymentDate(new Date(0));

                SharedPreferences pref = getSharedPreferences("SECURITY", MODE_PRIVATE);
                String token = pref.getString("token", "");

                if(ClientDeclateTax.add(dt, token)) tvStatusDeclareTax.setText("thêm thành công");
                else tvStatusDeclareTax.setText("thêm thất bại");

            }
        });
    }
}
