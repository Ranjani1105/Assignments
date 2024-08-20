public class MyController {

    public static void main(String[] args) throws Exception {
        MyCollection myCollection = new MyCollection();
        MyFileHandler csvHandler = new CSVFileHandler();
        MyFileHandler xmlHandler = new XMLFileHandler();
        MyFileHandler jsonHandler = new JsonFileHandler();

        // Creating threads for reading files
        Thread csvReaderThread = new Thread(() -> {
            try {
                MyCollection collection = csvHandler.read("resources/mock_data.csv");
                for (int i = 0; i < 100; i++) {
                    myCollection.add(collection.get());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread xmlReaderThread = new Thread(() -> {
            try {
                MyCollection collection = xmlHandler.read("resources/mock_data.xml");
                for (int i = 0; i < 100; i++) {
                    myCollection.add(collection.get());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread jsonReaderThread = new Thread(() -> {
            try {
                MyCollection collection = jsonHandler.read("resources/mock_data.json");
                for (int i = 0; i < 100; i++) {
                    myCollection.add(collection.get());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start all reader threads
        csvReaderThread.start();
        xmlReaderThread.start();
        jsonReaderThread.start();

        // Wait for all threads to finish
        csvReaderThread.join();
        xmlReaderThread.join();
        jsonReaderThread.join();

        // Verify that all records have been read
        System.out.println("Total records in MyCollection: " + myCollection.getWriteCounter());

        // Creating threads for writing files
        Thread csvWriterThread = new Thread(() -> {
            try {
                csvHandler.write(myCollection, "output/output_employees.csv");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread xmlWriterThread = new Thread(() -> {
            try {
                xmlHandler.write(myCollection, "output/output_employees.xml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread jsonWriterThread = new Thread(() -> {
            try {
                jsonHandler.write(myCollection, "output/output_employees.json");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start all writer threads
        csvWriterThread.start();
        xmlWriterThread.start();
        jsonWriterThread.start();

        // Wait for all writer threads to finish
        csvWriterThread.join();
        xmlWriterThread.join();
        jsonWriterThread.join();

        System.out.println("Write operations completed.");
    }
}

