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
public class CategoriaReceita 
{
    
   private int categoriaReceita;
   public static int salario = 1;
   public static int rendaAlternativa = 2;
   
   public CategoriaReceita()
   {
       this.categoriaReceita = 0;
   }
    
   public CategoriaReceita(int categoria)
   {
       this.categoriaReceita = categoria;
   }
   
   public void setCategoriaReceita(int categoria)
   {
       this.categoriaReceita = categoria;
   }
   
   public int getCategoriaReceita()
   {
       return this.categoriaReceita;
   }
 }
