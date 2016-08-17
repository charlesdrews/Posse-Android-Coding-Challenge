package com.charlesdrews.charlesdrewsdemoapp.util;

import android.support.annotation.NonNull;

/**
 * Format strings
 *
 * Created by charlie on 8/16/16.
 */
public class StringFormatUtil {

    /**
     * Formats a string of digits as a phone number. Non-digit characters will be removed.
     * @param phoneNum String
     * @return "+## (###) ###-####" or "(###) ###-####" or "###-####", depending on number of digits
     */
    public static String formatPhoneNumber(@NonNull String phoneNum) {
        // Remove non-digits and format nicely
        phoneNum = phoneNum.replaceAll("\\D","");

        if (phoneNum.length() > 10) {
            return String.format("+%s (%s) %s-%s", phoneNum.substring(0, phoneNum.length() - 10),
                    phoneNum.substring(phoneNum.length() - 10, phoneNum.length() - 7),
                    phoneNum.substring(phoneNum.length() - 7, phoneNum.length() - 4),
                    phoneNum.substring(phoneNum.length() - 4));
        } else if (phoneNum.length() == 10) {
            return String.format("(%s) %s-%s", phoneNum.substring(0, 3),
                    phoneNum.substring(3, 6), phoneNum.substring(6));
        } else if (phoneNum.length() > 4) {
            return String.format("%s-%s", phoneNum.substring(0, phoneNum.length() - 4),
                    phoneNum.substring(phoneNum.length() - 4));
        } else {
            return phoneNum;
        }
    }
}
