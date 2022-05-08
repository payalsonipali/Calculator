package com.enpik.calculator.utils;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.enpik.calculator.config.OperationsList;
import com.enpik.calculator.model.OperationHistoryModel;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;

public class Calculator {

    Context context;
    String showText;

    public Calculator(Context context, String showText) {
        this.context = context;
        this.showText = showText;
    }

    public Calculator(){}

    public OperationHistoryModel initiateCalculationOfExpression(TextView textScreen) {
        OperationHistoryModel operationHistoryModel;
        String expressionToEvaluate = showText;
        try {
            String[] splitString = StringUtils.splitByCharacterType(showText);
            if( !checkIfExpressionIsValid(expressionToEvaluate) ){
                showText = "";
                textScreen.setText(showText);
                return null;
            }
            ArrayList<String> listOfChars = new ArrayList<>();
            Collections.addAll(listOfChars, splitString);
            evaluateExpression(listOfChars);
            if(listOfChars.size() == 1){
                Toast.makeText(context, "Successfully Evaluated", Toast.LENGTH_SHORT).show();
                operationHistoryModel = new OperationHistoryModel(expressionToEvaluate, listOfChars.get(0));
                return operationHistoryModel;
            }
        } catch (Exception e) {
            Toast.makeText(context, "Some Unknown Error Occurred", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    public boolean checkIfExpressionIsValid(String splitString) {

        if(OperationsList.operationsList.contains(String.valueOf(splitString.charAt(0)))){
            if(context!=null) Toast.makeText(context, "Expression can't start with an operator", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(splitString.length() <= 2){
            if(context!=null) Toast.makeText(context, "Enter valid expression", Toast.LENGTH_SHORT).show();
            return false;
        }

        for(int i=1; i<splitString.length(); ++i){
            if(OperationsList.operationsList.contains(String.valueOf(splitString.charAt(i))) && OperationsList.operationsList.contains(String.valueOf(splitString.charAt(i-1)))){
                if(context!=null) Toast.makeText(context, "Invalid expression, try again.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public void evaluateExpression(ArrayList<String> listOfChars) {
        for (String s : OperationsList.operationsList){
            performOperation(listOfChars, s);
        }
    }

    public void performOperation(ArrayList<String> expressionList, String operator){
        while (expressionList.contains(operator)) {
            int indexOfOperator = expressionList.indexOf(operator);
            int operand1 = Integer.parseInt(expressionList.get(indexOfOperator - 1));
            int operand2 = Integer.parseInt(expressionList.get(indexOfOperator + 1));
            int result = 0;
            switch (operator){
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
            }
            expressionList.subList(indexOfOperator - 1, indexOfOperator + 2).clear();
            expressionList.add(indexOfOperator - 1, String.valueOf(result));
        }
    }


}
