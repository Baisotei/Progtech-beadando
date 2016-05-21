/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Baisotei
 */
public class EredmenyDAOFactory implements AutoCloseable {

    private static EredmenyDAOFactory instance;

    private static EntityManager em;
    private static EntityManagerFactory f;

    static {
        instance = new EredmenyDAOFactory();
        f = Persistence.createEntityManagerFactory("db");
        em = f.createEntityManager();

    }

    private EredmenyDAOFactory() {
    }

    public static EredmenyDAOFactory getInstance() {

        return instance;
    }

    public EredmenyDAO createEredmenyDAO() {
        return new EredmenyDAOImpl(em);
    }

    @Override
    public void close() {
        em.close();
        f.close();
    }

}
