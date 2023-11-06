package dbservices;

/**
 * @author Timkov Anton
 */
public interface IDataTransferToDB<T> {
    void insertIntoDB(T object);

}

