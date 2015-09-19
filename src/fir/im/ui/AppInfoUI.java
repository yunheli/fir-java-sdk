package fir.im.ui;

import fir.im.dialog.FirDialog;
import fir.im.dialog.TipDialog;
import fir.im.model.Binary;
import fir.im.service.UploadService;
import fir.im.swing.*;
import fir.im.utils.KeyManager;
import fir.im.utils.OsUtil;
import fir.im.utils.Resource;
import fir.im.utils.SearchFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AppInfoUI extends JPanel implements ActionListener ,MouseListener, UploadService.UploadServiceDelegate {
    private IconPanel iconPanel;
    private ChangeLogTextArea changeLogTextArea;
    private JLabel versionDisplay;
    private JLabel nameDisplay;
    private LinkLabel shortDisplay;
    private JLabel descDisplay;
    ProgressPanel progressPanel;


    private JLabel shortTag;
    private JLabel descTag;

    private UploadJButton uploadBtn;
    private CloseButton closeButton;
    private JLabel settingBtn;
    private JProgressBar progressBar;

    private static AppInfoUI appInfoUI;
    private JLabel selectBtn;
    private Binary binary;
    private String shortLink;
    JLabel percentLabel;
    JLabel percentLabelEclipse;

    public AppInfoUI() {
        setForeground(Color.WHITE);
        setBackground(Color.WHITE);

        setSize(500,500);
        setLayout(null);

        initSwing();
        setPosition();
        setupUI();
        initAction();

        appInfoUI = this;

        progressPanel = new ProgressPanel();
        add(progressPanel);
        progressPanel.setBounds(50,460,400,15);
        progressPanel.setVisible(false);

        percentLabel = new JLabel("当前进度: 0%");
        percentLabel.setForeground(Color.white);
        add(percentLabel);
        percentLabel.setBounds(50,440,400,15);
        percentLabel.setVisible(false);

        percentLabelEclipse = new JLabel("正在上传...(0%)");
        percentLabelEclipse.setForeground(Color.white);
        add(percentLabelEclipse);
        percentLabelEclipse.setBounds(50,450,400,20);
        percentLabelEclipse.setHorizontalAlignment(SwingConstants.CENTER);

        percentLabelEclipse.setFont(new   java.awt.Font("Dialog",   1,   20));

        percentLabelEclipse.setVisible(false);


        progressBar = new JProgressBar();
        progressBar.setBounds(40, 385, 422, 20);
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);

    }

    public void setPercentLabel(double x){
        int m = (int)(x * 100);
        percentLabel.setText("当前进度: "+m+"%");

        percentLabelEclipse.setText("正在上传...("+m+"%)");
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
        uploadBtn.addMouseListener(this);
        selectBtn.addMouseListener(this);
        settingBtn.addMouseListener(this);
    }

    private void initSwing(){
        versionDisplay = new JLabel("2.1.1  (345)");
        versionDisplay.setForeground(new Color(175,175,175));
        nameDisplay = new JLabel("高德地图");
        nameDisplay.setForeground(new Color(175,175,175));



        shortTag = new JLabel("短地址");
        shortTag.setForeground(new Color(175,175,175));
        shortTag.setHorizontalAlignment(SwingConstants.RIGHT);

        shortDisplay = new LinkLabel("");
        shortDisplay.setForeground(new Color(175,175,175));
        shortDisplay.setHorizontalAlignment(SwingConstants.LEFT);

        descTag = new JLabel("更新日志");
        descTag.setForeground(new Color(175,175,175));
        descTag.setHorizontalAlignment(SwingConstants.RIGHT);

        changeLogTextArea = new ChangeLogTextArea();
        uploadBtn = new UploadJButton();
        iconPanel = new IconPanel();
        closeButton = new CloseButton();
        selectBtn = new JLabel();
        ImageIcon select = new ImageIcon(Resource.getInstance().getResource("selectSmall.png"));
        selectBtn.setIcon(select);
        selectBtn.setBounds(400, 195, 30, 30);
        selectBtn.setSize(select.getIconWidth(), select.getIconHeight());


        settingBtn = new JLabel();
        ImageIcon setImg = new ImageIcon(Resource.getInstance().getResource("backBtn.png"));
        settingBtn.setIcon(setImg);
        settingBtn.setBounds(30, 18, 500, 29);
        settingBtn.setSize(setImg.getIconWidth(), setImg.getIconHeight());
    }
    private void setPosition(){
        versionDisplay.setBounds(180, 113, 110, 16);
        nameDisplay.setBounds(180, 70, 200, 16);
        shortTag.setBounds(70, 200, 61, 16);
        shortDisplay.setBounds(180, 200, 200, 16);
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
        shortLink = s;
        shortDisplay.setUrl(s);
    }



    public void upload(String changeLogTextArea){
        //上传按钮隐藏 进度条显示
        uploadBtn.setVisible(false);
        if (KeyManager.getInstance().isEclipse) {
            percentLabelEclipse.setVisible(true);

        }else{
            progressPanel.setVisible(true);
            percentLabel.setVisible(true);
        }
        settingBtn.setVisible(false);

        //======================


        new UploadService().sendBuild(null, Binary.getInstance().filePath, KeyManager.getInstance().getToken(),
                Binary.getInstance(),
                changeLogTextArea,
                this);
        progressBar.setVisible(false);
        progressBar.setValue(0);
    }

    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(Resource.getInstance().getResource("tbg.png"));
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, icon.getIconWidth(),
                icon.getIconHeight(), icon.getImageObserver());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
        if(actionEvent.getSource() == uploadBtn){
//            AppUploadingUI.getInstance().upload(changeLogTextArea.getText());
            upload(changeLogTextArea.getText()) ;
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

    @Override
    public void onUploadFinished(boolean finishedSuccessful) {
//       TipDialog.getInstance().fadeIn(1000);
        if(KeyManager.getInstance().getBrowserState().equals("close") )
        {
            TipDialog.getInstance().tip();
        }

        //上传按钮显示 进度条隐藏
        uploadBtn.setVisible(true);
        if(KeyManager.getInstance().isEclipse){
            percentLabelEclipse.setVisible(false);
            percentLabelEclipse.setText("正在上传...(0%)");
        } else{
            progressPanel.setVisible(false);
            percentLabel.setVisible(false);
            percentLabel.setText("当前进度: 0%");
        }

        //======================
        if(KeyManager.getInstance().getBrowserState() == null ||KeyManager.getInstance().getBrowserState().isEmpty() ){
             KeyManager.getInstance().setBrowserState("open");
        }else if( KeyManager.getInstance().getBrowserState().equals("open")){
            OsUtil.openUrlInBrowser(shortLink);
        }

        settingBtn.setVisible(true);
//
    }

    @Override
    public void onPackageSizeComputed(final long totalSize) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                progressBar.setMaximum((int) totalSize);

            }
        });
    }

    @Override
    public void onProgressChanged(final long progress) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                progressBar.setValue((int) progress);
//                System.out.println(progressBar.getPercentComplete());
                progressPanel.setPercent(progressBar.getPercentComplete());
                setPercentLabel(progressBar.getPercentComplete());
            }
        });    }

    @Override
    public void mouseClicked(MouseEvent actionEvent) {
        if(actionEvent.getSource() == uploadBtn){
//            AppUploadingUI.getInstance().upload(changeLogTextArea.getText());
            upload(changeLogTextArea.getText()) ;
        }
        if(actionEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }
        if(actionEvent.getSource() == selectBtn){
            LoginUI.getInstance().selectApk();
        }

        if(actionEvent.getSource() == settingBtn){
            FirDialog.getInstance().setContentPane(LoginUI.getInstance());
        }    }

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