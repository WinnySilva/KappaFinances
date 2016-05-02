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
    
    public Financa()
    {
      Calendar data = Calendar.getInstance();
      this.value = 0;
    }
    
    public Financa(Calendar date, double value)
    {
        int year, month, day;
        
        this.data = Calendar.getInstance();
        year = date.get(Calendar.YEAR);
        month = date.get(Calendar.MONTH);
        day = date.get(Calendar.DAY_OF_MONTH);
        this.data.set(year, month, day);
        this.value = value;
    }

    public void setDate(Calendar date)
    {
        int year, month, day;
        
        year = date.get(Calendar.YEAR);
        month = date.get(Calendar.MONTH);
        day = date.get(Calendar.DAY_OF_MONTH);
        this.data.set(year, month, day);
    }
    
    public Calendar getDate()
    {
        return this.data;
    }
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public double getValue()
    {
        return this.value;
    }
    public abstract int getCategoria();
}
