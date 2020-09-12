package com.example.ea_sports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText email,password,confirmpassword;
    Button register;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.tv_emailid_register);
        password= findViewById(R.id.tv_password_register);
        confirmpassword= findViewById(R.id.tv_passwordConfrim_register);
        register= findViewById(R.id.btn_register1);

        firebaseAuth= FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    if(password.getText().toString().equals(confirmpassword.getText().toString())){
                        showProgressBar(true);
                        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),
                                password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //showProgressBar(false);
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(com.example.ea_sports.register.this,"Registration Successful. PLease check Email for verification.",Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(com.example.ea_sports.register.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }else {
                                    Toast.makeText(com.example.ea_sports.register.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(com.example.ea_sports.register.this,"Password doesn't  Match",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private void showProgressBar(boolean b) {
        progressDialog= new ProgressDialog(register.this);
        if(b==true){
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
        }
        if(b==false){
            progressDialog.dismiss();
        }
    }
}
