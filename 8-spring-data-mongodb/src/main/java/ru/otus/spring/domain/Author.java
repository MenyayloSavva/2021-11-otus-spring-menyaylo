package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Автор книги.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "authors")
public final class Author {
    /**
     * Идентификатор автора.
     */
    @Id
    private long id;

    /**
     * Имя автора
     */
    private String name;

    /**
     * Страна.
     */
    private String country;

    /**
     * Дата рождения.
     */
    @Field(name = "birth_date")
    private LocalDate birthDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name) && country.equals(author.country) && birthDate.equals(author.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, birthDate);
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
