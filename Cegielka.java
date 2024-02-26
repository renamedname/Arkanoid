
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

class Cegielka extends Rectangle2D.Float
{
    private boolean visible;

    Cegielka(float x, float y, float width, float height)
    {
        super(x, y, width, height);
        visible = true;




    }

    boolean isVisible() {
        return visible;
    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }
}