package ru.lifanova;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.lifanova.convert.ConvertUtils;
import ru.lifanova.domain.Employee;

import java.util.Arrays;
import java.util.List;


public class ConvertUtilsTest {

    @Test
    public void wrongFilePathCheck() {
        // Неверное имя файла - такой файл не существует
        String jsonFileName = "old_data.json";

        // возникает exception, результирующий список пустой
        List<String> lines = ConvertUtils.readFromFile(jsonFileName);

        Assertions.assertTrue(lines != null && lines.isEmpty());
    }

    @Test
    public void emptyFileCheck() {
        String jsonFileName = "empty_data.json";

        // результирующий список пустой - ничего при это мне падает
        List<String> lines = ConvertUtils.readFromFile(jsonFileName);

        Assertions.assertTrue(lines != null && lines.isEmpty());
    }

    @Test
    public void readObjectsCheck() {
        String jsonFileName = "new_data.json";
        int expectedSize = 2;
        // результирующий список не пуст
        List<String> lines = ConvertUtils.readFromFile(jsonFileName);

        Assertions.assertTrue(lines != null && !lines.isEmpty());
        Assertions.assertEquals(expectedSize, lines.size());
    }

    @Test
    public void parseObjectsCheck() {
       String[] inputLines = {"{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}",
               "{\"id\":2,\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}"};
       int expectedSize = inputLines.length;

        List<String> list = Arrays.asList(inputLines);
        List<Employee> employeeList = ConvertUtils.processLines(list);

        // Отдается в результате список, который не пуст и все поля соответствуют
        Assertions.assertTrue(employeeList != null && !employeeList.isEmpty());
        Assertions.assertEquals(expectedSize, employeeList.size());
    }

    @Test
    public void parseAndEqualsCheck() {
        String[] inputLines = {"{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}",
                "{\"id\":2,\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}"};
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        List<String> list = Arrays.asList(inputLines);
        List<Employee> employeeList = ConvertUtils.processLines(list);

        String expected = inputLines[0];
        Employee employee = employeeList.get(0);

        String actual = gson.toJson(employee, Employee.class);
        // Сравниваем 2 строки
        Assertions.assertEquals(expected, actual);
    }
}
