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
    
    /*
     Construtor default;
     */
    public Despesa()
    {
        this.value = 0;
        this.data = Calendar.getInstance();
        despesa = new CategoriaDespesa();
    }
    /*
    Construtor principal;
    Inicializa os atributos herdados de finança;
    data = recebe ano/mes/dia da transação;
    value = valor da transação;
    */
    public Despesa(Calendar date, double value, int categoria)
    {
        this.data = Calendar.getInstance();
        this.data.set(date.get(Calendar.YEAR), 
                date.get(Calendar.MONTH), 
                date.get(Calendar.DAY_OF_MONTH));
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
