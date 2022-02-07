package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document(collection = "books")
public final class Book {
    /**
     * Идентификатор книги.
     */
    @Id
    private long id;

    /**
     * Название книги.
     */
    private String name;

    /**
     * Год издания.
     */
    @Field(name = "year_of_publication")
    private String yearOfPublication;

    /**
     * Автор.
     */
    @Field(name = "author_id")
    @DocumentReference
    private Author author;

    /**
     * Жанр.
     */
    @Field(name = "genre_id")
    @DocumentReference
    private Genre genre;

    /**
     * Комментарий.
     */
    @ReadOnlyProperty
    @DocumentReference(lookup = "{'book':?#{#self._id} }")
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
