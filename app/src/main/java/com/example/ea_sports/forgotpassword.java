package com.example.ea_sports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    Button forgotpassword;
    EditText email;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        email= findViewById(R.id.tv_emailid_forgot);
        forgotpassword= findViewById(R.id.btn_forgot);

        firebaseAuth= FirebaseAuth.getInstance();

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar(true);
                firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(com.example.ea_sports.forgotpassword.this,"Password reset email has been sent to your email.",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(com.example.ea_sports.forgotpassword.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
    private void showProgressBar(boolean b) {
        progressDialog= new ProgressDialog(forgotpassword.this);
        if(b==true){
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
        }
        if(b==false){
            progressDialog.dismiss();
        }
    }
}
