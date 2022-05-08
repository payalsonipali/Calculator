package com.enpik.calculator.service;

import com.enpik.calculator.adapter.ListViewAdapter;
import com.enpik.calculator.model.OperationHistoryModel;

import java.util.ArrayList;

public interface OperationHistoryService {
    void getDataFromFirebase(ListViewAdapter adapter);
    void addDataToFirebase(String operation, String result);
    ArrayList<OperationHistoryModel> getOperationHistoryList();
}
