package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookCommentRepositoryJpa implements BookCommentRepository {

    @PersistenceContext
    private final EntityManager em;


    @Override
    public BookComment save(BookComment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<BookComment> findById(int id) {
        return Optional.ofNullable(em.find(BookComment.class, id));
    }

    @Override
    public List<BookComment> findAll() {
        return em.createQuery("select bc from BookComment bc", BookComment.class).getResultList();
    }

    @Override
    public void updateTextById(int id, String text) {
        Query query = em.createQuery("update BookComment bc " +
                "set bc.text = :text " +
                "where bc.id = :id");
        query.setParameter("text", text);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(int id) {
        Query query = em.createQuery("delete " +
                "from BookComment bc " +
                "where bc.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
