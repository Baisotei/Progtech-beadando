/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Baisotei
 */
public class EredmenyDAOImpl implements EredmenyDAO {

    private EntityManager em;

    public EredmenyDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean createEredmeny(String nev) {

        if (searchEredmeny(nev)) {
            return false;
        } else {

            em.getTransaction().begin();
            Eredmeny e = new Eredmeny(nev, 0);
            em.persist(e);
            em.flush();
            em.getTransaction().commit();
            return true;
        }

    }

    @Override
    public boolean searchEredmeny(String nev) {

        TypedQuery<Eredmeny> q = em.createQuery("SELECT e FROM Eredmeny e WHERE e.nev='" + nev + "'", Eredmeny.class);

        List<Eredmeny> result = q.getResultList();

        return result.size() > 0;

    }

    @Override
    public Eredmeny readEredmeny(String nev) {

        TypedQuery<Eredmeny> q = em.createQuery("SELECT e FROM Eredmeny e WHERE e.nev='" + nev + "'", Eredmeny.class);

        List<Eredmeny> result = q.getResultList();

        return result.get(0);

    }

    @Override
    public void updateEredmeny(Eredmeny e) {

        em.getTransaction().begin();
        e.setEredmeny(e.getEredmeny() + 1);
        em.getTransaction().commit();

    }

}
