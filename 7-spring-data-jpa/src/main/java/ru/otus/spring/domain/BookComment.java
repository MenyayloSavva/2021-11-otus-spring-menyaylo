package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Комментарий к книге.
 */
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(toBuilder = true)
@Entity
@Table(name = "book_comments")
@NamedEntityGraph(name = "graph.BookComment",
        attributeNodes = @NamedAttributeNode(value = "book", subgraph = "subgraph.Book"),
        subgraphs = @NamedSubgraph(name = "subgraph.Book",
                attributeNodes = {
                        @NamedAttributeNode(value = "author"),
                        @NamedAttributeNode(value = "genre")
                }))
public final class BookComment {
    /**
     * Идентификатор комментария.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    /**
     * Текст.
     */
    @Column(name = "text", nullable = false)
    private final String text;

    /**
     * Книга.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private final Book book;


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
