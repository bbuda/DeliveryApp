package ru.bbuda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bbuda.configuration.DatabaseConfig;
import ru.bbuda.model.Client;

import java.util.List;

public class ClientDao {
    private static ClientDao instance;
    private final SessionFactory sessionFactory;

    private ClientDao() {
        sessionFactory = DatabaseConfig.getInstance().getSessionFactory();
    }

    public static synchronized ClientDao getInstance() {
        if (instance == null) {
            instance = new ClientDao();
        }
        return instance;
    }

    public Client findById(Long id) {
        Session session = sessionFactory.openSession();
        Client client = session.find(Client.class, id);
        session.close();
        return client;
    }

    public List<Client> findAll() {
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        session.close();
        return clients;
    }

    public void saveOrUpdate(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(client);
        transaction.commit();
        session.close();
    }

    public void delete(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(client);
        transaction.commit();
        session.close();
    }
}
