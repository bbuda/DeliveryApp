package ru.bbuda.configuration;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bbuda.model.Client;

import java.io.IOException;
import java.util.Properties;

@Getter
public class DatabaseConfig {

    private static DatabaseConfig instance;
    private final SessionFactory sessionFactory;

    private DatabaseConfig() {
        try {
            sessionFactory = new Configuration().setProperties(getHibernateProperties())
                                                .addAnnotatedClass(Client.class)
                                                .addAnnotatedClass(Package.class)
                                                .configure()
                                                .buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

//    public void shutdown() {
//        sessionFactory.close();
//    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("DB.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки данных конфигурации базы данных", e);
        }
        return properties;
    }
}
