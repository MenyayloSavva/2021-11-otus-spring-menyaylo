package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BookComment;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public Optional<BookComment> findById(long id) {
        return Optional.ofNullable(em.find(BookComment.class, id));
    }

    @Override
    public List<BookComment> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("graph.BookComment.book");
        TypedQuery<BookComment> query = em.createQuery("select bc from BookComment bc ", BookComment.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Optional<BookComment> bookComment = findById(id);
        bookComment.ifPresent(bc -> em.remove(bc));
    }
}
