package commands;

import dbservices.PostgreSQLService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Timkov Anton
 */
public class SQLSearchBySurnameCommand implements ICommand {
    private final PostgreSQLService service;

    public SQLSearchBySurnameCommand(PostgreSQLService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        try {
            var connection = service.getConnection();
            System.out.println("--- Средняя оценка по указанной фамилии ---");
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите искомую фамилию:");

            String surname = sc.nextLine();
            PreparedStatement avgGradeBySurname = connection.prepareStatement("""
                    SELECT
                        persons.first_name,
                        persons.last_name,
                        classrooms.year_of_study,
                        AVG(grades.grade) AS average_performance
                    FROM
                        persons
                        JOIN grades ON persons.id = grades.person_id
                        JOIN classrooms ON persons.classroom_id = classrooms.year_of_study
                    WHERE
                        persons.last_name = ?
                    GROUP BY
                        persons.first_name,
                        persons.last_name,
                        classrooms.year_of_study;""");

            avgGradeBySurname.setString(1, surname);
            ResultSet rs = avgGradeBySurname.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    String name = rs.getString("last_name") + " " + rs.getString("first_name");
                    int studyGroup = rs.getInt("year_of_study");
                    double avgPerformance = rs.getDouble("average_performance");
                    System.out.println("Средняя оценка ученика " + name + " из группы " + studyGroup + ": " + avgPerformance);
                }
            } else {
                System.out.println("Проверьте корректность ввода");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Проблемы с подключением к БД");
            throw new RuntimeException(e);
        }
    }
}
