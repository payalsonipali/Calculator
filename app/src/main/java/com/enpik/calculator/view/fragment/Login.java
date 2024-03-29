package com.enpik.calculator.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.enpik.calculator.view.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Fragment{
    EditText userName, password;
    Button login;
    TextView signupText;
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
        View view = inflater.inflate(R.layout.login,container, false);
        userName = view.findViewById(R.id.userName);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.loginButton);
        signupText = view.findViewById(R.id.signupText);
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout, new Signup()).commit();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn(userName.getText().toString(),password.getText().toString());
            }
        });
        return view;
    }

    private void logIn(String email, String password){
        Validator validator = new Validator();
        if(!validator.isValidEmail(email)){
            Toast.makeText(currentContext, "Enter valid email",
                    Toast.LENGTH_SHORT).show();
        } else if(!validator.isValidPassword(password)){
            Toast.makeText(currentContext, "Enter valid password",
                    Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity)currentContext, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(currentContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}
