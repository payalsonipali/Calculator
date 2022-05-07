package com.enpik.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    TextView testScreen;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDivide, buttonMultiple, buttonMinus, buttonAdd, buttonEqual, buttonHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testScreen = findViewById(R.id.text);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonMultiple = findViewById(R.id.buttonMultiple);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonHistory = findViewById(R.id.buttonHistory);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}