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
        return jdbc.queryForObject("select id, name, year_of_publication, author_id, genre_id from books where id = :id",
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
                    .build();
            Genre genre = Genre.builder()
                    .id(resultSet.getInt("genre_id"))
                    .build();
            return new Book(id, name, yearOfPublication, author, genre);
        }
    }
}
