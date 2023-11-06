package commands;

import dbservices.PostgreSQLService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timkov Anton
 */
public class SQLAvgGrades implements ICommand {
    private final PostgreSQLService service;

    public SQLAvgGrades(PostgreSQLService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        try {
            var connection = service.getConnection();
            System.out.println("--- Средние оценки по предметам в 10,11,12 классах ---");
            PreparedStatement avgGrades = connection.prepareStatement("""
                    SELECT
                        subject,
                        AVG(grade) AS average_grade
                    FROM
                        grades
                        JOIN persons ON grades.person_id = persons.id
                        JOIN classrooms ON persons.classroom_id = classrooms.year_of_study
                    WHERE
                        classrooms.year_of_study IN (10, 11)
                    GROUP BY
                        subject;""");
            ResultSet rs = avgGrades.executeQuery();
            while (rs.next()) {
                String subject = rs.getString("subject");
                double avgPerformance = rs.getDouble("average_grade");
                System.out.println(subject + ": " + avgPerformance);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Проблемы с подключением к БД");
            throw new RuntimeException(e);
        }
    }
}
