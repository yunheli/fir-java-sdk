package fir.im.ui;

import fir.im.dialog.FirDialog;
import fir.im.swing.*;
import fir.im.utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppInfoUI extends JPanel implements ActionListener {
    private IconPanel iconPanel;
    private ChangeLogTextArea changeLogTextArea;
    private JLabel versionDisplay;
    private JLabel nameDisplay;
    private JLabel shortDisplay;
    private JLabel descDisplay;

    private JLabel shortTag;
    private JLabel descTag;

    private UploadJButton uploadBtn;
    private CloseButton closeButton;

    public AppInfoUI() {
        setForeground(Color.WHITE);
        setBackground(Color.WHITE);

        // TODO Auto-generated constructor stub
        setSize(500,500);
        setLayout(null);

        initSwing();
        setPosition();
        setupUI();
        initAction();
    }

    private void initAction(){
        uploadBtn.addActionListener(this);
        closeButton.addActionListener(this);
    }

    private void initSwing(){
        versionDisplay = new JLabel("2.1.1  (345)");
        versionDisplay.setForeground(Color.LIGHT_GRAY);
        nameDisplay = new JLabel("高德地图");

        shortTag = new JLabel("短地址");
        shortTag.setForeground(Color.GRAY);
        shortTag.setHorizontalAlignment(SwingConstants.RIGHT);

        shortDisplay = new LinkLabel("http://fir.im/aabb");
        shortDisplay.setForeground(Color.GRAY);
        shortDisplay.setHorizontalAlignment(SwingConstants.LEFT);

        descTag = new JLabel("更新日志");
        descTag.setForeground(Color.GRAY);
        descTag.setHorizontalAlignment(SwingConstants.RIGHT);

        changeLogTextArea = new ChangeLogTextArea();
        uploadBtn = new UploadJButton();
        iconPanel = new IconPanel();
        closeButton = new CloseButton();
    }
    private void setPosition(){
        versionDisplay.setBounds(180, 40, 110, 16);
        nameDisplay.setBounds(180, 83, 61, 16);
        shortTag.setBounds(70, 172, 61, 16);
        shortDisplay.setBounds(180, 172, 200, 16);
        descTag.setBounds(70, 242, 61, 16);
        changeLogTextArea.setBounds(180, 242, 308, 153);
        iconPanel.setBounds(50, 21, 80, 80);

    }
    private void setupUI(){
        add(versionDisplay) ;
        add(nameDisplay);
        add(shortTag);
        add(shortDisplay) ;
        add(descTag);
        add(changeLogTextArea) ;
        add(uploadBtn);
        add(iconPanel);
        add(closeButton);
    }

    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(Resource.getInstance().getResource("tbg.png"));
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, icon.getIconWidth(),
                icon.getIconHeight(), icon.getImageObserver());

//        setSize(icon.getIconWidth(), icon.getIconHeight());

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
        if(actionEvent.getSource() == uploadBtn){
            FirDialog.getInstance().setContentPane(AppUploadingUI.getInstance());
        }
        if(actionEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }
    }
}