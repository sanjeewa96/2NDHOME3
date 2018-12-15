package com.example.sk.a2ndhome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity implements View.OnClickListener{

    private EditText registrationemail;
    private EditText registrationpassword;
    private Button registrationregister;
    private TextView registrationsignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        registrationemail=(EditText)findViewById(R.id.registrationemail);
        registrationpassword=(EditText)findViewById(R.id.registrationpassword);
        registrationregister=(Button)findViewById(R.id.registrationregister);
        registrationsignin=(TextView) findViewById(R.id.registrationsignin);


        registrationregister.setOnClickListener(this);
        registrationsignin.setOnClickListener(this);

        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));
        }




    }

    private void registeruser()
    {
        String email=registrationemail.getText().toString().trim();
        String password=registrationpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please Enter Your Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                                finish();
                                startActivity(new Intent(getApplicationContext(), Profile.class));

                        }
                        else
                        {
                            Toast.makeText(Registration.this,"Could not register, Please Try Again", Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if(view==registrationregister)
        {
            registeruser();
        }
        if(view==registrationsignin)
        {
            startActivity(new Intent(this, Login.class));
        }

    }
}
