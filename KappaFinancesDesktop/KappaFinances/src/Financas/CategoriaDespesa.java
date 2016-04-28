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
    public static int vestuario            = 1;
    public static int energia              = 2;    
    public static int agua                 = 3;
    public static int internet             = 4;
    public static int aluguel              = 5;
    public static int remedios             = 6;
    public static int lazer                = 7;
    public static int educacao             = 8;
    public static int alimentos            = 9;
    public static int produtosDomesticos   = 10;
    public static int transporte           = 11;
    public static int combustivel          = 12;
    public static int bensDuraveis         = 13;

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
