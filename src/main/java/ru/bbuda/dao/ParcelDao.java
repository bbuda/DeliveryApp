package ru.bbuda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bbuda.configuration.DatabaseConfig;
import ru.bbuda.model.Parcel;

import java.util.List;

public class ParcelDao {
    private static ParcelDao instance;
    private final SessionFactory sessionFactory;

    private ParcelDao() {
        sessionFactory = DatabaseConfig.getInstance().getSessionFactory();
    }

    public static synchronized ParcelDao getInstance() {
        if (instance == null) {
            instance = new ParcelDao();
        }
        return instance;
    }

    public Parcel findById(Long id) {
        Session session = sessionFactory.openSession();
        Parcel client = session.find(Parcel.class, id);
        session.close();
        return client;
    }

    public List<Parcel> findAll() {
        Session session = sessionFactory.openSession();
        List<Parcel> clients = session.createQuery("from Client", Parcel.class).list();
        session.close();
        return clients;
    }

    public void saveOrUpdate(Parcel client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(client);
        transaction.commit();
        session.close();
    }

    public void delete(Parcel client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(client);
        transaction.commit();
        session.close();
    }
}
