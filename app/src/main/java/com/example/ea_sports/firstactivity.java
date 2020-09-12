package com.example.ea_sports;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class firstactivity extends AppCompatActivity {

    TextView tv_progressbar;
    Button register;
    Button login;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        tv_progressbar= findViewById(R.id.tv_progressbar);
        register= findViewById(R.id.btn_register);
        login= findViewById(R.id.btn_login);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser= firebaseAuth.getCurrentUser();

        showProgressBar(true);
        if(firebaseUser!=null && firebaseUser.isEmailVerified()){
            Intent intent= new Intent(firstactivity.this,homeActivity.class);
            progressDialog.dismiss();
            startActivity(intent);
        }
        else{
            progressDialog.dismiss();
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(firstactivity.this,register.class);
                startActivity(intent1);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2= new Intent(firstactivity.this,login.class);
                startActivity(intent2);
            }
        });
    }

    private void showProgressBar(boolean b) {
        progressDialog= new ProgressDialog(firstactivity.this);
        if(b==true){
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
        }
        if(b==false){
            progressDialog.dismiss();
        }
    }
}
