package com.example.sk.a2ndhome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private Button buttonlogout;
    private EditText profilename;
    private EditText profileaddress;
    private EditText phonenumber;
    private EditText price;
    private Button savebutton;
    private DatabaseReference databasedepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databasedepartment=FirebaseDatabase.getInstance().getReference("department");

        profilename=(EditText)findViewById(R.id.edittextname);
        profileaddress=(EditText)findViewById(R.id.edittextaddress);
        phonenumber=(EditText)findViewById(R.id.edittextphonenumber);
        price=(EditText)findViewById(R.id.edittextprice);
        savebutton=(Button)findViewById(R.id.buttonsave);





        buttonlogout=(Button)findViewById(R.id.logout);
        buttonlogout.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this, Login.class));
        }

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddetails();
            }
        });

    }

    @Override
    public void onClick(View view) {

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, Login.class));

    }

    private void adddetails()
    {
        String name=profilename.getText().toString().trim();
        String address=profileaddress.getText().toString().trim();
        String phone=phonenumber.getText().toString().trim();
        String rent=price.getText().toString().trim();

        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "You Should Enter a name", Toast.LENGTH_LONG).show();

        }
        else if(TextUtils.isEmpty(address))
        {
            Toast.makeText(this, "You Should Enter the address", Toast.LENGTH_LONG).show();

        }
        else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "You Should Enter a phone number", Toast.LENGTH_LONG).show();

        }
        else if(TextUtils.isEmpty(rent))
        {
            Toast.makeText(this, "You Should Enter the price for month", Toast.LENGTH_LONG).show();

        }
        else
        {
            String id= databasedepartment.push().getKey();
            DepartmentDetails departmentDetails=new DepartmentDetails(id,name,address,phone,rent);
            databasedepartment.child(id).setValue(departmentDetails);
            Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
        }


    }
}
