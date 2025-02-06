package adventure.days.day_1;

import adventure.days.DayBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//
public class DayOneSolution implements DayBase<Integer> {
    public Integer SolutionPartOne() throws IOException {

        Path path = Path.of("app\\src\\main\\java\\adventure\\days\\day_1\\input.txt").toAbsolutePath();

        BufferedReader reader = Files.newBufferedReader(path);

        int result = 0;
        List<Integer> leftColList = new ArrayList<>();
        List<Integer> rightColList = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            String[] splitedLine = line.split(" ");
            leftColList.add(Integer.parseInt(splitedLine[0]));
            rightColList.add(Integer.parseInt(splitedLine[3]));

            line = reader.readLine();
        }

        leftColList.sort(Comparator.naturalOrder());
        rightColList.sort(Comparator.naturalOrder());

        ArrayDeque<Integer> leftColQ = new ArrayDeque<>(leftColList);
        ArrayDeque<Integer> rightColQ = new ArrayDeque<>(rightColList);

        int to = leftColList.size();
        for (int i = 0; i < to; i++) {
            System.out.println(leftColQ.peekFirst() + "   " + rightColQ.peekFirst());
            int addable = Math.abs(leftColQ.pollFirst() - rightColQ.pollFirst());
            System.out.println(addable);
            result += addable;
        }

        return result;
    }

    public Integer SolutionPartTwo() throws IOException {
        Path path = Path.of("app\\src\\main\\java\\adventure\\days\\day_1\\input.txt").toAbsolutePath();

        BufferedReader reader = Files.newBufferedReader(path);

        int result = 0;

        List<Integer> leftColList = new ArrayList<>();
        List<Integer> rightColList = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            String[] splitedLine = line.split(" ");
            leftColList.add(Integer.parseInt(splitedLine[0]));
            rightColList.add(Integer.parseInt(splitedLine[3]));

            line = reader.readLine();
        }

        Map<Integer, Long> mapOfTimesLeftCol = leftColList.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        HashMap<Integer, Integer> mapOfTimesLeftNumInRightCol = new HashMap<>();

        for (Integer num : mapOfTimesLeftCol.keySet()) {
            int count = 0;

            for (Integer rightNum : rightColList) {
                if (rightNum.equals(num)) count++;
            }
            result += (int) (num * count * mapOfTimesLeftCol.get(num));
        }

        return result;
    }


}
