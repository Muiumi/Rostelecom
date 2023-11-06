package dbservices;

import com.muyumi.rtkdatagroupsproject.IService;
import com.muyumi.rtkdatagroupsproject.Person;
import dataloaders.DataLoaderFromTextFile;
import dataloaders.IDataLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Timkov Anton
 */
public class PostgreSQLService implements IService<List<Person>> {
    private final String URL;
    private  final String USER;
    private  final String PASSWORD;
    private static Connection connection = null;
    private final IDataLoader<Person> fileDataLoader;

    public PostgreSQLService(String URL,String USER, String PASSWORD, DataLoaderFromTextFile fileDataLoader) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.fileDataLoader = fileDataLoader;
    }

    public List<Person> loadDataFromLoader() {
        return this.fileDataLoader.loadData();
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Подключение отсутствует");
            System.out.println(e.getMessage());
        }
        return connection;
    }

    @Override
    public void loadDataInSelectedStructure(List<Person> object) throws SQLException {
        getConnection();
        System.out.println("Началась загрузка БД...");
        try {
            connection.setAutoCommit(false);
            for (Person person : object) {
                var classroomTransfer = new DataTransferForClassrooms(connection);
                classroomTransfer.insertIntoDB(person);
                var personTransfer = new DataTransferForPersons(connection);
                personTransfer.insertIntoDB(person);
                var gradesTransfer = new DataTransferForGrades(connection, personTransfer.getGeneratedKey());
                gradesTransfer.insertIntoDB(person);
            }
            connection.commit();
            System.out.println("Успешная загрузка");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        } finally {
            connection.close();
        }
    }
}






