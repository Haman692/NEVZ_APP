package com.example.nevz.helpers;

import java.util.HashMap;
import java.util.Map;

public class MonthMapHelper {
    public static Map<String, String> monthMap() {
        Map<String, String> monthMap = new HashMap<String, String>();
        String[] monthEng = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        String[] monthRU = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        for (int i = 0; i < 12; i++) {
            monthMap.put(monthRU[i],monthEng[i]);
        }
        return monthMap;
    }

}
