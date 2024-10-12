package com.example.library;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.library")
public class LibraryConfig {

    /**
     * Конфигурационный класс Spring для настройки компонентов библиотеки.
     * Аннотация @Configuration указывает, что данный класс содержит определения бинов.
     * Аннотация @ComponentScan указывает Spring, где искать компоненты для автоматического создания бинов.
     */

    /**
     * Создает бин для библиотеки (Library).
     * @return новый экземпляр класса Library
     */
    @Bean
    public Library library() {
        return new Library();
    }

    /**
     * Создает бин для пользователя библиотеки (LibraryUser).
     * Для создания пользователя требуется передать бин библиотеки (Library) как зависимость.
     * @param library экземпляр библиотеки, который будет использоваться пользователем
     * @return новый экземпляр класса LibraryUser
     */
    @Bean
    public LibraryUser libraryUser(Library library) {
        return new LibraryUser(library);
    }

    /**
     * Создает бин для книги (Book).
     * Фабричный метод createFromProperties загружает данные о книге из файла config.properties.
     * @return новый экземпляр класса Book, созданный с использованием данных из config.properties
     */
    @Bean
    public Book book() {
        return Book.createFromProperties("book.title", "book.author");
    }
}
