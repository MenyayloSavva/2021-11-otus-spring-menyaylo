package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static ru.otus.spring.util.DateUtils.getLocalDateFromDate;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void insert(Book book) {
        jdbc.update("insert into books (id, name, year_of_publication, author_id, genre_id)" +
                        " values (:id, :name, :year_of_publication, :author_id, :genre_id)",
                Map.of("id", book.getId(),
                        "name", book.getName(),
                        "year_of_publication", book.getYearOfPublication(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId())
        );
    }

    @Override
    public Book getById(int id) {
        return jdbc.queryForObject("select b.id, b.name, b.year_of_publication, " +
                        "a.id as author_id, a.name as author_name, a.country as author_country, a.birth_date as author_birth_date, " +
                        "g.id as genre_id, g.name as genre_name " +
                        "from books b join authors a on b.author_id = a.id " +
                        "join genres g on b.genre_id = g.id " +
                        "where b.id = :id",
                Map.of("id", id),
                new BookMapper()
        );
    }

    @Override
    public void update(Book book) {
        jdbc.update("update books set name = :name, year_of_publication = :yearOfPublication," +
                        " author_id = :authorId, genre_id = :genreId where id = :id",
                Map.of("name", book.getName(),
                        "yearOfPublication", book.getYearOfPublication(),
                        "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId(),
                        "id", book.getId())
        );
    }

    @Override
    public void deleteById(int id) {
        jdbc.update("delete from books where id = :id",
                Map.of("id", id)
        );
    }

    @RequiredArgsConstructor
    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String yearOfPublication = resultSet.getString("year_of_publication");

            Author author = Author.builder()
                    .id(resultSet.getInt("author_id"))
                    .name(resultSet.getString("author_name"))
                    .country(resultSet.getString("author_country"))
                    .birthDate(getLocalDateFromDate(resultSet.getDate("author_birth_date")))
                    .build();

            Genre genre = Genre.builder()
                    .id(resultSet.getInt("genre_id"))
                    .name(resultSet.getString("genre_name"))
                    .build();

            return new Book(id, name, yearOfPublication, author, genre);
        }
    }
}
