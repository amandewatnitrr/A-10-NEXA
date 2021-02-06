package com.example.sanjeevani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText txtname,txtmail,txtphone,txtpass;
    private Button btnLogin,btnReg;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);
        initWidgets();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtmail.getText().toString();
                String password = txtpass.getText().toString();
                String name = txtname.getText().toString();
                String phone = txtphone.getText().toString();
                if (validateInput(name, phone, email,password)){
                    login(name, phone, email,password);
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtmail.getText().toString();
                String password = txtpass.getText().toString();
                String name = txtname.getText().toString();
                String phone = txtphone.getText().toString();
                if (validateInput(name, phone, email,password)){
                    register(name, phone, email,password);
                }
            }
        });

    }

    private void register(String name, final String phone, final String email, final String password) {
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, " User registered successfully !",Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login(String name, String phone, String email, String password) {
        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);

                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void initWidgets() {
        txtname = (EditText)findViewById(R.id.textName);
        txtphone = (EditText)findViewById(R.id.textMbno);
        txtmail = (EditText)findViewById(R.id.textMail);
        txtpass = (EditText)findViewById(R.id.textPass);
        btnLogin = (Button)findViewById(R.id.btnlogin);
        btnReg = (Button)findViewById(R.id.btnReg);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
    }

    private boolean validateInput(String name, String phone, String email, String password){
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(email == null || email.trim().length() == 0){
            Toast.makeText(this, "E-mail is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(name == null || name.trim().length() == 0){
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phone == null || phone.trim().length() == 0){
            Toast.makeText(this, "Phone is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
