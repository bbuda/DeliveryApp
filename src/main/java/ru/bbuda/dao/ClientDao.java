package ru.bbuda.dao;

public class ClientDao {
    private static ClientDao instance;


    public static synchronized ClientDao getInstance() {
        if (instance == null) {
            instance = new ClientDao();
        }
        return instance;
    }
}
