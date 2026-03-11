package org.java.biz;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Java9EnhancedIO {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Java9EnhancedIO.class.getClassLoader().getResourceAsStream("resource.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 你可以声明两个Resource类似的东西，然后都装到try (里面)
        try (bufferedReader; byteArrayOutputStream) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                byteArrayOutputStream.write(line.getBytes());
                System.out.println(byteArrayOutputStream.toString());
            }
        }
    }
}
