package fir.im.swing;

import fir.im.utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/1
 * Time: 下午12:28
 * To change this template use File | Settings | File Templates.
 */
public class IconPanel extends JPanel{
    private int width = 80;
    private int height = 80;
    public IconPanel(){
       super();
       this.setSize(width,height);
    }

    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(Resource.getInstance().getResource("icon.png"));
        Image img = icon.getImage();
        g.drawImage(img, 0, 0,getSize().width,getSize().height, this);
//        setSize(icon.getIconWidth(), icon.());

    }
}
