package org.california.model.validator.ValidationRegexTest;

import org.california.model.validator.ValidationRegex;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class alphanumericRegex {

    ValidationRegex regex = ValidationRegex.ALPHANUMERIC;

    @Test
    public void only_letters() {

        String smallLetters = "abcdefgh";
        assertTrue(regex.check(smallLetters));

        String bigLetters = "ABCDEFGH";
        assertTrue(regex.check(bigLetters));

        String mixedLetters1 = "aBcDeFgH";
        assertTrue(regex.check(mixedLetters1));

        String mixedLetters2 = "AbCdEfGh";
        assertTrue(regex.check(mixedLetters2));

    }


    @Test
    public void only_digits() {

        String digits = "1234567";
        assertTrue(regex.check(digits));

    }


    @Test
    public void mixed_digits_and_letters() {

        String string1 = "a1b23c";
        assertTrue(regex.check(string1));

        String string2 = "1aB3cDe";
        assertTrue(regex.check(string2));

    }


    @Test
    public void mixed_alphanumeric_and_non_alphanumeric() {
        String mixed;

        mixed = "1.dwfds";
        assertFalse(regex.check(mixed));

        mixed = "dw.fds1";
        assertFalse(regex.check(mixed));

        mixed = "d_w_f3ds";
        assertFalse(regex.check(mixed));

    }


    @Test
    public void nullable() {
        assertFalse(regex.check(null));
    }

}