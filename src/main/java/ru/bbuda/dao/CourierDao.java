package ru.bbuda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bbuda.configuration.DatabaseConfig;
import ru.bbuda.model.Courier;

import java.util.List;
import java.util.Optional;

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

    public Optional<Courier> findById(Long id) {
        Session session = sessionFactory.openSession();
        Courier courier = session.find(Courier.class, id);
        session.close();
        return Optional.ofNullable(courier);
    }

    public List<Courier> findAll() {
        Session session = sessionFactory.openSession();
        List<Courier> couriers = session.createQuery("from courier", Courier.class).list();
        session.close();
        return couriers;
    }

    public Long saveOrUpdate(Courier courier) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Courier merged = session.merge(courier);
        transaction.commit();
        session.close();
        
        return merged.getId();
    }

    public void delete(Courier courier) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(courier);
        transaction.commit();
        session.close();
    }
}
