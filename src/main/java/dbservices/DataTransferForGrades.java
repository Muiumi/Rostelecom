package dbservices;

import com.muyumi.rtkdatagroupsproject.Person;

import java.sql.*;

/**
 * @author Timkov Anton
 */
public class DataTransferForGrades implements IDataTransferToDB<Person> {
    private final Connection connection;
    private final int personGeneratedKey;
    private static final String GRADE_INSERT_STATEMENT = "INSERT INTO grades (subject, person_id, grade) VALUES (?, ?, ?)";
    private static final String SUBJECTS_SELECT_STATEMENT = "SELECT * FROM classrooms WHERE year_of_study = ?";

    public DataTransferForGrades(Connection connection, int personGeneratedKey) {
        this.connection = connection;
        this.personGeneratedKey = personGeneratedKey;
    }
    private String[] getSubjects(Person person) throws SQLException {
        try (PreparedStatement getId = connection.prepareStatement(SUBJECTS_SELECT_STATEMENT)) {
            getId.setInt(1, person.getGroup());
            try (ResultSet resultSet = getId.executeQuery()) {
                if (resultSet.next()) {
                    Array resultSetArray = resultSet.getArray("subjects");
                    return (String[])resultSetArray.getArray();
                } else {
                    throw new SQLException("Ошибка при поиске для заполнения таблицы оценок");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new SQLException();
    }
    @Override
    public void insertIntoDB(Person person) {
        try (PreparedStatement addGrade = connection.prepareStatement(GRADE_INSERT_STATEMENT)) {
            String [] subjectsArray = getSubjects(person);
            for( var i =0; i< person.getPersonGrades().length;i++){
                addGrade.setString(1, subjectsArray[i]);
                addGrade.setInt(2, personGeneratedKey);
                addGrade.setInt(3, person.getPersonGrades()[i]);
                addGrade.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении новой оценки");
            System.out.println(e.getMessage());
        }
    }
}
