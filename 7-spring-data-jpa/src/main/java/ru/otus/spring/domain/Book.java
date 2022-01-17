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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

/**
 * Книга.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "graph.Book",
        attributeNodes = {
                @NamedAttributeNode(value = "author"),
                @NamedAttributeNode(value = "genre")
        })
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
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "author_id")
    private Author author;

    /**
     * Жанр.
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "genre_id")
    private Genre genre;

    /**
     * Комментарий.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<BookComment> comments;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name) && yearOfPublication.equals(book.yearOfPublication)
                && author.equals(book.getAuthor()) && genre.equals(book.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfPublication, author, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfPublication='" + yearOfPublication + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }
}
