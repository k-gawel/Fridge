package org.california.controller.service.utils;

import java.util.Collection;
import java.util.HashSet;

public class Utils {

    public static Collection<Number> collectionOf(String ids) {
        Collection<Number> result = new HashSet<>();

            if(ids == null)
                return result;

            String[] idsAsArray = ids.split(",");

            for(String idString: idsAsArray) {
                try {
                    result.add(Long.valueOf(idString));
                } catch (Exception ignored) {
                }
            }

            return result;

    }


    public static Boolean stringToBoolean(String string) {
        return string.equals("") ? null : Boolean.valueOf(string);
    }


}
