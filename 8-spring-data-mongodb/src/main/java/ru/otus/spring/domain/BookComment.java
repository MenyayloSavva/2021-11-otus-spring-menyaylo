package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

/**
 * Комментарий к книге.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "book_comments")
public final class BookComment {
    /**
     * Идентификатор комментария.
     */
    @Id
    private long id;

    /**
     * Текст.
     */
    private String text;

    /**
     * Книга.
     */
    @Field(name = "book_id")
    @DocumentReference
    private Book book;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookComment that = (BookComment) o;
        return text.equals(that.text) && book.equals(that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, book);
    }

    @Override
    public String toString() {
        return "BookComment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", book=" + book +
                '}';
    }
}
