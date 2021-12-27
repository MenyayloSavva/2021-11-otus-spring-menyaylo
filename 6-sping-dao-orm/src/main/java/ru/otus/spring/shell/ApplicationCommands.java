package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorServiceImpl;
import ru.otus.spring.service.BookServiceImpl;
import ru.otus.spring.service.GenreServiceImpl;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final AuthorServiceImpl authorService;
    private final BookServiceImpl bookService;
    private final GenreServiceImpl genreService;

    @ShellMethod(value = "Insert book", key = {"i", "insert"})
    public String insertBook(
            @ShellOption int id,
            @ShellOption String name,
            @ShellOption String year,
            @ShellOption(defaultValue = "1") int authorId,
            @ShellOption(defaultValue = "1") int genreId
    ) {
        Author author = authorService.getAuthor(authorId);
        Genre genre = genreService.getGenre(genreId);
        Book book = new Book(id, name, year, author, genre);
        bookService.insertBook(book);
        return String.format("Book inserted: %s", book);
    }

    @ShellMethod(value = "Update book", key = {"u", "update"})
    public String updateBook(
            @ShellOption int id,
            @ShellOption String name,
            @ShellOption String year,
            @ShellOption(defaultValue = "1") int authorId,
            @ShellOption(defaultValue = "1") int genreId
    ) {
        Author author = authorService.getAuthor(authorId);
        Genre genre = genreService.getGenre(genreId);
        Book book = new Book(id, name, year, author, genre);
        bookService.updateBook(book);
        return String.format("Book updated: %s", book);
    }

    @ShellMethod(value = "Delete book", key = {"d", "delete"})
    public String insertBook(@ShellOption int id) {
        bookService.deleteBook(id);
        return String.format("Book with id = %d has deleted!", id);
    }

    @ShellMethod(value = "Get book", key = {"g", "get"})
    public String getBook(@ShellOption int id) {
        Book book = bookService.getBook(id);
        return String.format("Book: %s", book);
    }

}
