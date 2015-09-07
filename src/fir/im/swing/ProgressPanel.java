package fir.im.swing;

import fir.im.utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/7
 * Time: 下午11:20
 * To change this template use File | Settings | File Templates.
 */
public class ProgressPanel extends JPanel{
    public ProgressPanel(){
        super();
        this.setSize(400,30);
    }
    public void paint(Graphics g){
        RoundRectangle2D.Double rect=new RoundRectangle2D.Double(0, 0, 400, 20, 20, 20);
        g.setClip(rect);
        super.paint(g);

//        setBackground(Color.red);


        ImageIcon icon = new ImageIcon(Resource.getInstance().getResource("touming.png"));
        Image img = icon.getImage();
        g.drawImage(img, 0, 0,400,20, this);

        ImageIcon iconw = new ImageIcon(Resource.getInstance().getResource("toumingw.png"));
        Image imgw = icon.getImage();
        g.drawImage(imgw, 0, 0,400,20, this);


    }
}
