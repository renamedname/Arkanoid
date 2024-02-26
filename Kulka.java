import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

class Kulka extends Ellipse2D.Float
{
    Plansza p;
    float dx, dy;
    int t=0,f=0;
    Kulka(Plansza p, int x, int y, int dx, int dy)
    {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 10;

        this.p = p;
        this.dx = dx;
        this.dy = dy;
    }

    void nextKrok() {
        x += dx;
        y += dy;

           if (getMinX() < 0 || getMaxX() > p.getWidth()) {dx = -dx;}
//        if (getMinY() < 0 || getMaxY() > p.getHeight()) {
//            dy = -dy;
//
//        }


        if (getMinY() < 0) {
            dy = -dy;
        }
        if (getMaxY() > p.getHeight()) {
            dy = -dy;
            p.lusepoint++;
        }

        // Sprawdzanie kolizji z belką
        if (intersects(p.b)) {
            // Odbicie kulki od górnej krawędzi belki
            if (dy > 0 && p.b.y <= getMaxY() && p.b.y + p.b.height >= getMaxY()) {
                dy = -dy;
            }

            else if (dy < 0 && p.b.y + p.b.height >= getMinY() && p.b.y <= getMinY()) {
                dy = -dy;
            }

            else if (dx > 0 && p.b.x <= getMaxX() && p.b.x + p.b.width >= getMaxX()) {
                dx = -dx;
            } else if (dx < 0 && p.b.x + p.b.width >= getMinX() && p.b.x <= getMinX()) {
                dx = -dx;
            }
        }

            // zmina szybkosci
        if (p.punkty == 10 && f==0){dx = dx*2 ;dy=dy*2; f++;}
        if (p.punkty == 25 && t==0){t++;dx = dx*1.3f ;dy=dy*1.3f;}
        if (p.punkty > 59) {
            p.g2d.drawString("game over you win",30,40);
            dx = 0 ;dy=0;
        }

        ArrayList<Cegielka> cegielkiToRemove = new ArrayList<>();
        for (int i = 0; i < p.cegielki.size(); i++) {
            Cegielka c = p.cegielki.get(i);
            if (c.isVisible() && intersects(c)) {
                if (dy > 0 && c.y <= getMaxY() && c.y + c.height >= getMaxY()) {
                    dy = -dy;
                }

                else if (dy < 0 && c.y + c.height >= getMinY() && c.y <= getMinY()) {
                    dy = -dy;
                }

                else if (dx > 0 && c.x <= getMaxX() && c.x + c.width >= getMaxX()) {
                    dx = -dx;
                } else if (dx < 0 && c.x + c.width >= getMinX() && c.x <= getMinX()) {
                    dx = -dx;
                }

                cegielkiToRemove.add(c);
                p.zwiekszPunkty();
            }
        }



        p.cegielki.removeAll(cegielkiToRemove);  // Usunięcie cegiełek z listy

        p.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}

