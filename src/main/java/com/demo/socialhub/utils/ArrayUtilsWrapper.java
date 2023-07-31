package com.demo.socialhub.utils;

import org.thymeleaf.util.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayUtilsWrapper {
    public static <T> List<T> asListOrEmpty(T[] array) {
        return ArrayUtils.isEmpty(array) ? Collections.emptyList() : Arrays.asList(array);
    }
}
