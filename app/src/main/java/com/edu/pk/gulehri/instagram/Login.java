package com.edu.pk.gulehri.instagram;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import static java.util.jar.Pack200.Packer.PASS;

public class Login extends AppCompatActivity {


    private EditText myUserLog, logpwdfirst;
    private TextInputLayout logpwd;
    private Button login;
    private SourceClass mSourceClass;
    DataBaseOpenHelper helper;
    SQLiteDatabase db;
    Context mContext;

    public String uname, upass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        mSourceClass = new SourceClass(this);
        mSourceClass.DeleteRow();
        mSourceClass.UpdateRow("Gulehri");

        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (NullPointerException e) {
        }

        myUserLog = findViewById(R.id.myUserLog);
        logpwd = findViewById(R.id.logpwd);
        logpwdfirst = findViewById(R.id.logpwdfirst);
        login = findViewById(R.id.login);
        getIntent();

        logpwd.setHintAnimationEnabled(false);
        logpwd.setHintEnabled(false);


        login.setOnClickListener(v -> {

            //From login fields
            uname = myUserLog.getText().toString().trim();
            upass = logpwdfirst.getText().toString().trim();


            //Passing Username and password to the database to compare and validate
            if (uname.isEmpty()) {
                myUserLog.setError("Enter the Username");
            } else if (upass.isEmpty()) {
                logpwdfirst.setError("Enter the Password");
            } else if (mSourceClass.validateUser(uname, upass)) {
                Intent intentsecond = new Intent(Login.this, HomePage.class);
                intentsecond.putExtra("USER", uname);
                startActivity(intentsecond);
            } else {
                myUserLog.setError("UserName is incorrect");
                logpwdfirst.setError("Password is incorrect");
            }
        });

    }

    public void facebook(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en-gb.facebook.com/login/"));

        startActivity(intent);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSourceClass.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSourceClass.close();
    }
}
