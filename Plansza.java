import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

import java.util.ArrayList;

class Plansza extends JPanel implements MouseMotionListener
{
    Belka b;
    Kulka a;
    SilnikKulki s;
    ArrayList<Cegielka> cegielki;
    public int punkty,lusepoint;
    Graphics2D g2d;

    Plansza()
    {
        super();
        addMouseMotionListener(this);
        b = new Belka(100);
        a = new Kulka(this, 100, 300, 2, 2);
        s = new SilnikKulki(a);
        cegielki = new ArrayList<>();
        punkty = 0;


        for (int i = 0; i < 30; i++) {
            for (int j = 1; j < 4; j++) {
                float xx = i*35;
                float yy = j*25 ;

                Cegielka c = new Cegielka(xx, yy, 30, 20);
                cegielki.add(c);
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2d=(Graphics2D)g;

        if(lusepoint<10){
        g2d.fill(a);
        g2d.fill(b);}


        for (Cegielka c : cegielki) {
            if (c.isVisible() && lusepoint<10) {
                g2d.fill(c);
            }
        }

        g2d.setColor(Color.BLACK);
        g2d.drawString("Punkty: " + punkty, 9, 15);
        g2d.drawString("heals point: " + (10-lusepoint), 100, 15);
        if (punkty > 59) {
            g2d.drawString("game over you win", 30, 40);
        }

        if (lusepoint >= 10) {
            a.dx = 0 ;
            a.dy = 0 ;
            a.x = 0 ;
            a.y = 0 ;

            g2d.drawString("game over you lose!!!", 30, 40);
        }

    }

    public void mouseMoved(MouseEvent e)
    {
        b.setX(e.getX() - 50);
        repaint();
        if (punkty > 59) {
            g2d.drawString("game over you win", 30, 40);
        }
    }

    public void mouseDragged(MouseEvent e)
    {
    }

    public void zwiekszPunkty() {
        punkty++;
    }
}