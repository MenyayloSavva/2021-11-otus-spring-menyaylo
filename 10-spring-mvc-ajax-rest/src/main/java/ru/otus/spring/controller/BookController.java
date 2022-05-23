package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String insertBook(Model model) {
        Book book = bookService.createBlankBook();
        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") int id, Model model) {
        Book book = bookService.findById(id).orElseThrow();
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(Book book, Model model) {
        if (authorService.findById(book.getAuthor().getId()).isEmpty()) {
            model.addAttribute("exception", "No such author");
            return "/edit";
        }

        if (genreService.findById(book.getGenre().getId()).isEmpty()) {
            model.addAttribute("exception", "No such genre");
            return "/edit";
        }

        bookService.save(book);
        return "redirect:/";
    }
}
