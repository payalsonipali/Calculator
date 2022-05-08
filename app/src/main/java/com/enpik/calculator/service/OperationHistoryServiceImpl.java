package com.enpik.calculator.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.enpik.calculator.adapter.ListViewAdapter;
import com.enpik.calculator.model.OperationHistoryModel;
import com.enpik.calculator.repository.OperationHistoryRepository;
import com.enpik.calculator.utils.FirebaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OperationHistoryServiceImpl implements OperationHistoryService {
    OperationHistoryRepository operationHistoryRepository;
    FirebaseService firebaseService;

    public OperationHistoryServiceImpl() {
        operationHistoryRepository = new OperationHistoryRepository();
        firebaseService = FirebaseService.getInstance();
    }

    @Override
    public void getDataFromFirebase(ListViewAdapter adapter) {
        firebaseService.getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    OperationHistoryModel operationHistoryModel = new OperationHistoryModel(dataSnapshot.child("operation").getValue().toString(),dataSnapshot.child("result").getValue().toString());
                    operationHistoryRepository.getOperationList().add(operationHistoryModel);
                }
                if(adapter != null){
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void addDataToFirebase(String operation, String result) {
        firebaseService.getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                OperationHistoryModel operationHistoryModel = new OperationHistoryModel(operation,result);
                if(operationHistoryRepository.getOperationList().size() >= 10){
                    operationHistoryRepository.removeFromFirstIndex();
                }
                operationHistoryRepository.addOperationToList(operationHistoryModel);
                firebaseService.getRef().setValue(operationHistoryRepository.getOperationList());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public ArrayList<OperationHistoryModel> getOperationHistoryList(){
        return operationHistoryRepository.getOperationList();
    }
}
