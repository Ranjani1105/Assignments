import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

public class XMLFileHandler implements MyFileHandler {

    @Override
    public MyCollection read(String fileName) throws Exception {
        XmlMapper mapper = new XmlMapper();
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));

        // Deserialize XML content into an array of Employee objects
        List<Employee> employees = mapper.readValue(new File(fileName),
                mapper.getTypeFactory().constructCollectionType(List.class, Employee.class));

        MyCollection collection = new MyCollection();
        for (Employee employee : employees) {
            collection.add(employee);
        }
        return collection;
    }

    @Override
    public void write(MyCollection collection, String fileName) throws Exception {
        XmlMapper mapper = new XmlMapper();
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));

        Employee[] employees = new Employee[collection.getWriteCounter()];
        for (int i = 0; i < collection.getWriteCounter(); i++) {
            employees[i] = collection.get();
        }

        // Serialize the Employee array into XML and write to file
        mapper.writeValue(new File(fileName), employees);
    }
}
