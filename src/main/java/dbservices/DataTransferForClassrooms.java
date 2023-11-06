package dbservices;

import com.muyumi.rtkdatagroupsproject.Person;
import dataloaders.DataLoaderFromTextFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Timkov Anton
 */
public class DataTransferForClassrooms implements IDataTransferToDB<Person> {
    private final Connection connection;
    private static final String CLASSROOM_INSERT_STATEMENT = "INSERT INTO classrooms (year_of_study, subjects) VALUES (?,?) ON CONFLICT DO NOTHING";

    public DataTransferForClassrooms(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertIntoDB(Person person) {
        try {
            PreparedStatement addClassroom = connection.prepareStatement(CLASSROOM_INSERT_STATEMENT);
            addClassroom.setInt(1, person.getGroup());
            addClassroom.setArray(2, connection.createArrayOf("text", DataLoaderFromTextFile.getSubjects()));
            addClassroom.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении новой группы");
            System.out.println(e.getMessage());
        }
    }
}
