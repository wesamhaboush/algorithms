package com.codebreeze.algorithms;

import java.io.File;
import java.net.URL;

public class Utils {
    public static File getFile(String fileName) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    public static int[] arrayOf(int...ints) {
        return ints;
    }
}
