package edu.northeastern;

import edu.northeastern.decoder.Decoder;
import edu.northeastern.encoder.Encoder;
import edu.northeastern.tokenize.Tokenizer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> tokensFromEbooks = Tokenizer.createTokens();
        System.out.println("All Ebooks in /resources/ebook/ have been tokenized to build the vocabulary dataset...\n");

        Map<String, Integer> encoder = Encoder.buildEncoder(tokensFromEbooks);
        System.out.println("Encoder build complete.\n");

        Map<Integer, String> decoder = Decoder.buildDecoder(encoder);
        System.out.println("Decoder build complete.\n");

        Scanner scanner = new Scanner(System.in);

        // Encode user input
        System.out.println("Enter the text to encode:");
        String input = scanner.nextLine();
        List<Integer> encoded = Encoder.encode(input, encoder);
        System.out.println("Encoded input: " + encoded + "\n");

        // Decode user input
        System.out.println("Enter the encoded text:");
        String encodedText = scanner.nextLine();
        String decoded = Decoder.decode(encodedText, decoder);
        System.out.println("Decoded output: " + decoded + "\n");
    }
}