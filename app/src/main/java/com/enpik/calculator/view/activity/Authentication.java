package com.enpik.calculator.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.enpik.calculator.view.fragment.Login;
import com.enpik.calculator.R;
import com.google.firebase.FirebaseApp;

public class Authentication extends AppCompatActivity {
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        FirebaseApp.initializeApp(getApplicationContext());
        getSupportActionBar().hide();
        frameLayout = findViewById(R.id.frameLayout);
        getFragmentManager().beginTransaction().replace(R.id.frameLayout, new Login()).commit();

    }
}