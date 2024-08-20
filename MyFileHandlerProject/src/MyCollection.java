public class MyCollection {
    private Employee[] employees = new Employee[300];
    private int writeCounter = 0;
    private int readCounter = 0;

    public synchronized void add(Employee employee) {
        if (writeCounter < employees.length) {
            employees[writeCounter] = employee;
            writeCounter++;
        }
    }

    public synchronized Employee get() {
        if (readCounter < writeCounter) {
            Employee employee = employees[readCounter];
            readCounter++;
            return employee;
        }
        return null;
    }

    public int getWriteCounter() {
        return writeCounter;
    }

    public int getReadCounter() {
        return readCounter;
    }
}
