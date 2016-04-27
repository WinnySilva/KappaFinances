package Financas;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Winny S
 */
public class Receita extends Financa 
{
    private CategoriaReceita receita;
    
    
    public Receita()
    {
        this.value = 0;
        this.data = new Date();
        receita = new CategoriaReceita();
    }
    
    public Receita(int year, int month, int day, double value, int categoria)
    {
        this.data = new Date(year, month, day);
        this.value = value;
        receita = new CategoriaReceita(categoria);
    }
    
}
