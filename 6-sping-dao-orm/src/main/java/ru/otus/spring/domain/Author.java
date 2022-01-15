package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Автор книги.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder(toBuilder = true)
@Entity
@Table(name = "authors")
public final class Author {
    /**
     * Идентификатор автора.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    /**
     * Имя автора
     */
    @Column(name = "name", nullable = false)
    private final String name;

    /**
     * Страна.
     */
    @Column(name = "country")
    private final String country;

    /**
     * Дата рождения.
     */
    @Column(name = "birth_date")
    private final LocalDate birthDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && name.equals(author.name) && country.equals(author.country) && birthDate.equals(author.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, birthDate);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
