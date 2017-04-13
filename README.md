# SpringSecurityCRUDApp
Spring Security CRUD application using Spring Core, Spring Security, Hibernate, MySQL.

Приложение позволяет войти в систему под своим логином, либо зарегистрировать нового пользователя с правами USER.

В базе хранится один пользователь с правами администратора - login:administrator pass:administrator

Пользователю с правами администратора доступны страницы http://localhost:8080/welcome и http://localhost:8080/admin.
Он вправе создавать, обновлять, удалять список книг на странице http://localhost:8080/admin

Обычный пользователь может лишь просматривать список книг на http://localhost:8080/welcome, и ему недоступна http://localhost:8080/admin.
