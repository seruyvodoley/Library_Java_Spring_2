package com.example.library;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class Book {
    /**
     * Название книги
     */
    private String title;

    /**
     * Автор книги
     */
    private String author;

    /**
     * Доступна ли книга для выдачи
     */
    private boolean isAvailable;

    /**
     * Конструктор по умолчанию.
     * Инициализирует книгу как доступную.
     */
    public Book() {
        this.isAvailable = true; // по умолчанию книга доступна
    }

    /**
     * Конструктор с параметрами.
     * @param title название книги
     * @param author автор книги
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // по умолчанию книга доступна
    }

    /**
     * Устанавливает название книги.
     * @param title новое название книги
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Устанавливает автора книги.
     * @param author новый автор книги
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Возвращает название книги.
     * @return название книги
     */
    public String getTitle() {
        return title;
    }

    /**
     * Возвращает автора книги.
     * @return автор книги
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Проверяет, доступна ли книга для выдачи.
     * @return true, если книга доступна, иначе false
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Отмечает книгу как выданную.
     * Если книга доступна, меняет статус и выводит сообщение.
     */
    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Книга \"" + title + "\" выдана.");
        } else {
            System.out.println("Книга \"" + title + "\" недоступна.");
        }
    }

    /**
     * Возвращает книгу в библиотеку.
     * Если книга была выдана, отмечает её как доступную.
     */
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Книга \"" + title + "\" возвращена.");
        } else {
            System.out.println("Книга \"" + title + "\" уже была в наличии.");
        }
    }

    /**
     * Метод, выполняемый после инициализации объекта.
     * Выводит сообщение о том, что книга была инициализирована.
     */
    @PostConstruct
    public void init() {
        System.out.println("Книга \"" + title + "\" инициализирована.");
    }

    /**
     * Метод, выполняемый перед уничтожением объекта.
     * Выводит сообщение о том, что книга была уничтожена.
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Книга \"" + title + "\" уничтожена.");
    }

    /**
     * Фабричный метод для создания экземпляра книги с использованием данных из файла config.properties.
     * @param titleKey ключ для названия книги в файле свойств
     * @param authorKey ключ для автора книги в файле свойств
     * @return экземпляр книги, созданный на основе данных из файла свойств
     */
    public static Book createFromProperties(String titleKey, String authorKey) {
        Properties properties = new Properties();
        try (InputStream input = Book.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Извините, не удалось найти config.properties");
                return null;
            }
            properties.load(input);
            String title = properties.getProperty(titleKey);
            String author = properties.getProperty(authorKey);
            return new Book(title, author);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
