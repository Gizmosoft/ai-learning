package edu.northeastern.decoder;

import java.util.HashMap;
import java.util.Map;

public class Decoder {
    /*
     * Encoder hashmap is passed
     * A new decoder hashmap is created where each value of encoder is make the key of decoder and vice versa
     */
    public static Map<Integer, String> buildDecoder(Map<String, Integer> encoder) {
        Map<Integer, String> decoder = new HashMap<>();
        for (Map.Entry<String, Integer> entry : encoder.entrySet()) {
            decoder.put(entry.getValue(), entry.getKey());
        }
        return decoder;
    }

    public static String decode(String encoded, Map<Integer, String> decoder) {
        StringBuilder decoded = new StringBuilder();

        encoded = encoded.trim().replaceAll("^\\[|]$", "");  // remove surrounding brackets
        String[] codes = encoded.split("\\s*,\\s*");  // split on comma and trim spaces
        for(String code : codes) {
            try {
                int id = Integer.parseInt(code);
                String token = decoder.getOrDefault(id, "[UNK]");

                if (token.matches("\\p{Punct}")) {
                    decoded.append(token);
                } else {
                    // Add space if previous char isn't space/punctuation
                    if (!decoded.isEmpty() && !decoded.toString().endsWith(" ")) {
                        decoded.append(" ");
                    }
                    decoded.append(token);
                }
            } catch (NumberFormatException e) {
                decoded.append("[UNK]");
            }
        }
        return decoded.toString().trim();
    }
}
