package com.rastech.university.utils;

import java.util.regex.Pattern;

/**
 * @author Ali Shiravand, 10/16/22 10:57 PM
 */
public class EmailValidator {
    public boolean validate(String emailAddress) {
        String regex = "^(.+)@(\\S+)$";
        /*if (emailAddress.length() > 320)
            throw new Exception("Email address length is more than 320");*/
        return Pattern.compile(regex)
                .matcher(emailAddress)
                .matches();
    }
}
