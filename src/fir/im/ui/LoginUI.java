package fir.im.ui;

import fir.im.dialog.FirDialog;
import fir.im.swing.CloseButton;
import fir.im.utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/1
 * Time: 下午6:31
 * To change this template use File | Settings | File Templates.
 */
public class LoginUI extends JPanel implements ActionListener{
    private CloseButton closeButton;
    public LoginUI(){
        super();
        setLayout(null);
        setSize(500, 500);
        closeButton = new CloseButton();
        closeButton.addActionListener(this);
        add(closeButton);
    }


    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(Resource.getInstance().getResource("login.png"));
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, icon.getIconWidth(),
                icon.getIconHeight(), icon.getImageObserver());

//        setSize(icon.getIconWidth(), icon.getIconHeight());

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
        if(actionEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }
    }
}
