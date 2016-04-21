package Relatorios;

import java.awt.Graphics2D;
import java.util.Observer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Winny S
 */
public abstract class Relatorio implements Observer {
    
    public abstract Graphics2D  geraGrafico();
    
}
