package fir.im.ui;

import fir.im.dialog.FirDialog;
import fir.im.model.Binary;
import fir.im.swing.CloseButton;
import fir.im.utils.FileOperate;
import fir.im.utils.KeyManager;
import fir.im.utils.Resource;
import fir.im.utils.SearchFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/1
 * Time: 下午6:31
 * To change this template use File | Settings | File Templates.
 */
public class LoginUI extends JPanel implements ActionListener, MouseListener{
    private CloseButton closeButton;
    private JLabel tokenTag;
    private JLabel tokenDisplay;
    private JLabel tokenSetting;
    private JLabel selectBtn;
    private JLabel openBrowser;
    private JLabel openBrowserOption;
    private JLabel closeBrowserOption;
    ImageIcon openBrowserOptionImg = new ImageIcon(Resource.getInstance().getResource("browser_radio_button_on.png"));
    ImageIcon closeBrowserOptionImg = new ImageIcon(Resource.getInstance().getResource("browser_radio_button_off.png"));

    private static LoginUI loginUI;
    public LoginUI(){
        super();
        setLayout(null);
        setSize(500, 500);
        initSwing();
        initAction();
        initPosition();
        setUpUI();
        initValue();
        loginUI = this;

    }

    public static LoginUI getInstance(){
        if(loginUI == null) return new LoginUI();
        return loginUI;
    }
    private void initSwing(){
        closeButton = new CloseButton();
        tokenTag = new JLabel("设置api_token");
        tokenTag.setForeground(new Color(175,175,175));
        tokenDisplay = new JLabel();
        tokenDisplay.setForeground(new Color(175,175,175));


        tokenSetting = new JLabel();
        ImageIcon tokenSet = new ImageIcon(Resource.getInstance().getResource("tokenSet.png"));
        tokenSetting.setIcon(tokenSet);
        tokenSetting.setBounds(420, 150, 100, 100);
        tokenSetting.setSize(tokenSet.getIconWidth(), tokenSet.getIconHeight());

        selectBtn = new JLabel();
        ImageIcon select = new ImageIcon(Resource.getInstance().getResource("select.png"));
        selectBtn.setIcon(select);
        selectBtn.setBounds(200, 300, 100, 100);
        selectBtn.setSize(select.getIconWidth(), select.getIconHeight());

        openBrowser = new JLabel("浏览器开关");
        openBrowser.setForeground(new Color(175,175,175));
        openBrowser.setBounds(80,180,100,30);
        openBrowser.setToolTipText("上传完成之后是否直接打开浏览器设置");

        openBrowserOption = new JLabel();
        openBrowserOption.setIcon(openBrowserOptionImg);
        openBrowserOption.setBounds(200,185,50,50);
        openBrowserOption.setSize(openBrowserOptionImg.getIconWidth(), openBrowserOptionImg.getIconHeight());
        openBrowserOption.setToolTipText("当前是选中状态，上传完成之后会直接打开浏览器");

        closeBrowserOption = new JLabel();
        closeBrowserOption.setIcon(closeBrowserOptionImg);
        closeBrowserOption.setBounds(200,185,50,50);
        closeBrowserOption.setSize(closeBrowserOptionImg.getIconWidth(), closeBrowserOptionImg.getIconHeight());
        closeBrowserOption.setToolTipText("当前是取消选中状态，上传完成之后不直接打开浏览器");

        if(KeyManager.getInstance().getBrowserState() == null || KeyManager.getInstance().getBrowserState().isEmpty()) {
            KeyManager.getInstance().setBrowserState("open");
            closeBrowserOption.setVisible(false);
        }else if(KeyManager.getInstance().getBrowserState().equals("open")){
            closeBrowserOption.setVisible(false);
        }else if(KeyManager.getInstance().getBrowserState().equals("close")){
            openBrowserOption.setVisible(false);
        }
    }

    private void initAction(){
//        closeButton.addActionListener(this);
        tokenSetting.addMouseListener(this);
        selectBtn.addMouseListener(this);
        openBrowserOption.addMouseListener(this);
        closeBrowserOption.addMouseListener(this);

    }

    private void initPosition(){
        tokenTag.setBounds(80,150,150,30);
        tokenDisplay.setBounds(200,150,200,30);
    }

    private void setUpUI(){
        add(closeButton);
        add(tokenTag);
        add(tokenDisplay);
        add(tokenSetting);
        add(selectBtn);
        add(openBrowser);
        add(openBrowserOption);
        add(closeBrowserOption);
    }

    private void initValue(){
        tokenDisplay.setText(KeyManager.getInstance().getToken());
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
        if(actionEvent.getSource() == tokenSetting){
            System.out.println(".........");
            String response = JOptionPane.showInputDialog(null,
                    "请输入您的API_TOKEN",
                    "请输入您的API_TOKEN",
                    JOptionPane.QUESTION_MESSAGE);
//            System.out.println("xxxxxxxxxxx"+response);
            if(response == null || response.isEmpty()){
//               System.out.println("nnnnnnnnnnnnnnnnn");
            }else{
                tokenDisplay.setText(response);
                KeyManager.getInstance().setToken(response);
            }
        }

        if(actionEvent.getSource() == selectBtn){
            selectApk();
        }


    }

    public void selectApk(){
        String path = FileOperate.getInstance().fileChoose();
        if( path != null && path.endsWith(".apk")){
            initPath(path);
//            System.out.println(SearchFile.getInstance().queryIcon(Binary.getInstance().icon));
        }
    }

    public void initPath(String path){
        Binary.getInstance().initPath(path);
        SearchFile.getInstance().initPath(path);
        AppInfoUI.getInstance().initBinary(Binary.getInstance());
        KeyManager.getInstance().setApkPath(path);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }
        if(mouseEvent.getSource() == tokenSetting){
//            System.out.println(".........");
            String response = JOptionPane.showInputDialog(null,
                    "请输入您的API_TOKEN",
                    "请输入您的API_TOKEN",
                    JOptionPane.QUESTION_MESSAGE);
//            System.out.println(response);
            if(response == null || response.isEmpty()){
                JOptionPane.showMessageDialog(null,"请填写正确的api_token");
            }else{
                tokenDisplay.setText(response);
                KeyManager.getInstance().setToken(response);
            }
        }
        if(mouseEvent.getSource() == selectBtn){
            if(KeyManager.getInstance().getToken() == null || KeyManager.getInstance().getToken().isEmpty()) {
                JOptionPane.showMessageDialog(null,"请填写api_token");
                return;
            }
            selectApk();
        }

        if(mouseEvent.getSource() == openBrowserOption){
            openBrowserOption.setVisible(false);
            closeBrowserOption.setVisible(true);
            KeyManager.getInstance().setBrowserState("close");
        }

        if(mouseEvent.getSource() == closeBrowserOption){
            openBrowserOption.setVisible(true);
            closeBrowserOption.setVisible(false);
            KeyManager.getInstance().setBrowserState("open");
        }
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
