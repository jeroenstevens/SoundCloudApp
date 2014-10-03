package soundcloudapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InpuStreamConverter {

    public static String toString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8"));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null)
            stringBuilder.append(line);

        bufferedReader.close();
        inputStream.close();
        // Return as String object instead of StringBuilder
        return stringBuilder.toString();
    }
}
