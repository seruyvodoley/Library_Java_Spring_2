package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryUser {
    /**
     * Класс LibraryUser представляет собой пользователя библиотеки, который может брать и возвращать книги.
     * Данный класс является компонентом Spring, благодаря аннотации @Component, что позволяет Spring автоматически создавать и управлять экземплярами этого класса.
     */

    private final LibraryManagement library;

    /**
     * Конструктор класса LibraryUser, который использует внедрение зависимости через конструктор (аннотация @Autowired).
     * В качестве зависимости передается интерфейс LibraryManagement, который реализуется классом Library.
     *
     * @param library объект библиотеки, который реализует интерфейс LibraryManagement
     */
    @Autowired
    public LibraryUser(LibraryManagement library) {
        this.library = library;
    }

    /**
     * Метод borrowBook позволяет пользователю взять книгу из библиотеки.
     * Метод сначала ищет книгу по названию, а затем использует метод библиотеки для того, чтобы "выдать" книгу.
     *
     * @param title название книги, которую пользователь хочет взять
     */
    public void borrowBook(String title) {
        Book book = library.findBook(title);
        library.borrowBook(book);
    }

    /**
     * Метод returnBook позволяет пользователю вернуть книгу в библиотеку.
     * Метод ищет книгу по названию, а затем использует метод библиотеки для возврата книги.
     *
     * @param title название книги, которую пользователь хочет вернуть
     */
    public void returnBook(String title) {
        Book book = library.findBook(title);
        library.returnBook(book);
    }
}
