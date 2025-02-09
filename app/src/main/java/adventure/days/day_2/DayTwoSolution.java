package adventure.days.day_2;

import adventure.days.DayBase;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class DayTwoSolution implements DayBase<Integer> {

    @Override
    public Integer SolutionPartOne() throws Exception {
        Path path = Path.of("app\\src\\main\\java\\adventure\\days\\day_2\\input.txt").toAbsolutePath();
        BufferedReader reader = Files.newBufferedReader(path);

        int count = 0;
        String line = reader.readLine();
        while (line != null) {
            List<Integer> splitedLine = Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .toList();

            if (checkByAsc(splitedLine, false) || checkByDesc(splitedLine, false)) count++;

            line = reader.readLine();
        }

        return count;
    }

    // бля в пизду. Было без брутфорса 527, а ответ 528... я ебал
    @Override
    public Integer SolutionPartTwo() throws Exception {
        Path path = Path.of("app\\src\\main\\java\\adventure\\days\\day_2\\input.txt").toAbsolutePath();

        BufferedReader reader = Files.newBufferedReader(path);

        int count = 0;
        String line = reader.readLine();
        while (line != null) {
            List<Integer> splitedLine = Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            List<Integer> integers = new ArrayList<>(splitedLine);
            List<Integer> shit = new ArrayList<>(splitedLine);

            System.out.println(line);
            System.out.println(checkByAsc(new ArrayList<>(splitedLine), true));
            System.out.println(checkByDesc(new ArrayList<>(splitedLine), true));
            System.out.println();

            if (checkByAsc(integers, true) ||
                    checkByDesc(shit, true)) {
                count++;
            }

            line = reader.readLine();
        }

        return count;
    }

    private boolean checkByDesc(List<Integer> list, boolean isOneErrorTolerate) {
        List<Integer> badLevelsIndex = new ArrayList<>();

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) <= list.get(i + 1)) {
                badLevelsIndex.add(i);
                continue;
            }
            if (Math.abs(list.get(i) - list.get(i + 1)) > 3) badLevelsIndex.add(i);
        }
        if (!isOneErrorTolerate) {
            return badLevelsIndex.isEmpty();
        }

        if (badLevelsIndex.isEmpty()) return true;
        if (badLevelsIndex.size() > 2) return false;

        int removableIndex = badLevelsIndex.get(0);

        List<Integer> minusOne = new ArrayList<>(list);
        List<Integer> plusOne = new ArrayList<>(list);
        plusOne.remove(removableIndex+1);
        list.remove(removableIndex);
        badLevelsIndex.clear();

        for (int i = 0; i < list.size(); i++) {
            List<Integer> shit = new ArrayList<>(list);
            shit.remove(i);

            for (int j = 0; j < shit.size() - 1; j++) {
                if (shit.get(j) <= shit.get(j + 1)) {
                    badLevelsIndex.add(j);
                    continue;
                }
                if (Math.abs(shit.get(j) - shit.get(j + 1)) > 3) badLevelsIndex.add(j);
            }
            if (!isOneErrorTolerate) {
                return badLevelsIndex.isEmpty();
            }
        }

        return badLevelsIndex.size() > 0;
    }

    private boolean checkByAsc(List<Integer> list, boolean isOneErrorTolerate) {
        List<Integer> badLevelsIndex = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                badLevelsIndex.add(i);
                continue;
            }
            if (Math.abs(list.get(i) - list.get(i + 1)) > 3) {
                badLevelsIndex.add(i);
            }
        }
        if (!isOneErrorTolerate) {
            return badLevelsIndex.isEmpty();
        }
        if (badLevelsIndex.isEmpty()) return true;

        if (badLevelsIndex.size() > 2) return false;


        for (int i = 0; i < list.size(); i++) {
            List<Integer> shit = new ArrayList<>(list);
            shit.remove(i);

            for (int j = 0; j < shit.size() - 1; j++) {
                if (shit.get(j) >= shit.get(j + 1)) {
                    badLevelsIndex.add(j);
                    continue;
                }
                if (Math.abs(shit.get(j) - shit.get(j + 1)) > 3) {
                    badLevelsIndex.add(j);
                }
            }
        }
        return badLevelsIndex.size() > 0;
    }

}
