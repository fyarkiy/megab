package com.orchard.repository;

import com.orchard.exceptions.DataProcessingException;
import com.orchard.models.Tree;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TreeRepositoryImpl implements TreeRepository {

    private final SessionFactory sessionFactory;

    public TreeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Tree add(Tree item) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            return item;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Not able to add " + item + " to database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Tree> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Tree> getAllTrees =
                    session.createQuery("From Tree t where t.deleted = false", Tree.class);
            return getAllTrees.getResultList();
        }
    }

    @Override
    public Tree getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tree> treeQuery = session.createQuery("from Tree t where t.deleted = false " +
                    "and t.id =: id", Tree.class);
            treeQuery.setParameter("id", id);
            return treeQuery.uniqueResult();
        }
    }

    @Override
    public boolean remove(Tree tree) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(tree);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Not able to delete " + tree + " in database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    @Override
    public Tree update(Tree tree) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(tree);
            transaction.commit();
            return tree;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Not able to update " + tree + " in database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
