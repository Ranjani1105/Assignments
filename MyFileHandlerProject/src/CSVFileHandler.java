import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVFileHandler implements MyFileHandler {

    @Override
    public void write(MyCollection collection, String fileName) throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            for (int i = 0; i < collection.getWriteCounter(); i++) {
                Employee employee = collection.get();
                if (employee != null) {
                    String[] record = {
                            employee.getFirstName(),
                            employee.getLastName(),
                            new SimpleDateFormat("MM/dd/yyyy").format(employee.getDateOfBirth()),
                            String.valueOf(employee.getExperience())
                    };
                    writer.writeNext(record);
                }
            }
        }
    }

    @Override
    public MyCollection read(String fileName) throws Exception {
        MyCollection collection = new MyCollection();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] line;
            boolean firstLine = true;  // To skip the header
            while ((line = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;  // Skip header row
                }
                String firstName = line[0];
                String lastName = line[1];
                Date dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(line[2]);
                double experience = Double.parseDouble(line[3]);
                Employee employee = new Employee(firstName, lastName, dateOfBirth, experience);
                collection.add(employee);
            }
        }
        return collection;
    }
}
