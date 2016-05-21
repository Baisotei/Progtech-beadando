/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Baisotei
 */
public interface EredmenyDAO {
    
    public boolean createEredmeny(String nev);
    
    public boolean searchEredmeny(String nev);
    
    public Eredmeny readEredmeny(String nev);
    
    public void updateEredmeny(Eredmeny e);
    
    
    
}
