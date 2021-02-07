package com.example.sanjeevani.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanjeevani.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IotActivity extends AppCompatActivity {

    private TextView txtname,valname,txttemp,valtemp;
    private ProgressBar progress;
    private Button btnstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot);
        initialiseWidgets();
        //fetchName();
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);
                fetchName();
            }
        });
    }

    private void fetchName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid());
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    progress.setVisibility(View.GONE);
                    String name = snapshot.child("name").getValue(String.class);
                    if (name!=null){
                        valname.setText(name);
                    }else {
                        valname.setText("Loading...");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(IotActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(IotActivity.this,"User not found",Toast.LENGTH_SHORT).show();
        }
    }

    private void initialiseWidgets() {
        txtname = (TextView)findViewById(R.id.textName);
        valname = (TextView)findViewById(R.id.valName);
        txttemp = (TextView)findViewById(R.id.texttemp);
        valtemp =(TextView) findViewById(R.id.valtemp);
        progress = (ProgressBar)findViewById(R.id.progressBar);
        progress.setVisibility(View.GONE);
        btnstart = (Button)findViewById(R.id.btnstart);
    }
}
