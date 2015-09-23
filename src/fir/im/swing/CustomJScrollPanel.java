package fir.im.swing;

import fir.im.utils.Resource;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/23
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
public class CustomJScrollPanel extends JScrollPane {

    public CustomJScrollPanel(){
        super();
    }

    public CustomJScrollPanel(Component component) {
        super(component);
        this.setForeground(new Color(175,175,175));
    }

    public CustomJScrollPanel(Component component, int i, int i1) {
        super(component, i, i1);
    }


    @Override
    protected void paintComponent(Graphics g) {
        final ImageIcon imText = new ImageIcon(Resource.getInstance().getResource("jtextarea.png"));
        g.drawImage(imText.getImage(), 0, 0, this);
        super.paintComponent(g);
    }
}
