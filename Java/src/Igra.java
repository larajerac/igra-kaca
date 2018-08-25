import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.*;

public class Igra implements Runnable, KeyListener{

    private final Mreza mreza;
    private final Izgled izgled;
   
    private static boolean running = false;
    
   
    public Igra(Mreza mreza, Izgled izgled) {
        this.mreza = mreza;
        this.izgled = izgled;
        
        Igra.running = true;
    }
    
    
    
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(Math.max(50, 200 - mreza.pridobiTocke() / 5 * 30));                    
            } catch (InterruptedException e) {
                break;
            }
            mreza.seSmerSpremeni = false;
            if (mreza.naslRunda() == true) {
                izgled.narisi();
            } else {
            	
            	
                izgled.sporociloKonec(); //ob koncu igre poklièemo pojavno okno
                running = false;
                
               
            }
        }
    }
    // smer spreminjamo ob kliku na pušèice
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (mreza.seSmerSpremeni == false) {
            switch (keyCode) {
                case KeyEvent.VK_UP :
                    mreza.spremeniSmer(Smer.gor);
                    break; 
                case KeyEvent.VK_RIGHT :
                    mreza.spremeniSmer(Smer.desno);
                    break;
                case KeyEvent.VK_DOWN :
                    mreza.spremeniSmer(Smer.dol);
                    break;
                case KeyEvent.VK_LEFT :
                    mreza.spremeniSmer(Smer.levo);
                    break;
                case KeyEvent.VK_SPACE : //za ponovno igro kliknemo space
                    
                    Aplikacija aplikacija = new Aplikacija();
                    aplikacija.run();
                   
              
                    break;
                
            }
        }
        
    }
   
	
