package com.kontrakanelite.healthivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegistActivity extends AppCompatActivity {

    private Button btnRegist;
    private EditText Name, Email, Age;
    private EditText Password,ConfirmPassword;
    private TextView linkToLogin;

    DatabaseReference databaseRef;

    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        databaseRef = FirebaseDatabase.getInstance().getReference("user");

        //init views
        Name = findViewById(R.id.etNameRegist);
        Email = findViewById(R.id.etEmailRegist);
        Age = findViewById(R.id.etAgeRegist);
        Password = findViewById(R.id.etPasswordRegist);
        ConfirmPassword = findViewById(R.id.etRetypePasswordRegist);
        btnRegist = findViewById(R.id.btnRegist);
        linkToLogin = findViewById(R.id.tvToLogin);

        //event listener
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name.setEnabled(false);
                Email.setEnabled(false);
                Age.setEnabled(false);
                Password.setEnabled(false);
                ConfirmPassword.setEnabled(false);
                btnRegist.setEnabled(false);
                linkToLogin.setEnabled(false);
                SignUp();
            }
        });

        linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        }

    private void SignUp(){
        String name = Name.getText().toString().trim();
        String email = Email.getText().toString().trim();
        int age = Integer.valueOf(Age.getText().toString());
        String pass = Password.getText().toString().trim();
        String confirmPass = ConfirmPassword.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)&&pass.length()>5&&email.matches(emailPattern)&&pass.equals(confirmPass)){
            cekEmail();
        }else{
            if (TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)){
                Toast.makeText(this, "Email must be filled!",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(pass)&&!TextUtils.isEmpty(email)){
                Toast.makeText(this, "Password must be filled!",Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(email)&&TextUtils.isEmpty(pass)){
                Toast.makeText(this, "Please fill the email and password fields!",Toast.LENGTH_SHORT).show();
            }else if (pass.length()<6){
                Toast.makeText(this, "Password must have at least 6 characters",Toast.LENGTH_SHORT).show();
            }else if (!confirmPass.equals(pass)){
                Toast.makeText(this, "Please re-type password correctly!",Toast.LENGTH_SHORT).show();
            }else if(!email.matches(emailPattern)){
                Toast.makeText(this, "email format : example@domain.com",Toast.LENGTH_SHORT).show();
            }
            Name.setEnabled(true);
            Email.setEnabled(true);
            Age.setEnabled(true);
            Password.setEnabled(true);
            ConfirmPassword.setEnabled(true);
            btnRegist.setEnabled(true);
            linkToLogin.setEnabled(true);
        }
    }

    private void cekEmail(){
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                String email = Email.getText().toString().trim();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    User user = userSnapshot.getValue(User.class);
                    if (user.getEmail().equals(email)){
                        userList.add(user);
                    }
                }
                if (userList.size()>0){
                    addUser(false);
                }else{
                    addUser(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addUser(boolean status){
        String name = Name.getText().toString().trim();
        String email = Email.getText().toString().trim();
        int age = Integer.valueOf(Age.getText().toString());
        String pass = Password.getText().toString().trim();

        if (status==true){
            String id = databaseRef.push().getKey();

            User user = new User(id,name,email,age,pass);

            databaseRef.child(id).setValue(user);

            Name.getText().clear();
            Email.getText().clear();
            Age.getText().clear();
            Password.getText().clear();
            ConfirmPassword.getText().clear();

            closeKeyboard();
            Toast.makeText(this,"Registration success!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Email have been used! Use another email!",Toast.LENGTH_SHORT).show();
        }

        Name.setEnabled(true);
        Email.setEnabled(true);
        Age.setEnabled(true);
        Password.setEnabled(true);
        ConfirmPassword.setEnabled(true);
        btnRegist.setEnabled(true);
        linkToLogin.setEnabled(true);
    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
