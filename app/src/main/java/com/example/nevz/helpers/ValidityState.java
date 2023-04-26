package com.example.nevz.helpers;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.List;

public class ValidityState {
    /**
     * Validity of completed items
     *
     * @param s                    input Date
     * @param autoCompleteTextView input drawing
     * @param type                 input machine(coefficient)
     * @param countText            input count
     * @param drawings             all drawing
     * @return validity
     */
    public static boolean valid(String[] s,
                                AutoCompleteTextView autoCompleteTextView,
                                int type,
                                EditText countText, List<String> drawings) {

        return validDate(s)
                && validDrawing(autoCompleteTextView, drawings)
                && validType(type)
                && validCount(countText);
    }

    /**
     * Valid input count
     *
     * @param countText count products
     * @return validity
     */
    private static boolean validCount(EditText countText) {
        return Integer.parseInt(countText.getText().toString()) > 0;
    }

    /**
     * Valid input machine
     *
     * @param type machine(coefficient)
     * @return validity
     */
    private static boolean validType(int type) {
        return type != 0;
    }

    /**
     * Valid input drawing
     *
     * @param autoCompleteTextView input drawing
     * @param drawings             all drawings
     * @return validity
     */
    private static boolean validDrawing(AutoCompleteTextView autoCompleteTextView, List<String> drawings) {
        return drawings.contains(autoCompleteTextView.getText().toString());
    }

    /**
     * Valid input date
     *
     * @param s Date
     * @return validity
     */
    private static boolean validDate(String[] s) {
        int[] arr = new int[3];
        for (int i = 0; i < s.length; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }
        if (arr[0] < 0) {
            return false;
        }
        if (arr[2] % 4 == 0
                && arr[1] == 2
                && arr[0] <= 29) {
            return true;
        } else if (arr[2] % 4 != 0
                && arr[1] == 2
                && arr[0] <= 28) {
            return true;
        } else if (arr[1] % 2 == 0
                && arr[1] != 2
                && arr[0] <= 30) {
            return true;
        } else if (arr[1] % 2 == 1
                && arr[0] <= 31) {
            return true;
        } else return false;
    }
}