package laba4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> list = CSVList.getListFromFile("src/main/resources/foreign_names.csv");
        for(Person p: list)
            System.out.println(p);
    }
}