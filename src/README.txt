Приложение позволяет либо войти в систему под своим логином,
либо зарегистрировать нового пользователя с правами USER.

В базе хранится один пользователь с правами администратора - login:administrator pass:administrator

Пользователю с правами администратора доступны страницы http://localhost:8080/welcome и 
http://localhost:8080/admin. Он вправе создавать, обновлять, удалять список книг.

Обычный пользователь может лишь просматривать список книг.

Итак:

1. Настроили подключение к MySQL /src/main/resources/database.properties
2. Cоздали Базу данных /src/main/resources/database.sql
3. Создали модели классов - сущностей в БД /src/java/spring.security/model/*
4. Создали Dao слой /src/java/spring.security/dao/*
5. Создали сервисы /src/java/spring.security/services/*
6. Создали валидаторы /src/java/spring.security/validator/*
7. Создали контроллер /src/java/spring.security/controller/*
8. Прописали логгирование logback.xml
9. Написали сообщения для ошибок при валидации validation.properties
10.Создали страницы admin, login, registration, welcome
11.Прописали настройки конфигурационных файлов Spring:
 - а: откуда получать данные для доступа к БД appconfig-data.xml
 - б: где находятся файлы views appconfig-mvc.xml
 - в: где находятся настройки безопасности appconfig-security.xml

Страница входа:
http://localhost:8080/login