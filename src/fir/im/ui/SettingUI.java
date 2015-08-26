package fir.im.ui;

import fir.im.dialog.FirDialog;
import fir.im.utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/25
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
public class SettingUI extends JPanel {
    JTextField textField ;
    JButton backBtn;
    private static  SettingUI settingUI;
    public SettingUI() {
        this.setSize(322, 518);
        setLayout(null);

        JLabel label = new JLabel("设置");
        label.setForeground(Color.white);
        label.setBounds(136, 6, 61, 16);
        add(label);

        JLabel lblApitokn = new JLabel("api_token");
        lblApitokn.setForeground(Color.white);
        lblApitokn.setBounds(6, 53, 82, 16);
        add(lblApitokn);

        JButton tokenBtn = new JButton("保存");
        tokenBtn.setBounds(172, 103, 117, 29);

        add(tokenBtn);

        textField = new JTextField();
        textField.setBounds(107, 47, 182, 28);
        add(textField);
        textField.setColumns(10);

        backBtn = new JButton();
        ImageIcon imBack = new ImageIcon(Resource.getInstance().getResource("back.png"));
        backBtn.setSize(imBack.getIconWidth(),imBack.getIconHeight());
        backBtn.setIcon(imBack);
        backBtn.setBorderPainted(false);
        backBtn.setBounds(-6, 1, 117, 29);
        add(backBtn);

        initAction();
        settingUI = this;
    }
    public static SettingUI getInstance(){
        if(settingUI == null){
            return new SettingUI();
        }else{
            return settingUI;
        }
    }
    private void initAction(){
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                FirDialog.getInstance().setContentPane(UploadUI.getInstance());
            }
        });
    }

    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(Resource.getInstance().getResource("background.png"));
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, icon.getIconWidth(),
                icon.getIconHeight(), icon.getImageObserver());
//        setSize(icon.getIconWidth(), icon.getIconHeight());

    }
}
