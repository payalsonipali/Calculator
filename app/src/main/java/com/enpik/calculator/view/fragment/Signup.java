package com.enpik.calculator.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enpik.calculator.R;
import com.enpik.calculator.utils.Validator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends Fragment {
    EditText userName, password, confirmPassword;
    Button signUp;
    TextView loginText;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Context currentContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.currentContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup, container, false);
        userName = view.findViewById(R.id.userName);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        signUp = view.findViewById(R.id.signupBotton);
        loginText = view.findViewById(R.id.loginText);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout, new Login()).commit();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(userName.getText().toString(),password.getText().toString(), confirmPassword.getText().toString());
            }
        });
        return view;
    }

    private void signUp(String email, String password, String confirmPassword){
        Validator validator = new Validator();
        if(!validator.isValidEmail(email)){
            Toast.makeText(currentContext, "Enter valid email",
                    Toast.LENGTH_SHORT).show();
        } else if(!validator.isValidPassword(password)){
            Toast.makeText(currentContext, "Password should be greater than 6",
                    Toast.LENGTH_SHORT).show();
        } else if(!password.equals(confirmPassword)){
            Toast.makeText(currentContext, "Password and confirm password not match",
                    Toast.LENGTH_SHORT).show();
        } else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity)currentContext, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                getFragmentManager().beginTransaction().replace(R.id.frameLayout, new Login()).commit();
                            } else {
                                Toast.makeText(currentContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}
