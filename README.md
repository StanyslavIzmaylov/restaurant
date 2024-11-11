Technical requirement:

Design and implement a REST API using Hibernate/Spring/SpringMVC (Spring-Boot preferred!) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

    2 types of users: admin and regular users
    Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
    Menu changes each day (admins do the updates)
    Users can vote for a restaurant they want to have lunch at today
    Only one vote counted per user
    If user votes again the same day:
        If it is before 11:00 we assume that he changed his mind.
        If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it (better - link to Swagger).

swagger url - http://localhost:8080/swagger-ui/index.html

Login:
admin@ya.ru
admin

user@ya.ru
user

Техническое требование:

Спроектировать и внедрить REST API с использованием Hibernate/Spring/Spring MVC (предпочтительнее Spring-Boot!) без интерфейса.

Задача состоит в том, чтобы:

Создать систему голосования для принятия решения о том, где пообедать.

    2 типа пользователей: администраторы и обычные пользователи
    Администратор может указать ресторан и его меню на обед на день (обычно 2-5 блюд, только название блюда и цена)
    Меню меняется каждый день (администраторы вносят изменения)
    Пользователи могут проголосовать за ресторан, в котором они хотят пообедать сегодня
    Учитывается только один голос для каждого пользователя
    Если пользователь проголосует повторно в тот же день:
        Если это произойдет до 11:00, мы будем считать, что он передумал.
        Если после 11:00, значит, уже слишком поздно, голосование изменить нельзя

Каждый ресторан ежедневно публикует новое меню.

В результате предоставьте ссылку на репозиторий на github. Он должен содержать код, README.md с документацией по API и парой команд curl для его тестирования (лучше - ссылку на Swagger).

swagger url - http://localhost:8080/swagger-ui/index.html

Login:
admin@ya.ru
admin

user@ya.ru
user
