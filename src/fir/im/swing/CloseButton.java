package fir.im.swing;

import fir.im.utils.Resource;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/1
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class CloseButton extends JLabel {
    public CloseButton(){
        super();

        ImageIcon imBack = new ImageIcon(Resource.getInstance().getResource("close.png"));
        this.setIcon(imBack);
        setBounds(452, 18, 500, 29);
        this.setSize(imBack.getIconWidth(),imBack.getIconHeight());
    }
}
