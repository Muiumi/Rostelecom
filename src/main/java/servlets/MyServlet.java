package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muyumi.rtkdatagroupsproject.DataGroup;
import com.muyumi.rtkdatagroupsproject.IGroupCriterion;
import com.muyumi.rtkdatagroupsproject.Person;
import com.muyumi.rtkdatagroupsproject.StudentService;
import dataloaders.DataLoaderFromTextFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/*"})
public class MyServlet extends HttpServlet {

    DataLoaderFromTextFile fileLoader = new DataLoaderFromTextFile("D:\\students.csv");
    StudentService service;
    DataGroup classroomDataGroup;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // Проверка инициализирована ли уже структура, если нет, то инициализируем
        if (service == null) {
            service = new StudentService(fileLoader);
            IGroupCriterion classroomCriterion = Person::getGroup;
            classroomDataGroup = new DataGroup(classroomCriterion);
            service.loadDataInSelectedStructure(classroomDataGroup);
        }
        // Получаем параметры, введённые пользователем
        var classroom_id = req.getParameter("classroom_id");

        var mapper = new ObjectMapper();

        try (var output = resp.getWriter()) {
            resp.setContentType("application/json");
            output.write(mapper.writeValueAsString(classroomDataGroup.getPersons(Integer.parseInt(classroom_id))));
            output.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // Проверка инициализирована ли уже структура, если нет, то инициализируем
        if (service == null) {
            service = new StudentService(fileLoader);
            IGroupCriterion classroomCriterion = Person::getGroup;
            classroomDataGroup = new DataGroup(classroomCriterion);
            service.loadDataInSelectedStructure(classroomDataGroup);
        }
        // Получаем параметры, введённые пользователем
        int classroom_id = Integer.parseInt(req.getParameter("classroom_id"));
        var first_name = req.getParameter("first_name");
        var surname = req.getParameter("surname");
        var subject = req.getParameter("subject");
        int newGrade = Integer.parseInt(req.getParameter("grade"));

        boolean personIsPresent = false;
        for (Person person : classroomDataGroup.getPersons(classroom_id)) {
            if ((person.getSurname().equals(surname)) && (person.getFirstName().equals(first_name))) {
                person.setPersonGrade(subject, newGrade);
                personIsPresent = true;
            }
        }
        if (!personIsPresent){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Указанный ученик не найден");
        }
    }

}
