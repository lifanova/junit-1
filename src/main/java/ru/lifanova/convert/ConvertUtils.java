package ru.lifanova.convert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.lifanova.domain.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ConvertUtils {
    private static final String DATA_DIR_PATH = System.getProperty("user.dir") + "/src/main/resources/data/";

    public static List<Employee> parseToJson(String fileName) {
        List<String> lines = readFromFile(fileName);

        if (lines == null || lines.isEmpty()) {
            System.out.println("[parseToJson]: Error: список пуст!");
            return Collections.emptyList();
        }

        return processLines(lines);
    }

    public static List<String> readFromFile(String fileName) {
        List<String> resultList = new ArrayList<>();

        if (fileName == null || fileName.isEmpty()) {
            System.out.println("[readFromFile]: Error: empty file name!");
            return null;
        }

        String filePath = DATA_DIR_PATH + fileName;

        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = reader.readLine()) != null) {
                resultList.add(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return resultList;
    }

    public static List<Employee> processLines(List<String> inputLines) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        List<Employee> resultList = new ArrayList<>();
        for (String line : inputLines) {
            Employee employee = gson.fromJson(line, Employee.class);
            System.out.println(employee.toString());
            resultList.add(employee);
        }

        return resultList;
    }
}
