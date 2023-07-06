package ru.bbuda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bbuda.configuration.DatabaseConfig;
import ru.bbuda.model.Courier;

import java.util.List;

public class CourierDao {
    private static CourierDao instance;
    private final SessionFactory sessionFactory;

    private CourierDao() {
        sessionFactory = DatabaseConfig.getInstance().getSessionFactory();
    }

    public static synchronized CourierDao getInstance() {
        if (instance == null) {
            instance = new CourierDao();
        }
        return instance;
    }

    public Courier findById(Long id) {
        Session session = sessionFactory.openSession();
        Courier client = session.find(Courier.class, id);
        session.close();
        return client;
    }

    public List<Courier> findAll() {
        Session session = sessionFactory.openSession();
        List<Courier> clients = session.createQuery("from Client", Courier.class).list();
        session.close();
        return clients;
    }

    public void saveOrUpdate(Courier client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(client);
        transaction.commit();
        session.close();
    }

    public void delete(Courier client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(client);
        transaction.commit();
        session.close();
    }
}
