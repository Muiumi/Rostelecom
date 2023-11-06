package commands;

import dbservices.PostgreSQLService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timkov Anton
 */
public class SQLPerfectStudents implements ICommand {
    private final PostgreSQLService service;

    public SQLPerfectStudents(PostgreSQLService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        try {
            var connection = service.getConnection();
            System.out.println("--- Отличники старше 14 лет ---");
            PreparedStatement perfectStudents = connection.prepareStatement("""
                    SELECT
                        persons.first_name,
                        persons.last_name
                    FROM
                        persons
                        JOIN (
                            SELECT
                            person_id,
                            AVG(grade) AS average_grade
                        FROM
                            grades
                        GROUP BY
                            person_id
                        HAVING
                            AVG(grade) = 5
                        ) AS excellent_grades
                            ON persons.id = excellent_grades.person_id
                    WHERE
                         persons.age > 14;""");
            ResultSet rs = perfectStudents.executeQuery();
            while (rs.next()) {
                String name = rs.getString("last_name") + " " + rs.getString("first_name");
                System.out.println(name);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Проблемы с подключением к БД");
            throw new RuntimeException(e);
        }

    }
}

