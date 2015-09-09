package fir.im.swing;

import fir.im.utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/7
 * Time: 下午11:20
 * To change this template use File | Settings | File Templates.
 */
public class ProgressPanel extends JPanel{
    ImageIcon iconw = new ImageIcon(Resource.getInstance().getResource("toumingw.png"));
    ImageIcon icon = new ImageIcon(Resource.getInstance().getResource("touming.png"));


    int draw_width = -400;
    Boolean draw_bool = false;
    public ProgressPanel(){
        super();
        this.setSize(400,20);

    }
    public void paint(Graphics g){
        super.paint(g);
//
        Graphics2D  w = (Graphics2D)g;

        RoundRectangle2D.Double rect=new RoundRectangle2D.Double(0, 0, 400, 15, 15, 15);
        w.setClip(rect);


//       setBackground(Color.red);
//
//
        if (draw_bool == false)
        {
            Image img = icon.getImage();
            w.drawImage(img, 0, 0,400,15, this);
            draw_bool = true;
        }


        Image imgw = icon.getImage();
        w.drawImage(imgw, draw_width, 0,400,15, this);

    }



    public void setPercent(double x){
         draw_width = (int)(400 * x) - 400;
         System.out.println("draw_width#"+draw_width);
         repaint();
    }
}
