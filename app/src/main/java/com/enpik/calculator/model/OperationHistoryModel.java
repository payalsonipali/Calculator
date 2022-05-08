package com.enpik.calculator.model;

public class OperationHistoryModel {
    private String operation;
    private String result;

    public String getOperation() {
        return operation;
    }

    public String getResult() {
        return result;
    }

    public OperationHistoryModel(String operation, String result) {
        this.operation = operation;
        this.result = result;
    }
}
