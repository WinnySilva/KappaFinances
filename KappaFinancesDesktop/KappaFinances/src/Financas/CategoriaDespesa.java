package Financas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Winny S
 */
public class CategoriaDespesa 
{
    private int categoriaDespesa;
     public enum categorias
    {
        VESTUARIO, ENERGIA, AGUA, INTERNET, ALUGUEL, REMEDIOS, LAZER,
        EDUCACAO, ALIMENTOS, PRODUTOS_DOMESTICOS, TRANSPORTE, COMBUSTIVEL,
        BENS_DURAVEIS;
    }
    
    public CategoriaDespesa()
    {
        categoriaDespesa = 0;
    }
 
    public CategoriaDespesa(int categoria)
    {
        categoriaDespesa = categoria;
    }
 
    public void setCategoriaDespesa(int categoria)
    {
        categoriaDespesa = categoria;
    }

   public int getCategoriaDespesa()
   {
       return this.categoriaDespesa;
   }

}
