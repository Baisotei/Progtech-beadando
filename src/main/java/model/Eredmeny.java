/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eredmenylista")
public class Eredmeny {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nev")
    private String nev;
    @Column(name = "eredmeny")
    private int eredmeny;

    public Eredmeny(String nev, int eredmeny) {
        this.nev = nev;
        this.eredmeny = eredmeny;
    }

    public Eredmeny() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getEredmeny() {
        return eredmeny;
    }

    public void setEredmeny(int eredmeny) {
        this.eredmeny = eredmeny;
    }

}
