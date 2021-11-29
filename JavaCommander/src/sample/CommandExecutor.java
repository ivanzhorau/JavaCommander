package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandExecutor {
    public static String executeCommand(String[] command) {

        StringBuilder output = new StringBuilder();

        Process p;
        try {
            p = new ProcessBuilder(command).start();
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine())!= null) {
                output.append(line).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
}
