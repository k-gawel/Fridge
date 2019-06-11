package org.california.util;

import java.util.HashSet;
import java.util.Set;

public class Converter {

    public static Set<Long> stringOfNumbersToSetOfLongs(String string) {

        String[] numbersArray = string.split(",");
        Set<Long> result;

         result = new HashSet<>();

         for(String number: numbersArray) {
             result.add(Long.valueOf(number));
         }
         return result;

    }

}
