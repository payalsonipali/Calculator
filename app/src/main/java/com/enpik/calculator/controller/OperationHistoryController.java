package com.enpik.calculator.controller;

import com.enpik.calculator.adapter.ListViewAdapter;
import com.enpik.calculator.model.OperationHistoryModel;
import com.enpik.calculator.service.OperationHistoryService;

import java.util.ArrayList;

public interface OperationHistoryController {
    public void getDataFromFirebase(ListViewAdapter adapter);
    public void addDataToFirebase(String operation, String result);
    public ArrayList<OperationHistoryModel> getOperationHistoryList();
}
