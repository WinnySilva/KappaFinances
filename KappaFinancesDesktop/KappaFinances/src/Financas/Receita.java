package Financas;

import java.util.Calendar;


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
        this.data = Calendar.getInstance();
        receita = new CategoriaReceita();
    }
    
    public Receita(Calendar date, double value, int categoria)
    {
        int year, month, day;
        
        this.data = Calendar.getInstance();
        year = date.get(Calendar.YEAR);
        month = date.get(Calendar.MONTH);
        day = date.get(Calendar.DAY_OF_MONTH);
        this.data.set(year, month, day);
        this.value = value;
        receita = new CategoriaReceita(categoria);
    }
    
    public void setCategoria(int categoria)
    {
        this.receita.setCategoriaReceita(categoria);
    }

    @Override
    public int getCategoria() {
        return this.receita.getCategoriaReceita();
    }
    
}
