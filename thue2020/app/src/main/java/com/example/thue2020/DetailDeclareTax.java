package com.example.thue2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thue2020.Service.ClientDeclateTax;
import com.example.thue2020.Service.ConvertJson;
import com.example.thue2020.modal.declareTax;

import java.sql.Date;

public class DetailDeclareTax extends AppCompatActivity {

    TextView tvDetailId, tvDetailTaxPeriod, tvDetailTimes, tvDetailFax, tvDetailTotalIncome,
            tvDetailYourSefl, tvDetailDependentPerson, tvDetailCharity, tvDetailInsurrance,
            tvDetailDateCreate, tvDetailPaymentDate, tvDetailMoney;

    Button btnDetailRemove, btnDetailPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_declare_tax);

        tvDetailId = findViewById(R.id.tvDetailId);
        tvDetailTaxPeriod = findViewById(R.id.tvDetailTaxPeriod);
        tvDetailTimes = findViewById(R.id.tvDetailTimes);
        tvDetailFax = findViewById(R.id.tvDetailFax);
        tvDetailTotalIncome = findViewById(R.id.tvDetailTotalIncome);
        tvDetailYourSefl = findViewById(R.id.tvDetailYourSefl);
        tvDetailDependentPerson = findViewById(R.id.tvDetailDependentPerson);
        tvDetailCharity = findViewById(R.id.tvDetailCharity);
        tvDetailInsurrance = findViewById(R.id.tvDetailInsurrance);
        tvDetailDateCreate = findViewById(R.id.tvDetailDateCreate);
        tvDetailPaymentDate  = findViewById(R.id.tvDetailPaymentDate);
        tvDetailMoney = findViewById(R.id.tvDetailMoney);

        btnDetailPay = findViewById(R.id.btnDetailPay);
        btnDetailRemove = findViewById(R.id.btnDetailRemove);

        Intent intent = getIntent();
        String json = intent.getStringExtra("declareTax");
        final declareTax dt = ConvertJson.fromJson(json, declareTax.class);

        SharedPreferences pref = getSharedPreferences("SECURITY", MODE_PRIVATE);
        final String token = pref.getString("token", "");

        tvDetailId.setText("ID:" + String.valueOf(dt.getId()));
        tvDetailTaxPeriod.setText("TAX PERIOD:" + dt.getTaxPeriod().toString());
        tvDetailTimes.setText("TIMES:" + String.valueOf(dt.getTimes()));
        tvDetailFax.setText("FAX:" + dt.getFax());
        tvDetailTotalIncome.setText("TOTAL INCOME:" + String.valueOf(dt.getTotalIncome()));
        tvDetailYourSefl.setText("YOURSEFL:" + String.valueOf(dt.getMinusYourSefl()));
        tvDetailDependentPerson.setText("DEPENDENT PERSON:" + String.valueOf(dt.getMinusDependentPerson()));
        tvDetailCharity.setText("CHARITY:" + String.valueOf(dt.getMinusCharity()));
        tvDetailInsurrance.setText("INSURRANCE:" + String.valueOf(dt.getMinusInsurance()));
        tvDetailDateCreate.setText("DATE CREATE:" + dt.getDateCreate().toString());
        String datePay = "chưa trả";
        if (!dt.getPaymentDate().equals(new Date(0))) datePay = dt.getPaymentDate().toString();
        tvDetailPaymentDate.setText("DATE PAY:" + datePay);
        tvDetailMoney.setText("MONEY:" + String.valueOf(dt.caculatorTaxPay()));

        btnDetailRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientDeclateTax.remove(token, dt.getId());
            }
        });

        btnDetailPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientDeclateTax.pay(token, dt.getId());
            }
        });

    }
}
