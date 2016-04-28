package Financas;

import java.util.Calendar;
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
public abstract class Financa 
{
    Date data;
    double value;
    
    public Financa()
    {
      this.data = new Date();
      this.value = 0;
    }
    
    public Financa(int year, int month, int day, double value)
    {
        data = new Date(year, month, day);
        this.value = value;
    }
    /* Discussao ncessaria, a classe Date é obsoleta 
    public void setDate(int year, int month, int day)
    {
        this.data.setYear(year);
        this.data.setMonth(month);
       // this.data.setDay(day);            LOL nao existe set date dafuq
    }
    
    public Date getDate()
    {
        return this.data.();
    }*/
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public double getValue()
    {
        return this.value;
    }
    
}
