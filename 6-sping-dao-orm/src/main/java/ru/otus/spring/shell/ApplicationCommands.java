package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorServiceImpl;
import ru.otus.spring.service.BookCommentServiceImpl;
import ru.otus.spring.service.BookServiceImpl;
import ru.otus.spring.service.GenreServiceImpl;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final AuthorServiceImpl authorService;
    private final BookCommentServiceImpl bookCommentService;
    private final BookServiceImpl bookService;
    private final GenreServiceImpl genreService;

    @ShellMethod(value = "Save book", key = {"sb"})
    public String saveBook(
            @ShellOption long id,
            @ShellOption String name,
            @ShellOption(defaultValue = "9999") String year,
            @ShellOption(defaultValue = "1") long authorId,
            @ShellOption(defaultValue = "1") long genreId
    ) {
        Optional<Author> author = authorService.findById(authorId);
        Optional<Genre> genre = genreService.findById(genreId);
        Book book = new Book(id, name, year, author.orElse(null), genre.orElse(null), null);
        bookService.save(book);
        return String.format("Book has been saved: %s", book);
    }

    @ShellMethod(value = "Find book by id", key = {"fb"})
    public String findBookById(
            @ShellOption long id
    ) {
        Optional<Book> book = bookService.findById(id);
        return book.isPresent() ? String.format("Book has been found: %s", book) : String.format("Book hasn't been found!");
    }

    @ShellMethod(value = "Find all books", key = {"fab"})
    public String findAllBooks() {
        List<Book> books = bookService.findAll();
        return String.format("All found books: %s", books);
    }

    @ShellMethod(value = "Find books by Name", key = {"fbn"})
    public String findBooksByName(
            @ShellOption String name
    ) {
        List<Book> books = bookService.findByName(name);
        return String.format("All found books by name: %s", books);
    }

    @ShellMethod(value = "Update book name by id", key = {"ubn"})
    public String updateBookNameById(
            @ShellOption long id,
            @ShellOption String name
    ) {
        bookService.updateNameById(id, name);
        return String.format("Book name has been updated!");
    }

    @ShellMethod(value = "Delete book by id", key = {"db"})
    public String deleteBookById(
            @ShellOption long id
    ) {
        bookService.deleteById(id);
        return String.format("Book has been deleted!");
    }


    @ShellMethod(value = "Save book comment", key = {"sbc"})
    public String saveBookComment(
            @ShellOption long id,
            @ShellOption String text,
            @ShellOption(defaultValue = "1") int bookId
    ) {
        Optional<Book> book = bookService.findById(bookId);
        BookComment bookComment = new BookComment(id, text, book.orElse(null));
        bookCommentService.save(bookComment);
        return String.format("Book comment has been saved: %s", bookComment);
    }

    @ShellMethod(value = "Find book comment by id", key = {"fbc"})
    public String findBookCommentById(
            @ShellOption long id
    ) {
        Optional<BookComment> bookComment = bookCommentService.findById(id);
        return bookComment.isPresent() ? String.format("Book comment has been found: %s", bookComment) :
                String.format("Book comment hasn't been found!");
    }

    @ShellMethod(value = "Find book comments by book_id", key = {"fbcb"})
    public String findBookCommentByBookId(
            @ShellOption long id
    ) {
        List<BookComment> bookComments = bookCommentService.findByBookId(id);
        return String.format("All found book comments: %s", bookComments);
    }

    @ShellMethod(value = "Find all book comments", key = {"fabc"})
    public String findAllBookComments() {
        List<BookComment> bookComments = bookCommentService.findAll();
        return String.format("All found book comments: %s", bookComments);
    }

    @ShellMethod(value = "Update book comment text by id", key = {"ubct"})
    public String updateBookCommentTextById(
            @ShellOption long id,
            @ShellOption String text
    ) {
        bookCommentService.updateTextById(id, text);
        return String.format("Book comment text has been updated!");
    }

    @ShellMethod(value = "Delete book comment by id", key = {"dbc"})
    public String deleteBookCommentById(
            @ShellOption long id
    ) {
        bookCommentService.deleteById(id);
        return String.format("Book comment has been deleted!");
    }
}
