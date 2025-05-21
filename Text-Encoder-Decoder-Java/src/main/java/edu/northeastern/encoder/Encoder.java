package edu.northeastern.encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Encoder {
    /*
     * A list of tokens is passed
     * A hashmap called encoder is created with the tokens as key and an id as their value
     */
    public static Map<String, Integer> buildEncoder(List<String> tokens) {
        Map<String, Integer> encoder = new HashMap<>();
        int id = 0;
        for (String token : tokens) {
            if (!token.isBlank() && !encoder.containsKey(token)) {
                encoder.put(token, id++);
            }
        }
        return encoder;
    }

    /*
     * Encode user input with the encode value from the encoder
     */
    public static List<Integer> encode(String input, Map<String, Integer> encoder) {
        List<Integer> encoded = new ArrayList<>();
        Pattern tokenPattern = Pattern.compile("\\w+|\\p{Punct}");      // include words, punctuations and spaces
        Matcher matcher = tokenPattern.matcher(input.toLowerCase());
        while (matcher.find()) {
            String token = matcher.group();
            encoded.add(encoder.getOrDefault(token, -1));  // -1 for unknown token
        }
        return encoded;
    }
}
