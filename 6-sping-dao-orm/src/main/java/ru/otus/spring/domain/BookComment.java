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
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * Комментарий к книге.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder(toBuilder = true)
@Entity
@Table(name = "book_comments")
@NamedEntityGraph(name = "graph.BookComment.book",
        attributeNodes = @NamedAttributeNode(value = "book", subgraph = "subgraph.book"),
        subgraphs = @NamedSubgraph(name = "subgraph.book",
                attributeNodes = {
                        @NamedAttributeNode("author"),
                        @NamedAttributeNode("genre")
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
        return id == that.id && text.equals(that.text) && book.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, book);
    }

    @Override
    public String toString() {
        return "BookComment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", book_id=" + (nonNull(book) ? book.getId() : "null") +
                '}';
    }
}
