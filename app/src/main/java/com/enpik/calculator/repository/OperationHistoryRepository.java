package com.enpik.calculator.repository;

import com.enpik.calculator.model.OperationHistoryModel;
import java.util.ArrayList;

public class OperationHistoryRepository {
    ArrayList<OperationHistoryModel> operationList;

    public OperationHistoryRepository() {
        operationList = new ArrayList<>();
    }

    public ArrayList<OperationHistoryModel> getOperationList() {
        return operationList;
    }

    public void addOperationToList(OperationHistoryModel operationHistoryModel){
        operationList.add(operationHistoryModel);
    }

    public void removeFromFirstIndex() {
        this.operationList.remove(0);
    }

}
