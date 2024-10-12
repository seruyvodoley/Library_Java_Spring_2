package com.example.library;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Загружаем контекст Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LibraryConfig.class);

        // Получаем бины из контекста
        Library library = context.getBean(Library.class);
        LibraryUser user = context.getBean(LibraryUser.class);

        // Добавляем книги в библиотеку
        Book book = context.getBean(Book.class);
        library.addBook(book);

        // Пробуем найти книгу
        String searchTitle = "1984";
        Book foundBook = library.findBook(searchTitle);
        if (foundBook != null) {
            System.out.println("Найдена книга: " + foundBook.getTitle() + " автор: " + foundBook.getAuthor());
        }

        // Пользователь берет книгу
        user.borrowBook(searchTitle);

        // Возвращаем книгу
        user.returnBook(searchTitle);

        // Проверяем доступность книги
        System.out.println("Книга доступна: " + foundBook.isAvailable());

        // Закрываем контекст, чтобы вызвать методы destroy
        context.close();
    }
}
