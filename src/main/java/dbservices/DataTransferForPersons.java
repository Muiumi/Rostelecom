package dbservices;

import com.muyumi.rtkdatagroupsproject.Person;

import java.sql.*;

/**
 * @author Timkov Anton
 */
public class DataTransferForPersons implements IDataTransferToDB<Person> {
    private final Connection connection;
    private int generatedKey;

    private static final String PERSON_INSERT_STATEMENT = "INSERT INTO persons (first_name, last_name, age, classroom_id) " +
            "VALUES (?, ?, ?, ?)";
    private static final String CLASSROOM_SELECT_STATEMENT = "SELECT * FROM classrooms WHERE year_of_study = ?";

    public DataTransferForPersons(Connection connection) {
        this.connection = connection;
    }

    public int getGeneratedKey() {
        return generatedKey;
    }

    private int getClassroomId(Person person) throws SQLException {
        try (PreparedStatement getId = connection.prepareStatement(CLASSROOM_SELECT_STATEMENT)) {
            getId.setInt(1, person.getGroup());
            try (ResultSet resultSet = getId.executeQuery()) {
                if (resultSet.next()) {
                    return (resultSet.getInt("year_of_study"));
                } else {
                    throw new SQLException("Такого года обучения нет");
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при поиске года обучения");
            System.out.printf(e.getMessage());
        }
        throw new SQLException();
    }

    @Override
    public void insertIntoDB(Person person) {
        try (PreparedStatement addPerson = connection.prepareStatement(PERSON_INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS)) {
            addPerson.setString(1, person.getFirstName());
            addPerson.setString(2, person.getSurname());
            addPerson.setInt(3, person.getAge());
            addPerson.setInt(4, getClassroomId(person));
            addPerson.executeUpdate();
            ResultSet personKey = addPerson.getGeneratedKeys();
            while (personKey.next()){
                generatedKey = personKey.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении нового учащегося");
            System.out.printf(e.getMessage());
        }
    }
}
