package com.enpik.calculator.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import com.enpik.calculator.R;
import com.enpik.calculator.adapter.ListViewAdapter;
import com.enpik.calculator.controller.OperationHistoryControllerImpl;
import com.enpik.calculator.model.OperationHistoryModel;
import com.enpik.calculator.utils.Calculator;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import org.apache.commons.lang3.StringUtils;


public class MainActivity extends AppCompatActivity {
    TextView textScreen;
    LinearLayout bottomSheetLayout;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDivide, buttonMultiply, buttonSubstraction, buttonAdd, buttonEqual,clearButton, backButton, buttonHistory;
    String showText= "";
    OperationHistoryControllerImpl operationHistoryController = new OperationHistoryControllerImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textScreen = findViewById(R.id.showText);
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
        buttonMultiply = findViewById(R.id.buttonMultiple);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubstraction = findViewById(R.id.buttonMinus);
        buttonHistory = findViewById(R.id.buttonHistory);
        clearButton = findViewById(R.id.clearButton);
        backButton = findViewById(R.id.backButton);
        buttonEqual = findViewById(R.id.buttonEqual);
        bottomSheetLayout = findViewById(R.id.bottomSheetLayout);
        operationHistoryController.getDataFromFirebase(null);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumberToOperand("9");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperation("+");
            }
        });
        buttonSubstraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperation("-");
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperation("*");
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperation("/");
            }
        });
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayBottomSheet();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showText = "";
                textScreen.setText(showText);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showText = StringUtils.chop(showText);
                textScreen.setText(showText);
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateExpression();
            }
        });
    }

    private void addNumberToOperand(String number){
        showText=showText+number;
        textScreen.setText(showText);
    }

    private void addOperation(String operator){
        showText=showText+operator;
        textScreen.setText(showText);
    }

    public void evaluateExpression(){
        Calculator calculator = new Calculator(this, showText);
        OperationHistoryModel operationHistoryModel = calculator.initiateCalculationOfExpression(textScreen);
        if( operationHistoryModel != null){
            showText = String.valueOf(operationHistoryModel.getResult());
            textScreen.setText(showText);
            uploadHistoryOnFirebase(operationHistoryModel.getOperation(), operationHistoryModel.getResult());
        } else{
            showText = "";
            textScreen.setText(showText);
        }
    }

    public void uploadHistoryOnFirebase(String operation, String result){
        operationHistoryController.addDataToFirebase(operation, result);
    }

    public void displayBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);;
        View layout = LayoutInflater.from(MainActivity.this).inflate(R.layout.history, bottomSheetLayout);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        ListView listView = layout.findViewById(R.id.historyListView);
        ListViewAdapter adapter = new ListViewAdapter(this, operationHistoryController.getOperationHistoryList());
        listView.setAdapter(adapter);
        bottomSheetDialog.show();
    }

}

