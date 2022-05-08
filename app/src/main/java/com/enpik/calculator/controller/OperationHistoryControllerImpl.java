package com.enpik.calculator.controller;

import com.enpik.calculator.adapter.ListViewAdapter;
import com.enpik.calculator.model.OperationHistoryModel;
import com.enpik.calculator.service.OperationHistoryServiceImpl;
import java.util.ArrayList;

public class OperationHistoryControllerImpl implements OperationHistoryController{

    OperationHistoryServiceImpl operationHistoryService;

    public OperationHistoryControllerImpl() {
        operationHistoryService = new OperationHistoryServiceImpl();
    }

    @Override
    public void getDataFromFirebase(ListViewAdapter adapter) {
        operationHistoryService.getDataFromFirebase(adapter);
    }

    @Override
    public void addDataToFirebase(String operation, String result) {
        operationHistoryService.addDataToFirebase(operation,result);
    }

    @Override
    public ArrayList<OperationHistoryModel> getOperationHistoryList() {
        return operationHistoryService.getOperationHistoryList();
    }

}
