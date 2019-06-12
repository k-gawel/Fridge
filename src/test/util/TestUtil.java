package util;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.fail;

public class TestUtil {

    public static void assertThrowsMessage(Class<? extends Throwable> errorClass, Executable executable, String errorMessage) {

        boolean errorOccurred = false;

        try {
            executable.execute();
        } catch (Throwable throwable) {
            errorOccurred = true;

            if(!throwable.getClass().equals(errorClass))
                fail("\nExpected error: " + errorClass.getSimpleName() + "\n" +
                        "GOT: " + throwable.getClass().getSimpleName());

            if(throwable.getMessage() == null || !throwable.getMessage().equals(errorMessage))
                fail("\nExpected error message: " + errorMessage + "\n" +
                        "GOT " + throwable.getMessage());
        }

        if(!errorOccurred)
            fail("\nExpected error: " + errorClass.getSimpleName() + " with message: " + errorMessage);
    }

}
