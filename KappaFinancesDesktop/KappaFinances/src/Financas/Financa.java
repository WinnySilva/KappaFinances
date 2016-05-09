package Financas;
import XMLHandler.*;

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
public abstract class Financa 
{
    protected Calendar data;
    protected double value;
    /*
    Construtor default;
    */
    public Financa()
    {
      Calendar data = Calendar.getInstance();
      this.value = 0;
    }

    /*
    Construtor principal;
    data = recebe ano/mes/dia da transação;
    value = valor da transação;
    */
    public Financa(Calendar date, double value)
    {   
        this.data = Calendar.getInstance();
        this.data.set(date.get(Calendar.YEAR), 
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH));
        this.value = value;
    }

    public void setDate(Calendar date)
    {
        this.data.set(date.get(Calendar.YEAR),
                date.get(Calendar.MONTH), 
                date.get(Calendar.DAY_OF_MONTH));
    }
    
    public Calendar getDate()
    {
        return this.data;
    }
    
    public void setValue(double value)
    {
        this.value = value;
    }
    
    public double getValue()
    {
        return this.value;
    }
    public abstract int getCategoria();
}
