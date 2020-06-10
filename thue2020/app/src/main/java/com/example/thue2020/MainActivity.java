package com.example.thue2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thue2020.Service.ClientTaxPayer;
import com.example.thue2020.modal.User;


public class MainActivity extends AppCompatActivity {

    EditText etTaxcode, etPassword;
    Button btnLogin, btnSignUp;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // không có thì client.newCall(request).execute(); sẽ lỗi
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        etTaxcode = findViewById(R.id.etTaxcode);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvStatus = findViewById(R.id.tvStatus);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long taxCode;
                String password;

                try {
                    taxCode = Long.valueOf(etTaxcode.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Tax code là số và không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                password = etPassword.getText().toString();
                if (password.equals("")) {
                    Toast.makeText(MainActivity.this, "password không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                String token = ClientTaxPayer.login(new User(taxCode, password));

                if (token.equals("")) tvStatus.setText("Sai tax code hoặc password");
                else {
                    SharedPreferences pref = getSharedPreferences("SECURITY", MODE_PRIVATE);
                    pref.edit().putString("token", token).apply();

                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpPeopleActivity.class);
                startActivity(intent);
            }
        });

    }
}
