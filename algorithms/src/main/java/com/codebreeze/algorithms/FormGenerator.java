package com.codebreeze.algorithms;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.function.BiFunction;

public class FormGenerator implements BiFunction<String, Map<String, String>, String> {

    @Override
    public String apply(String schemaName, Map<String, String> fieldValues) {
        try {
            String template = Files.readString(Utils.getFile("form-generator/schema-for-FormGenerator.txt").toPath());
            StringBuilder sb = new StringBuilder(template.length());
            for(int i = 0; i < template.length(); i++) {
                if(template.charAt(i) == '$') {
                    char currentChar = template.charAt(++i); // start from after the $
                    int start = i;
                    while(Character.isLetterOrDigit(currentChar) || currentChar == '_') {
                        currentChar = template.charAt(i++);
                    }
                    int endExclusive = --i; // we want to go back one because the loop will increment again

                    String fieldName = template.substring(start, endExclusive);
                    sb.append(fieldValues.get(fieldName));
                    sb.append(currentChar); // the space or the dot or the new line must be appended too!
                } else {
                    sb.append(template.charAt(i));
                }
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
