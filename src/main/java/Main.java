import ru.lifanova.convert.ConvertUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String jsonFileName = "old_data.json";

        ConvertUtils.parseToJson(jsonFileName);
    }
}
