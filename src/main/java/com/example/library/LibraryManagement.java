package com.example.library;

/**
 * Интерфейс управления библиотекой
 */
public interface LibraryManagement {
    /**
     * Добавляет книгу в библиотеку
     * @param book книга
     */
    void addBook(Book book);

    /**
     * Удаляет книгу из библиотеки
     * @param book книга
     */
    void removeBook(Book book);

    /**
     * Ищет книгу в библиотеке
     * @param title название книги
     * @return книга, если найдена
     */
    Book findBook(String title);

    /**
     * Выдаёт книгу пользователю
     * @param book книга
     */
    void borrowBook(Book book);

    /**
     * Возвращает книгу в библиотеку
     * @param book книга
     */
    void returnBook(Book book);
}
