package fir.im.ui;
import fir.im.dialog.FirDialog;
import fir.im.utils.Resource;
import fir.im.utils.XmlUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadUI extends JPanel implements ActionListener {
    private JButton uploadBtn;
    private JButton settingBtn;
    private JButton closeBtn;
    private static UploadUI uploadUI;
    public UploadUI() {
        setLayout(null);
        this.setSize(322, 518);
        initUI();
        initAction();
        uploadUI = this;
        // TODO Auto-generated constructor stub
    }

    public static UploadUI getInstance(){
        if(uploadUI == null){
            return new UploadUI();
        }
        return uploadUI;
    }
    public void initAction(){
        closeBtn.addActionListener(this);
        uploadBtn.addActionListener(this);
        settingBtn.addActionListener(this);
    }
    public void initUI(){
        JLabel lblNewLabel = new JLabel("fir.im upload");
        lblNewLabel.setForeground(UIManager.getColor("MenuBar.selectionForeground"));
        lblNewLabel.setBounds(113, 20, 125, 16);
        add(lblNewLabel);

        uploadBtn = new JButton();
        uploadBtn.setBounds(130, 126, 117, 29);
        ImageIcon uploadImg = new ImageIcon(Resource.getInstance().getResource("upload.png"));
        uploadBtn.setIcon(uploadImg);
        uploadBtn.setSize(uploadImg.getIconWidth(),uploadImg.getIconHeight());
        uploadBtn.setBorderPainted(false);
        add(uploadBtn);

        settingBtn = new JButton();
        ImageIcon settingImg = new ImageIcon(Resource.getInstance().getResource("setting.png"));
        settingBtn.setBounds(10, 6, 117, 29);
        settingBtn.setIcon(settingImg);
        settingBtn.setSize(settingImg.getIconWidth(),settingImg.getIconHeight());
        settingBtn.setBorderPainted(false);

        add(settingBtn);

        closeBtn = new JButton("");
        ImageIcon closeImg = new ImageIcon(Resource.getInstance().getResource("close.png"));
        closeBtn.setIcon(closeImg);
        closeBtn.setBounds(280, 6, 117, 29);
        closeBtn.setSize(closeImg.getIconWidth(),closeImg.getIconHeight());
        closeBtn.setBorderPainted(false);
        add(closeBtn);
    }


    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(Resource.getInstance().getResource("background.png"));
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, icon.getIconWidth(),
                icon.getIconHeight(), icon.getImageObserver());
//        setSize(icon.getIconWidth(), icon.getIconHeight());  

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == this.settingBtn){
            //TODO: 设置按钮
            FirDialog.getInstance().setContentPane(SettingUI.getInstance());
        }
        if(e.getSource() == this.closeBtn){
            //TODO: 关闭按钮
            System.out.print(".........");
            FirDialog.getInstance().setVisible(false);
        }
        if(e.getSource() == this.uploadBtn){
            //TODO: 上传按钮
        }
    }
}
