package edu.northeastern.tokenize;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    public static List<String> createTokens() throws IOException {
        List<String> tokens = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URI resource = null;
        try {
            resource = Objects.requireNonNull(classLoader.getResource("ebook")).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Path dirPath = Paths.get(resource);
        Pattern tokenPattern = Pattern.compile("\\w+|\\p{Punct}");

        Files.walk(Paths.get(dirPath.toUri()))
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    try {
                        String content = Files.readString(path).toLowerCase();
                        Matcher matcher = tokenPattern.matcher(content);
                        while (matcher.find()) {
                            String token = matcher.group();
                            if (!token.isBlank()) {
                                tokens.add(token);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return tokens;
    }
}
