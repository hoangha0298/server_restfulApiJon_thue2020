package com.example.thue2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

import com.example.thue2020.Service.ConvertJson;
import com.example.thue2020.modal.*;

public class SignUpPeopleActivity extends AppCompatActivity {

    EditText etIdCard, etName, etDateOfBirth, etSex, etAddress, etNumberPhone;
    Button btnNext;
    TextView tvStatusPeople;
    final int signUpTaxPayer = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_people);

        etIdCard = findViewById(R.id.etIdCard);
        etName = findViewById(R.id.etName);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        etSex = findViewById(R.id.etSex);
        etAddress = findViewById(R.id.etAddress);
        etNumberPhone = findViewById(R.id.etNumberPhone);
        btnNext = findViewById(R.id.btnPeopleNext);
        tvStatusPeople = findViewById(R.id.tvStatusPeople);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long idCard;
                try {
                    idCard = Long.valueOf(etIdCard.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpPeopleActivity.this, "idCard là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = etName.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(SignUpPeopleActivity.this, "name không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                Date dateOfBirth = null;
                String sDateOfBirth = etDateOfBirth.getText().toString();
                try {
                    dateOfBirth = Date.valueOf(sDateOfBirth);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!sDateOfBirth.equals("")) Toast.makeText(SignUpPeopleActivity.this, "dateOfBirth sai", Toast.LENGTH_SHORT).show();
                }

                byte sex = 0;
                try {
                    sex = Byte.valueOf(etSex.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String address = etAddress.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(SignUpPeopleActivity.this, "address không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                long numberPhone;
                try {
                    numberPhone = Long.valueOf(etNumberPhone.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpPeopleActivity.this, "numberPhone là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                people p = new people(idCard, name, dateOfBirth, sex, address, numberPhone);

                Intent intent = new Intent(SignUpPeopleActivity.this, SignUpTaxPayerActivity.class);
                intent.putExtra("people", ConvertJson.toJson(p));
                startActivityForResult(intent, signUpTaxPayer);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println(requestCode);
        System.out.println(requestCode);
        if (requestCode == signUpTaxPayer) {
            if (resultCode == RESULT_OK) {
                finish();
            }

        }

    }

}
