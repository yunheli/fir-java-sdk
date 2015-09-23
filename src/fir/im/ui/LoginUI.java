package fir.im.ui;

import fir.im.dialog.FirDialog;
import fir.im.model.Binary;
import fir.im.swing.CloseButton;
import fir.im.utils.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
    private JLabel autoUpload;

    private JLabel openAutoUploadOption;
    private JLabel closeAutoUploadOption;
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

        autoUpload = new JLabel("自动上传开关");
        autoUpload.setForeground(new Color(175,175,175));
        autoUpload.setBounds(80,210,100,30);
        autoUpload.setToolTipText("发现文件改变是否自动上传选项");

        openAutoUploadOption = new JLabel();
        openAutoUploadOption.setIcon(openBrowserOptionImg);
        openAutoUploadOption.setBounds(200,215,50,50);
        openAutoUploadOption.setSize(openBrowserOptionImg.getIconWidth(), openBrowserOptionImg.getIconHeight());
        openAutoUploadOption.setToolTipText("当前是选中状态，发现文件改变会自动上传");
        openAutoUploadOption.setVisible(false);

        closeAutoUploadOption = new JLabel();
        closeAutoUploadOption.setIcon(closeBrowserOptionImg);
        closeAutoUploadOption.setBounds(200,215,50,50);
        closeAutoUploadOption.setSize(closeBrowserOptionImg.getIconWidth(), closeBrowserOptionImg.getIconHeight());
        closeAutoUploadOption.setToolTipText("当前是取消选中状态，发现文件改变取消自动上传");
        closeAutoUploadOption.setVisible(false);

        if(KeyManager.getInstance().getBrowserState() == null || KeyManager.getInstance().getBrowserState().isEmpty()) {
            KeyManager.getInstance().setBrowserState("open");
            closeBrowserOption.setVisible(false);
        }else if(KeyManager.getInstance().getBrowserState().equals("open")){
            closeBrowserOption.setVisible(false);
        }else if(KeyManager.getInstance().getBrowserState().equals("close")){
            openBrowserOption.setVisible(false);
        }


        if(KeyManager.getInstance().getAutoUpload() == null || KeyManager.getInstance().getAutoUpload().isEmpty()) {
            KeyManager.getInstance().setAutoUpload("close");
            closeAutoUploadOption.setVisible(true);
        }else if(KeyManager.getInstance().getAutoUpload().equals("close")){
            closeAutoUploadOption.setVisible(true);
        }else if(KeyManager.getInstance().getAutoUpload().equals("open")){
            openAutoUploadOption.setVisible(true);
        }
    }

    private void initAction(){
//        closeButton.addActionListener(this);
        tokenSetting.addMouseListener(this);
        selectBtn.addMouseListener(this);
        openBrowserOption.addMouseListener(this);
        closeBrowserOption.addMouseListener(this);
        closeAutoUploadOption.addMouseListener(this);
        openAutoUploadOption.addMouseListener(this);
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
        add(openAutoUploadOption);
        add(closeAutoUploadOption);
        add(autoUpload);
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
                if(validate_token(response)){
                    tokenDisplay.setText(response);
                    KeyManager.getInstance().setToken(response);
                }else{
                    JOptionPane.showMessageDialog(null,"请填写正确的api_token");
                }
            }
        }

        if(actionEvent.getSource() == selectBtn){
            selectApk();
        }


    }

    public Boolean validate_token(String token){
        String url = "http://api.fir.im/apps?api_token="+token;

        HttpGet request = new HttpGet(url);

        try {

            HttpResponse response = new DefaultHttpClient().execute(request);

            if(response.getStatusLine().getStatusCode()==200){
                  return true;
            }

        }catch (Exception e){
             return false;
        }
        return false;
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
        KeyManager.getInstance().setMd5(OsUtil.getMd5(Binary.getInstance().filePath));
        KeyManager.getInstance().setAppId(Binary.getInstance().bundleId);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }
        if(mouseEvent.getSource() == tokenSetting){
            String response = JOptionPane.showInputDialog(null,
                    "请输入您的API_TOKEN",
                    "请输入您的API_TOKEN",
                    JOptionPane.QUESTION_MESSAGE);
            if(response == null){
            }else{
                if(validate_token(response)){
                    tokenDisplay.setText(response);
                    KeyManager.getInstance().setToken(response);
                }else{
                    JOptionPane.showMessageDialog(null,"请填写正确的api_token");
                }
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

        if(mouseEvent.getSource() == openAutoUploadOption){
            openAutoUploadOption.setVisible(false);
            closeAutoUploadOption.setVisible(true);
            KeyManager.getInstance().setAutoUpload("close");
        }

        if(mouseEvent.getSource() == closeAutoUploadOption){
            openAutoUploadOption.setVisible(true);
            closeAutoUploadOption.setVisible(false);
            KeyManager.getInstance().setAutoUpload("open");
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
