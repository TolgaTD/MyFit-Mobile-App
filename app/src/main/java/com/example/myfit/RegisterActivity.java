package com.example.myfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email, phone, gender;
    Button registerButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);
        registerButton = findViewById(R.id.registerButton);
        db = new DatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String mail = email.getText().toString();
                String phoneNum = phone.getText().toString();
                String genderVal = gender.getText().toString();

                boolean checkUser = db.checkUsername(user);
                if (checkUser) {
                    Toast.makeText(getApplicationContext(), "Böyle bir Kullanıcı Mevcut!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insert = db.insertData(user, pass, mail, phoneNum, genderVal);
                    if (insert) {
                        Toast.makeText(getApplicationContext(), "Kayıt Başarılı!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Kayıt Başarısız!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}