package fir.im.swing;

import fir.im.dialog.FirDialog;
import fir.im.utils.Resource;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/1
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class CloseButton extends JLabel implements MouseListener {
    public CloseButton(){
        super();

        ImageIcon imBack = new ImageIcon(Resource.getInstance().getResource("close.png"));
        this.setIcon(imBack);
        setBounds(452, 18, 500, 29);
        this.setSize(imBack.getIconWidth(),imBack.getIconHeight());
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        FirDialog.getInstance().setVisible(false);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
