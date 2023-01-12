package laba4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class that transforms CSV table to List of Persons
 *
 * @author mqiiz
 */

public class CSVList {

    /**
     * @value List that contains Person instances
     */
    private static final List<Person> list = new ArrayList<>();

    /**
     * @value Value to fill a field id of the Division object
     */
    private static long genId;

    /**
     * A method that parses file to List of Persons
     *
     * @param pathToFile A path to parsable file
     * @return List of Persons
     */

    public static List<Person> getListFromFile(String pathToFile) {
        list.clear();
        genId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line = reader.readLine(); //need to avoid first non-parsable line
            while ((line = reader.readLine()) != null)
                lineToList(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(list);
    }

    /**
     * A method that parses a line to a Person instance and adds it to CSVList
     *
     * @param line Single line of a parsable table
     */
    private static void lineToList(String line) {
        String[] data = line.split(";");
        long id = Long.parseLong(data[0]);
        String name = data[1];
        Gender gender = data[2].equals("Male") ? Gender.MALE : Gender.FEMALE;
        List<Integer> date = Arrays.stream(data[3].split("\\.")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        Date birthDate = new Calendar.Builder().setDate(date.get(2), date.get(1) - 1, date.get(0)).build().getTime();
        Division division = new Division(genId++, data[4]);
        double salary = Double.parseDouble(data[5]);
        list.add(new Person(id, name, gender, birthDate, division, salary));
    }
}