openapi: 3.0.0
info:
  title: Успеваемость учеников по спецификации Open API 
  version: "1.0.0"
servers:
  # Added by API Auto Mocking Plugin
  - description: SwaggerHub API Auto Mocking
    url: https://virtserver.swaggerhub.com/Muiumi/RTKTask4/1.0.0
  - url: http://localhost:8080/RTKDataStructuresProject

paths:
  /persons/average-grades/{classroom_id}:
    get:
      summary: Получить средние оценки каждого ученика в указанном классе
      parameters:
        - name: classroom_id
          in: path
          description: Идентификатор класса
          required: true
          schema:
            type: integer
            example: 1
      responses:
        '200':
          description: Средние оценки каждого ученика в указанном классе
          content:
            application/json:
              schema:
                type: object
                properties:
                  student:
                    type: string
                    example: "Абрамов Егор"
                  average_grade:
                    type: number
                    example: 5.0
  /persons/{classroom_id}/{first_name}&{surname}/{subject}/new-grade:
    put:
      summary: Изменить оценку конкретного ученика (по ФИО и классу) по конкретному предмету
      parameters:
        - name: classroom_id
          in: path
          description: Идентификатор класса
          required: true
          schema:
            type: integer
            example: 1
        - name: first_name
          in: path
          description: Имя ученика
          required: true
          example: "Георгий"
          schema:
            type: string
        - name: surname
          in: path
          description: Фамилия ученика
          required: true
          example: "Иванов"
          schema:
            type: string
        - name: subject
          in: path
          description: Название предмета
          required: true
          example: "Информатика"
          schema:
            type: string
        - name: grade
          in: query
          description: Новая оценка
          required: true
          schema:
            type: integer
      responses:
        '200':
          description: Оценка изменена
        '404':
          description: Ученик не найден