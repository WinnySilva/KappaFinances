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
public class Despesa extends Financa 
{
     private CategoriaDespesa despesa;
    
    
    public Despesa()
    {
        this.value = 0;
        this.data = Calendar.getInstance();
        despesa = new CategoriaDespesa();
    }
    
    public Despesa(Calendar date, double value, int categoria)
    {
        int year, month, day;
        
        this.data = Calendar.getInstance();
        year = date.get(Calendar.YEAR);
        month = date.get(Calendar.MONTH);
        day = date.get(Calendar.DAY_OF_MONTH);
        this.data.set(year, month, day);
        this.value = value;
        this.despesa = new CategoriaDespesa(categoria);
    }
    
    public void setCategoria(int categoria)
    {
        this.despesa.setCategoriaDespesa(categoria);
    }

    @Override
    public int getCategoria() {
        return this.despesa.getCategoriaDespesa();
    }
}
