package adventure.days.day_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DayEightSolution {
    public static int partOneSolution() throws IOException
    {
        Path path = Path.of("src\\main\\java\\adventure\\days\\day_8\\input.txt").toAbsolutePath();

        BufferedReader reader = Files.newBufferedReader(path);

        // список команд
        List<Character> commands = reader.readLine()
                                    .chars()
                                    .mapToObj(c -> (char)c)
                                    .collect(Collectors.toList());

        reader.readLine();

        String line = reader.readLine();
        // самая первая точка с которой начинается опрос
        String currentCommand = "AAA"; // пременная отвечает за ту точку на которой сейчас находиться пользователь

        Map<String, List<String>> map = new HashMap<>();

        while (line != null) {
            
            // рефакторим строку для более удобной работы с ней
            line = line.replace("(", "")
                        .replace(")", "")
                        .replaceAll(" ", "")
                        .replace(",", "=");
            // System.out.println( line);
            String[] splitted = line.split("=");

            // splitted[0] - key
            // splitted[1] - left
            // splitted[2] - right

            map.put(splitted[0], Arrays.asList(splitted[1], splitted[2]));

            line =reader.readLine();
        }

        int counter = 0;

        // System.out.println(currentCommand);
        while (!currentCommand.equals("ZZZ")) {
            
        
            for(int i = 0; i < commands.size(); i++){
                char command = commands.get(i);

                switch (command) {
                    case 'L' -> {
                        currentCommand = map.get(currentCommand).get(0);
                        break;
                    }
                    case 'R' -> {
                        currentCommand = map.get(currentCommand).get(1);
                        break;
                    }
                    default->{
                        break;
                    }
                }

                // System.out.println(currentCommand);

                counter++;

                if(currentCommand.equals("ZZZ")){
                    break;
                }

            }
        }
        
        return counter;
    }
}
