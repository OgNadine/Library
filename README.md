# Library

Этот проект реализует CRUD Web API для управления библиотекой с использованием Spring Boot.

## Основной функционал

- Получение списка всех книг
- Получение книги по ID
- Получение книги по ISBN
- Добавление новой книги
- Изменение информации о книге
- Удаление книги

## Дополнительный функционал (LibraryService)

- Учет свободных книг
- Получение списка свободных книг
- Изменение информации о книге

## Требования

- Java 17
- Gradle
- MySQL

## Установка

1. **Клонируйте репозиторий**
2. **Настройте базу данных**
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/yourdbname
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
```
3. **Запустите сервисы Eureka, LibraryService, BookService**
