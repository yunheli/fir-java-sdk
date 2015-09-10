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
    private JButton selectBtn;

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
        return loginUI;
    }
    private void initSwing(){
        closeButton = new CloseButton();
        tokenTag = new JLabel("设置TOKEN");
        tokenTag.setForeground(Color.gray);
        tokenDisplay = new JLabel();

        tokenSetting = new JLabel();
        ImageIcon tokenSet = new ImageIcon(Resource.getInstance().getResource("tokenSet.png"));
        tokenSetting.setIcon(tokenSet);
        tokenSetting.setBounds(420, 150, 100, 100);
        tokenSetting.setSize(tokenSet.getIconWidth(), tokenSet.getIconHeight());

        selectBtn = new JButton();
        ImageIcon select = new ImageIcon(Resource.getInstance().getResource("select.png"));
        selectBtn.setIcon(select);
        selectBtn.setBorderPainted(false);
        selectBtn.setBounds(200, 300, 100, 100);
        selectBtn.setSize(select.getIconWidth(), select.getIconHeight());
    }

    private void initAction(){
//        closeButton.addActionListener(this);
        closeButton.addMouseListener(this);
        tokenSetting.addMouseListener(this);
        selectBtn.addActionListener(this);
    }

    private void initPosition(){
        tokenTag.setBounds(80,150,70,30);
        tokenDisplay.setBounds(200,150,200,30);
    }

    private void setUpUI(){
        add(closeButton);
        add(tokenTag);
        add(tokenDisplay);
        add(tokenSetting);
        add(selectBtn);
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
            System.out.println(response);
            if(response == null){

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
            Binary.getInstance().initPath(path);
            SearchFile.getInstance().initPath(path);
            AppInfoUI.getInstance().initBinary(Binary.getInstance());
            System.out.println(SearchFile.getInstance().queryIcon(Binary.getInstance().icon));
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }
        if(mouseEvent.getSource() == tokenSetting){
            System.out.println(".........");
            String response = JOptionPane.showInputDialog(null,
                    "请输入您的API_TOKEN",
                    "请输入您的API_TOKEN",
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println(response);
            if(response == null){

            }else{
                tokenDisplay.setText(response);
                KeyManager.getInstance().setToken(response);
            }
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
