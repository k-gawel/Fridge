package org.california.controller.service.utils;

import org.california.util.StringUtils;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class StringUtilsTest_collectionFrom {

    @Test
    public void stringIsEmpty() {

        String string;
        Collection<Number> result;

        string = null;
        result = StringUtils.collectionOf(string);
        assertTrue(result.isEmpty());

        string = "";
        result = StringUtils.collectionOf(string);
        assertTrue(result.isEmpty());

    }

    @Test
    public void stringOfSingleNumber() {

        String string;
        Collection<Number> result;

        string = "1";
        result = StringUtils.collectionOf(string);
        assertEquals(1, result.size());

        string = "1,";
        result = StringUtils.collectionOf(string);
        assertEquals(1, result.size());

        string = ",1";
        result = StringUtils.collectionOf(string);
        assertEquals(1, result.size());

        string = ",1,";
        result = StringUtils.collectionOf(string);
        assertEquals(1, result.size());

    }

    @Test
    public void stringOfAlphabeticCharacters() {

        String string;
        Collection<Number> result;

        string = "a";
        result = StringUtils.collectionOf(string);
        assertTrue(result.isEmpty());

        string = "a,";
        result = StringUtils.collectionOf(string);
        assertTrue(result.isEmpty());

        string = ",a";
        result = StringUtils.collectionOf(string);
        assertTrue(result.isEmpty());

        string = "a,a";
        result = StringUtils.collectionOf(string);
        assertTrue(result.isEmpty());

        string = ",a,b,c,";
        result = StringUtils.collectionOf(string);
        assertTrue(result.isEmpty());

        string = ",a,";
        result = StringUtils.collectionOf(string);
        assertTrue(result.isEmpty());

    }

    @Test
    public void stringOfNumbers() {

        String string;
        Collection<Number> result;

        string = "1,2";
        result = StringUtils.collectionOf(string);
        assertEquals(result.size(), 2);

        string = ",1,2";
        result = StringUtils.collectionOf(string);
        assertEquals(result.size(), 2);

        string = ",1,2,";
        result = StringUtils.collectionOf(string);
        assertEquals(result.size(), 2);

        string = "1,2,";
        result = StringUtils.collectionOf(string);
        assertEquals(result.size(), 2);

    }

    @Test
    public void mixedString() {

        String string;
        Collection<Number> result;

        string = "1,a,2";
        result = StringUtils.collectionOf(string);
        assertEquals(result.size(), 2);

        string = "1,2,a";
        result = StringUtils.collectionOf(string);
        assertEquals(result.size(), 2);

        string = "a,1,2";
        result = StringUtils.collectionOf(string);
        assertEquals(result.size(), 2);

        string = "1,a,a,2,";
        result = StringUtils.collectionOf(string);
        assertEquals(result.size(), 2);

    }


}