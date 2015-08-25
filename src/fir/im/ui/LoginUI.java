package fir.im.ui;

import fir.im.utils.Resource;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/25
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
public class LoginUI extends JPanel {
    public LoginUI() {
        this.setSize(322, 518);
        setLayout(null);

        JLabel label = new JLabel("设置");
        label.setBounds(136, 6, 61, 16);
        add(label);

        JLabel lblApitokn = new JLabel("api_token");
        lblApitokn.setBounds(6, 53, 82, 16);
        add(lblApitokn);

        JButton tokenBtn = new JButton();
        ImageIcon im = new ImageIcon(Resource.getInstance().getResource("button.png"));
        tokenBtn.setSize(im.getIconWidth(),im.getIconHeight());
        tokenBtn.setIcon(im);
        tokenBtn.setBorderPainted(false);
        tokenBtn.setBounds(131, 48, 117, 29);
        add(tokenBtn);
    }
}
