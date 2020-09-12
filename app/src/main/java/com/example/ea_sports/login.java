package com.example.ea_sports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText email,password;
    Button login;
    TextView forgotpwd;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.tv_emailid_login);
        password= findViewById(R.id.tv_password_login);
        login= findViewById(R.id.btn_login1);
        forgotpwd= findViewById(R.id.tv_forgotpwd);
        firebaseAuth= FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    showProgressBar(true);
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if(task.isSuccessful()){
                                        if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                            Toast.makeText(com.example.ea_sports.login.this,"Login Successful",Toast.LENGTH_LONG).show();
                                            Intent intent= new Intent(com.example.ea_sports.login.this,homeActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(com.example.ea_sports.login.this,"PLease Verify your email.",Toast.LENGTH_LONG).show();
                                        }
                                    }else {
                                        Toast.makeText(com.example.ea_sports.login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                }
            }
        });

        forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(com.example.ea_sports.login.this,forgotpassword.class);
                startActivity(intent1);
            }
        });
    }
    private void showProgressBar(boolean b) {
        progressDialog= new ProgressDialog(login.this);
        if(b==true){
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
        }
        if(b==false){
            progressDialog.dismiss();
        }
    }
}
