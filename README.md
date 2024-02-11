# Twittersk
В данном репозитории располагается исходный код клиент-серверного веб-приложения в, котором пользователи публикуют сообщения и взаимодействуют с ними.



## Содержание
- [Технологии](#технологии)
- [Использование](#использование)
- [Тестирование](#тестирование)

## Технологии
- [Java](https://www.java.com/ru/)
- [Spring Framework](https://spring.io/)
- [Maven](https://maven.apache.org/)
- [Hibernate](https://hibernate.org/)
- [Flyway](https://flywaydb.org/)
- [Postgresql](https://www.postgresql.org/)
- [FreeMarker](https://freemarker.apache.org/index.html)
- [Docker](https://www.docker.com/)

## Использование
Освободите порт 80.

Чтобы запустить приложение, запустите скрипт start.sh:
```sh
$ ./start.sh
```

## Тестирование
Наш проект покрыт юнит-тестами JUnit. Для их запуска выполните команду:
```sh
mvn test
```
