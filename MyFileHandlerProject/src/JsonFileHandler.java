import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JsonFileHandler implements MyFileHandler {

    @Override
    public void write(MyCollection collection, String fileName) throws Exception {
        Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").setPrettyPrinting().create();

        Employee[] employees = new Employee[collection.getWriteCounter()];
        for (int i = 0; i < collection.getWriteCounter(); i++) {
            employees[i] = collection.get();
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(employees, writer);
        }
    }

    @Override
    public MyCollection read(String fileName) throws Exception {
        MyCollection collection = new MyCollection();
        Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();

        Type employeeListType = new TypeToken<List<Employee>>() {}.getType();

        try (FileReader reader = new FileReader(fileName)) {
            List<Employee> employees = gson.fromJson(reader, employeeListType);
            for (Employee employee : employees) {
                collection.add(employee);
            }
        }

        return collection;
    }
}
