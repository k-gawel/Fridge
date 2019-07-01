package org.california.util;

public class ObjectUtils {

    public static boolean areAllNullOrNoneIs(Object... objects) {
        boolean first = objects[0] == null;

        for(Object object : objects) {
            if((object == null) != first)
                return false;
        }

        return true;
    }

}
