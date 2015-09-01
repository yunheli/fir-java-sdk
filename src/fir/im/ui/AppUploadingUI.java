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
 * Time: 下午3:32
 * To change this template use File | Settings | File Templates.
 */
public class AppUploadingUI extends JPanel implements ActionListener {
    private static AppUploadingUI appUploadingUI;
    JProgressBar progressBar;
    CloseButton closeButton;
    public AppUploadingUI(){
        setLayout(null);
        this.setSize(500,500);
        progressBar = new JProgressBar();
        progressBar.setBounds(40, 385, 422, 20);
        add(progressBar);
        appUploadingUI = this;
        closeButton = new CloseButton();
        add(closeButton);
        closeButton.addActionListener(this);

    }

    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(Resource.getInstance().getResource("uploadingbg.png"));
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, icon.getIconWidth(),
                icon.getIconHeight(), icon.getImageObserver());


    }

    public static AppUploadingUI getInstance(){
        if(appUploadingUI == null) return new AppUploadingUI();
        return appUploadingUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
        if(actionEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }
    }
}
