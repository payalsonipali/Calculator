package com.enpik.calculator.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {
    FirebaseDatabase database;
    public static FirebaseService firebaseService = null;
    DatabaseReference ref;
    private FirebaseService(){
        database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            ref = database.getReference().child("History").child(user.getUid());
        }
    }

    public static FirebaseService getInstance(){
        if(firebaseService == null){
            return new FirebaseService();
        }
        return firebaseService;
    }

    public DatabaseReference getRef() {
        return ref;
    }
}
