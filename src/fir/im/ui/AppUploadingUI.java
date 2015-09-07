package fir.im.ui;

import fir.im.dialog.FirDialog;
import fir.im.model.Binary;
import fir.im.service.UploadService;
import fir.im.swing.CloseButton;
import fir.im.swing.QrcodePanel;
import fir.im.utils.KeyManager;
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
public class AppUploadingUI extends JPanel implements ActionListener, UploadService.UploadServiceDelegate{
    private static AppUploadingUI appUploadingUI;
    JProgressBar progressBar;
    CloseButton closeButton;
    QrcodePanel qrcodePanel;
    JLabel shortLinkLabel;
    public AppUploadingUI(){
        setLayout(null);
        this.setSize(500, 500);
        progressBar = new JProgressBar();
        progressBar.setBounds(40, 385, 422, 20);
        progressBar.setStringPainted(true);
        qrcodePanel = new QrcodePanel();
        qrcodePanel.setBounds(200,100,100,100);
        this.add(qrcodePanel);

        shortLinkLabel = new JLabel();
        shortLinkLabel.setBounds(200,230,200,30);
        this.add(shortLinkLabel);

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

    public void setShortDisplay(String t){
        shortLinkLabel.setText(t);
        qrcodePanel.setLink(t);
    }

    public void upload(String changeLogTextArea){
        FirDialog.getInstance().setContentPane(this);

        new UploadService().sendBuild(null, Binary.getInstance().filePath, KeyManager.getInstance().getToken(),
                Binary.getInstance(),
                changeLogTextArea,
                this);
        progressBar.setVisible(true);
        progressBar.setValue(0);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
        if(actionEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }
    }

    @Override
    public void onUploadFinished(boolean finishedSuccessful) {
//        FirDialog.getInstance().setContentPane(AppInfoUI.getInstance());
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

            }
        });
    }
}
