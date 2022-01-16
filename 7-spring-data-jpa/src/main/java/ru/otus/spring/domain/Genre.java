package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Жанр книги.
 */
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(toBuilder = true)
@Entity
@Table(name = "genres")
public final class Genre {
    /**
     * Идентификатор жанра.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    /**
     * Название жанра.
     */
    @Column(name = "name", nullable = false)
    private final String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
