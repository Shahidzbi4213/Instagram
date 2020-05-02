package com.edu.pk.gulehri.instagram;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;


public class SignUp extends AppCompatActivity {

    LinearLayout linearLayout;
    EditText email, name, username, password, confirmpassword;
    TextInputLayout signpwd, signconfirmpwd;
    Button signup;

    public SourceClass mSourceClass;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile
                    ("^" +
                            "(?=.*[a-zA-Z])" +      //any letter
                            "(?=.*[@#$%^&+=])" +    //at least 1 special character
                            "(?=\\S+$)" +           //no white spaces
                            ".{4,}" +               //at least 4 characters
                            "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        mSourceClass = new SourceClass(this);

        try {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        } catch (NullPointerException ignored) {
        }


        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        signpwd = findViewById(R.id.signpwd);
        linearLayout = findViewById(R.id.laymain);
        signconfirmpwd = findViewById(R.id.signconfirmpwd);
        confirmpassword = findViewById(R.id.confirmpassword);


        signpwd.setHintEnabled(false);
        signpwd.setHintAnimationEnabled(false);
        signconfirmpwd.setHintEnabled(false);
        signconfirmpwd.setHintAnimationEnabled(false);


        signup.setOnClickListener(v -> {


            String myemail = email.getText().toString();
            String myname = name.getText().toString();
            String myuser = username.getText().toString();
            String mypwd = password.getText().toString();
            String myconfirmpwd = confirmpassword.getText().toString();


            if (myemail.equalsIgnoreCase("")) {
                email.setError("please enter email");//it gives user to info message
            } else if (!Patterns.EMAIL_ADDRESS.matcher(myemail).matches()) {
                email.setError("Invalid Email Address");
            } else if (myname.equalsIgnoreCase("")) {
                name.setError("please enter name");//it gives user to info message
            } else if (myuser.equalsIgnoreCase("")) {
                username.setError("please enter username");//it gives user to info message
            } else if (mypwd.equalsIgnoreCase("")) {
                password.setError("please enter password");//it gives user to info message
            } else if (!PASSWORD_PATTERN.matcher(mypwd).matches()) {
                password.setError("Password is too weak");
            } else if (!mypwd.equals(myconfirmpwd)) {
                confirmpassword.setError("Password Does Not Match");
            } else {
                  mSourceClass.insertData(myemail, myname, myuser, mypwd);
                  Intent intent = new Intent(SignUp.this, Login.class);
                 startActivity(intent);
            }

        });
    }


    public void facebook(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en-gb.facebook.com/login/"));

        startActivity(intent);

    }

    public void logIn(View view) {
        Intent intent = new Intent(this, Login.class);
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

