package com.edu.pk.gulehri.instagram;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    TextView textView;
    SourceClass mSourceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        textView = findViewById(R.id.HomeUser);
        mSourceClass = new SourceClass(this);

        String Username = getIntent().getExtras().getString("USER");
        textView.setText(Username);
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