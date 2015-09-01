package fir.im.swing;

import fir.im.utils.Resource;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/1
 * Time: 下午1:27
 * To change this template use File | Settings | File Templates.
 */
public class ChangeLogTextArea extends JTextArea {
    public ChangeLogTextArea(){
        super();
        this.setMargin(new Insets(10, 10, 10, 10));
    }

    private static final long serialVersionUID = -8220994963464909915L;

    {
        setOpaque(false); // 设置透明
    }

    protected void paintComponent(Graphics g) {
        final ImageIcon imText = new ImageIcon(Resource.getInstance().getResource("jtextarea.png"));
        g.drawImage(imText.getImage(), 0, 0, this);
        super.paintComponent(g);
    }
}
