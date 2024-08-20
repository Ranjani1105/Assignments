public interface MyFileHandler {
    void write(MyCollection collection, String fileName) throws Exception;
    MyCollection read(String fileName) throws Exception;
}
