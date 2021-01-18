package com.orchard.repository;

import com.orchard.exceptions.DataProcessingException;
import com.orchard.models.Specie;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SpecieRepositoryImpl implements SpecieRepository {
    private final SessionFactory sessionFactory;

    public SpecieRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Specie add(Specie item) {
        Transaction transaction = null;
        Session session = null;
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
            throw new DataProcessingException("Not able to add " + item + " to data Base", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Specie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Specie> getAllSpecies =
                    session.createQuery("from Specie s where s.deleted = false", Specie.class);
            return getAllSpecies.getResultList();
        }
    }

    @Override
    public Specie getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Specie> specieQuery = session.createQuery("from Specie s where s.id =: id" +
                    " and s.deleted = false", Specie.class);
            specieQuery.setParameter("id", id);
            return specieQuery.uniqueResult();
        }
    }

    @Override
    public boolean remove(Specie specie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(specie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Not able to delete " + specie + " in database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    @Override
    public Specie update(Specie specie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(specie);
            transaction.commit();
            return specie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Not able to update " + specie + " in data Base", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
