openapi 3.0.0
info:
  title: Электронный дневник студента
  version: 1.0.0
servers:
  # Added by API Auto Mocking Plugin
  - description: SwaggerHub API Auto Mocking
    url: httpsvirtserver.swaggerhub.comMuiumiRTKTask41.0.0
  - url: httplocalhost8080

paths
  /students-data/db-init:
    get:
      summary: Инициализирует структуру БД и заполняет её данными для работы с учебным заданием
      responses:
        '200':
          description Успешная загрузка в БД
        '404':
          description Возникла ошибка при загрузке 
  /grades/{classroom_id}/avg-grades:
    get:
      summary: Получение средних оценок каждого ученика в указанном классе
      parameters:
        - name: classroom_id
          in: path
          description: Идентификатор класса
          required: true
          schema:
            type: integer
            example: 12
      responses:
        '200':
          description: Средние оценки каждого ученика в указанном классе
          content:
            application/json:
              schema:
                type: object
                properties:
                  first_name:
                    type: string
                    example Егор
                  surname:
                    type: string
                    example: Абрамов
                  average_grade:
                    type: number
                    example: 5.0
        '404':
          description: Класса с указанным идентификатором не существует
  /students/{student_id}/grade-change/{subject_name}/{grade}:
    put:
      summary: Изменение у студента под указанным идентификатором оценки по указанной дисциплине
      parameters:
        - name: student_id
          in: path
          description: Идентификатор студента
          required: true
          schema:
            type: number
            example: 105
        - name: subject_name
          in: path
          description: Название учебной дисциплины
          required: true
          schema:
            type: string
            example: physics
        - name: grade
          in: path
          description: Новая оценка
          required: true
          schema:
            type: integer
            example: 5
      responses:
        '200':
          description: Успешное изменение оценки
        '404':
          description: Ошибка при изменении оценки, отсутствие студента с указанным идентификатором 
  /students/student-addition:
    post:
      summary: Добавление студента с указанными данными
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref #componentsschemasStudent
        
      responses:
        '200':
          description: Успешное добавление студента, необходимо заполнить оценки
        '404':
          description: Ошибка при добавлении студента, отсутствие учебной группы с указанным идентификатором 
  /student/sstudent-addition/{student_id}:
    post:
      summary: Добавление оценок студенту, добавленному с помощью student-addition
      parameters:
        - name: student_id
          in: path
          description: Идентификатор студента
          required: true
          schema:
            type: number
            example: 105
        - name: grades
          in: query
          description: Новые оценки студента
          required: true
          schema:
            type: string
            example: 1,5,3,2,4,5
      responses:
        '200':
          description: Успешное добавление оценок студенту
        '404':
          description: Ошибка при добавлении студента, отсутствие студента с указанным идентификатором, попытка заполнить оценки студенту, у которого они уже есть
components:
  schemas:
    Student:
      type: object
      required: 
        - first_name
        - surname
        - age
        - classroom_num
      properties:
        first_name:
          type: string
          example: Степан
        surname:
          type: string
          example: Верховенский
        age:
          type: integer
          example: 12
        classroom_num:
          type: integer
          example: 6