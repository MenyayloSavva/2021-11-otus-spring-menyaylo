package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * Книга.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder(toBuilder = true)
@Entity
@Table(name = "books")
public final class Book {
    /**
     * Идентификатор книги.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Название книги.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Год издания.
     */
    @Column(name = "year_of_publication")
    private String yearOfPublication;

    /**
     * Автор.
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "author_id")
    private Author author;

    /**
     * Жанр.
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "genre_id")
    private Genre genre;

    /**
     * Комментарий.
     */
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, mappedBy = "book")
    private List<BookComment> comments;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && name.equals(book.name) && yearOfPublication.equals(book.yearOfPublication)
                && author.getId() == book.getAuthor().getId() && genre.getId() == book.getGenre().getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, yearOfPublication, author, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfPublication='" + yearOfPublication + '\'' +
                ", author_id=" + (nonNull(author) ? author.getId() : "null") +
                ", genre_id=" + (nonNull(author) ? genre.getId() : "null") +
                '}';
    }
}
