package ru.bbuda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bbuda.configuration.DatabaseConfig;
import ru.bbuda.model.Address;

import java.util.List;
import java.util.Optional;

public class AddressDao {
    private static AddressDao instance;
    private final SessionFactory sessionFactory;

    private AddressDao() {
        sessionFactory = DatabaseConfig.getInstance().getSessionFactory();
    }

    public static synchronized AddressDao getInstance() {
        if (instance == null) {
            instance = new AddressDao();
        }
        return instance;
    }

    public Optional<Address> findById(Long id) {
        Session session = sessionFactory.openSession();
        Address Address = session.find(Address.class, id);
        session.close();
        return Optional.ofNullable(Address);
    }

    public List<Address> findAll() {
        Session session = sessionFactory.openSession();
        List<Address> Addresss = session.createQuery("from Address", Address.class).list();
        session.close();
        return Addresss;
    }

    public String saveOrUpdate(Address Address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Address merged = session.merge(Address);
        transaction.commit();
        session.close();

        return merged.getName();
    }

    public void delete(Address Address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(Address);
        transaction.commit();
        session.close();
    }
}
