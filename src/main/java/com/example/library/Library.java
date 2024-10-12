package com.example.library;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class Library implements LibraryManagement {
    /**
     * Список книг, хранящихся в библиотеке
     */
    private List<Book> books;

    /**
     * Конструктор по умолчанию.
     * Инициализирует пустой список книг.
     */
    public Library() {
        this.books = new ArrayList<>();
    }

    /**
     * Добавляет книгу в библиотеку.
     * @param book книга для добавления
     */
    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Книга \"" + book.getTitle() + "\" добавлена в библиотеку.");
    }

    /**
     * Удаляет книгу из библиотеки.
     * Если книга не найдена, выводит сообщение.
     * @param book книга для удаления
     */
    @Override
    public void removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            System.out.println("Книга \"" + book.getTitle() + "\" удалена из библиотеки.");
        } else {
            System.out.println("Книга \"" + book.getTitle() + "\" не найдена.");
        }
    }

    /**
     * Ищет книгу в библиотеке по названию.
     * Если книга найдена, возвращает её.
     * @param title название книги для поиска
     * @return найденная книга или null, если книга не найдена
     */
    @Override
    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        System.out.println("Книга \"" + title + "\" не найдена.");
        return null;
    }

    /**
     * Выдаёт книгу пользователю.
     * Если книга доступна, её статус изменяется на "выдана".
     * @param book книга для выдачи
     */
    @Override
    public void borrowBook(Book book) {
        if (book != null) {
            book.borrowBook();
        }
    }

    /**
     * Возвращает книгу в библиотеку.
     * Если книга была выдана, её статус изменяется на "доступна".
     * @param book книга для возврата
     */
    @Override
    public void returnBook(Book book) {
        if (book != null) {
            book.returnBook();
        }
    }

    /**
     * Метод, выполняемый после инициализации объекта.
     * Выводит сообщение о том, что библиотека была инициализирована.
     */
    @PostConstruct
    public void init() {
        System.out.println("Библиотека инициализирована.");
    }

    /**
     * Метод, выполняемый перед уничтожением объекта.
     * Выводит сообщение о том, что библиотека закрыта.
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Библиотека уничтожена.");
    }
}
