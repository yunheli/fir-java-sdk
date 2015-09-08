package fir.im.ui;

import fir.im.dialog.FirDialog;
import fir.im.model.Binary;
import fir.im.service.UploadService;
import fir.im.swing.CloseButton;
import fir.im.swing.ProgressPanel;
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
    ProgressPanel progressPanel;
    JLabel percentLabel;
    JButton settingBtn;
    public AppUploadingUI(){
        setLayout(null);
        this.setSize(500, 500);
        progressBar = new JProgressBar();
        progressBar.setBounds(40, 385, 422, 20);
        progressBar.setStringPainted(true);
        progressBar.hide();

        qrcodePanel = new QrcodePanel();
        qrcodePanel.setBounds(200,100,100,100);
        this.add(qrcodePanel);

        shortLinkLabel = new JLabel();
        shortLinkLabel.setBounds(150,210,200,30);
        shortLinkLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(shortLinkLabel);

        add(progressBar);
        appUploadingUI = this;
        closeButton = new CloseButton();
        add(closeButton);
        closeButton.addActionListener(this);

        progressPanel = new ProgressPanel();
        add(progressPanel);
        progressPanel.setBounds(50,385,400,15);

        percentLabel = new JLabel("当前进度: 0%");
        percentLabel.setForeground(Color.white);
        add(percentLabel);
        percentLabel.setBounds(50,368,400,15);

        settingBtn = new JButton();
        ImageIcon setImg = new ImageIcon(Resource.getInstance().getResource("back.png"));
        settingBtn.setIcon(setImg);
        settingBtn.setBorderPainted(false);
        settingBtn.setBounds(30, 18, 22, 22);
        settingBtn.setSize(setImg.getIconWidth(), setImg.getIconHeight());
        add(settingBtn);
        settingBtn.addActionListener(this);

    }


    public void setPercentLabel(double x){
        int m = (int)(x * 100);
        percentLabel.setText("当前进度: "+m+"%");
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

    public void setShortDisplay(final String t){
        shortLinkLabel.setText(t);
        new Thread(new Runnable() {
            @Override
            public void run() {
                qrcodePanel.setLink(t);
            }
        }).start();

    }

    public void upload(String changeLogTextArea){
        FirDialog.getInstance().setContentPane(this);

        new UploadService().sendBuild(null, Binary.getInstance().filePath, KeyManager.getInstance().getToken(),
                Binary.getInstance(),
                changeLogTextArea,
                this);
        progressBar.setVisible(false);
        progressBar.setValue(0);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
        if(actionEvent.getSource() == closeButton){
            FirDialog.getInstance().setVisible(false);
        }

        if(actionEvent.getSource() == settingBtn){
            FirDialog.getInstance().setContentPane(AppInfoUI.getInstance());
        }
    }

    @Override
    public void onUploadFinished(boolean finishedSuccessful) {
//       FirDialog.getInstance().setContentPane(AppInfoUI.getInstance());

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
                setPercentLabel(progressBar.getPercentComplete()) ;
            }
        });
    }
}
