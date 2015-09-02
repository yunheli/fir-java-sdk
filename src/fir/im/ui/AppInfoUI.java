package fir.im.ui;

import fir.im.dialog.FirDialog;
import fir.im.model.Binary;
import fir.im.swing.*;
import fir.im.utils.Resource;
import fir.im.utils.SearchFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppInfoUI extends JPanel implements ActionListener {
    private IconPanel iconPanel;
    private ChangeLogTextArea changeLogTextArea;
    private JLabel versionDisplay;
    private JLabel nameDisplay;
    private LinkLabel shortDisplay;
    private JLabel descDisplay;

    private JLabel shortTag;
    private JLabel descTag;

    private UploadJButton uploadBtn;
    private CloseButton closeButton;
    private JButton settingBtn;

    private static AppInfoUI appInfoUI;
    private JButton selectBtn;
    private Binary binary;
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

        appInfoUI = this;
    }

    public static AppInfoUI getInstance(){
        if(appInfoUI == null) return  new AppInfoUI();
        return appInfoUI;
    }

    public void initBinary(Binary binary){
        this.binary = binary;
        initVal();
        FirDialog.getInstance().setContentPane(this);
    }

    private void initVal(){
        nameDisplay.setText(binary.name);
        versionDisplay.setText(""+binary.versionName+"( "+binary.versionCode+" )");
        iconPanel.setIcon(SearchFile.getInstance().queryIcon(Binary.getInstance().icon));
    }


    private void initAction(){
        uploadBtn.addActionListener(this);
        closeButton.addActionListener(this);
        selectBtn.addActionListener(this);
        settingBtn.addActionListener(this);
    }

    private void initSwing(){
        versionDisplay = new JLabel("2.1.1  (345)");
        versionDisplay.setForeground(Color.LIGHT_GRAY);
        nameDisplay = new JLabel("高德地图");
        nameDisplay.setForeground(Color.GRAY);


        shortTag = new JLabel("短地址");
        shortTag.setForeground(Color.GRAY);
        shortTag.setHorizontalAlignment(SwingConstants.RIGHT);

        shortDisplay = new LinkLabel("");
        shortDisplay.setForeground(Color.GRAY);
        shortDisplay.setHorizontalAlignment(SwingConstants.LEFT);

        descTag = new JLabel("更新日志");
        descTag.setForeground(Color.GRAY);
        descTag.setHorizontalAlignment(SwingConstants.RIGHT);

        changeLogTextArea = new ChangeLogTextArea();
        uploadBtn = new UploadJButton();
        iconPanel = new IconPanel();
        closeButton = new CloseButton();
        selectBtn = new JButton();
        ImageIcon select = new ImageIcon(Resource.getInstance().getResource("selectSmall.png"));
        selectBtn.setIcon(select);
        selectBtn.setBorderPainted(false);
        selectBtn.setBounds(400, 168, 30, 30);
        selectBtn.setSize(select.getIconWidth(), select.getIconHeight());


        settingBtn = new JButton();
        ImageIcon setImg = new ImageIcon(Resource.getInstance().getResource("tokenSet.png"));
        settingBtn.setIcon(setImg);
        settingBtn.setBorderPainted(false);
        settingBtn.setBounds(30, 18, 500, 29);
        settingBtn.setSize(setImg.getIconWidth(), setImg.getIconHeight());
    }
    private void setPosition(){
        versionDisplay.setBounds(180, 70, 110, 16);
        nameDisplay.setBounds(180, 113, 200, 16);
        shortTag.setBounds(70, 182, 61, 16);
        shortDisplay.setBounds(180, 182, 200, 16);
        descTag.setBounds(70, 242, 61, 16);
        changeLogTextArea.setBounds(180, 242, 308, 153);
        iconPanel.setBounds(60, 60, 80, 80);

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
        add(selectBtn);
        add(settingBtn);
    }

    public void setShortDisplay(String s){
        shortDisplay.setUrl(s);
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
            AppUploadingUI.getInstance().upload(changeLogTextArea.getText());
        }
        if(actionEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }
        if(actionEvent.getSource() == selectBtn){
            LoginUI.getInstance().selectApk();
        }

        if(actionEvent.getSource() == settingBtn){
            FirDialog.getInstance().setContentPane(LoginUI.getInstance());
        }

    }
}