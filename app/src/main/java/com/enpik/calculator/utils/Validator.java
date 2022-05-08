package com.enpik.calculator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(String password){
        if(password.length()>6)
            return true;
        return false;
    }
}
