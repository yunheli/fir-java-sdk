package fir.im.ui;
import fir.im.dialog.FirDialog;
import fir.im.model.Binary;
import fir.im.service.UploadService;
import fir.im.swing.LinkLabel;
import fir.im.utils.KeyManager;
import fir.im.utils.Resource;
import fir.im.utils.XmlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadUI extends JPanel implements ActionListener,  UploadService.UploadServiceDelegate{
    private JButton selectBtn;
    private JButton settingBtn;
    private JButton closeBtn;
    private static UploadUI uploadUI;
    private JTextArea descTextField;
    private JLabel descTag;
    private JButton uploadBtn;
    private JLabel pathTag;
    private JLabel pathLabel;
    private String apkPath;
    private Binary binary;
    private JLabel shortTag;
    private LinkLabel shortLabel;
    private JProgressBar progressBar;
    private JButton editBtn ;

    public UploadUI() {
        setLayout(null);
        this.setSize(322, 518);
        initUI();
        initAction();
        uploadUI = this;
        binary = Binary.getInstance();
    }

    public static UploadUI getInstance(){
        if(uploadUI == null){
            return new UploadUI();
        }
        return uploadUI;
    }
    public void initAction(){
        closeBtn.addActionListener(this);
        selectBtn.addActionListener(this);
        settingBtn.addActionListener(this);
        uploadBtn.addActionListener(this);
        editBtn.addActionListener(this);
    }
    public void initUI(){
        JLabel lblNewLabel = new JLabel("fir.im upload");
        lblNewLabel.setForeground(UIManager.getColor("MenuBar.selectionForeground"));
        lblNewLabel.setBounds(113, 20, 125, 16);
        add(lblNewLabel);

        selectBtn = new JButton();
        selectBtn.setBounds(130, 126, 117, 29);
        ImageIcon uploadImg = new ImageIcon(Resource.getInstance().getResource("upload.png"));
        selectBtn.setIcon(uploadImg);
        selectBtn.setSize(uploadImg.getIconWidth(), uploadImg.getIconHeight());
        selectBtn.setBorderPainted(false);
        selectBtn.setToolTipText("选择文件上传");
        add(selectBtn);

        settingBtn = new JButton();
        ImageIcon settingImg = new ImageIcon(Resource.getInstance().getResource("setting.png"));
        settingBtn.setBounds(10, 6, 117, 29);
        settingBtn.setIcon(settingImg);
        settingBtn.setSize(settingImg.getIconWidth(),settingImg.getIconHeight());
        settingBtn.setBorderPainted(false);
        settingBtn.setToolTipText("设置apiToken");

        add(settingBtn);

        closeBtn = new JButton("");
        ImageIcon closeImg = new ImageIcon(Resource.getInstance().getResource("close.png"));
        closeBtn.setIcon(closeImg);
        closeBtn.setBounds(280, 6, 117, 29);
        closeBtn.setSize(closeImg.getIconWidth(),closeImg.getIconHeight());
        closeBtn.setBorderPainted(false);
        add(closeBtn);


        descTextField = new JTextArea();
        descTextField.setBounds(92, 138, 210, 142);
        descTextField.setVisible(false);
        add(descTextField);
        descTextField.setColumns(10);

        descTag = new JLabel("更新日志:");
        descTag.setForeground(Color.WHITE);
        descTag.setBounds(10, 139, 61, 16);
        descTag.setVisible(false);
        descTag.setToolTipText("填写更新日志");
        add(descTag);

        uploadBtn = new JButton("上传");
        uploadBtn.setBounds(92, 370, 210, 29);
        uploadBtn.setVisible(false);
        add(uploadBtn);

        pathTag = new JLabel("路径:");
        pathTag.setForeground(Color.WHITE);
        pathTag.setBounds(10, 72, 61, 16);
        pathTag.setVisible(false);
        add(pathTag);

        pathLabel = new JLabel("");
        pathLabel.setForeground(Color.WHITE);
        pathLabel.setBounds(92, 72, 150, 16);
        pathLabel.setVisible(false);
        add(pathLabel);

        shortTag = new JLabel("地址:");
        shortTag.setForeground(Color.WHITE);
        shortTag.setBounds(10, 99, 61, 16);
        shortTag.setVisible(false);
        add(shortTag);

        shortLabel = new LinkLabel();
        shortLabel.setForeground(Color.WHITE);
        shortLabel.setBounds(92, 100, 188, 16);
        shortLabel.setVisible(false);
        add(shortLabel);


        progressBar = new JProgressBar();
        progressBar.setBounds(102, 324, 189, 20);
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);
        add(progressBar);

        editBtn = new JButton("编辑");
        editBtn.setBounds(241, 67, 61, 29);
        editBtn.setVisible(false);
        add(editBtn);
    }

    public void apkPanelShow(){
        pathLabel.setText(apkPath);
        selectBtn.setVisible(false);
        descTag.setVisible(true);
        uploadBtn.setVisible(true);
        pathTag.setVisible(true);
        pathLabel.setVisible(true);
        descTextField.setVisible(true);
        shortTag.setVisible(true);
        shortLabel.setVisible(true);
        editBtn.setVisible(true);
    }

    public void apkPanelHide(){
        selectBtn.setVisible(true);
        descTag.setVisible(false);
        uploadBtn.setVisible(false);
        pathTag.setVisible(false);
        pathLabel.setVisible(false);
        descTextField.setVisible(false);
        shortLabel.setVisible(false);
        shortTag.setVisible(false);
        editBtn.setVisible(false);
    }

    public void setShort(String s){
        shortLabel.setUrl(s);
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
        if(e.getSource() == this.settingBtn){
            FirDialog.getInstance().setContentPane(SettingUI.getInstance());
        }
        if(e.getSource() == this.closeBtn){
            System.out.print(".........");
            FirDialog.getInstance().setVisible(false);
        }
        if(e.getSource() == this.selectBtn ){
            fileChoose() ;
        }

        if(e.getSource() == this.uploadBtn){
            System.out.println("开始上传");
            uploadFile();

        }

        if(e.getSource() == this.editBtn){
            fileChoose();
        }
    }

    private void fileChoose(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter ff = new FileNameExtensionFilter( null, "apk");
        fileChooser.setFileFilter(ff);
        int option = fileChooser.showOpenDialog(null);
        if(option == JFileChooser.APPROVE_OPTION){
//获取基本信息
            System.out.println("路径："+fileChooser.getSelectedFile().getPath());
            System.out.println("绝对路径："+fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println("文件名称："+fileChooser.getSelectedFile().getName());
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if(path.endsWith(".apk")){
                apkPath = path;
                binary.initPath(path);
                apkPanelShow();
            }

        }
    }

    private void uploadFile(){
        new UploadService().sendBuild(null, binary.filePath, KeyManager.getInstance().getToken(),
                binary,
                descTextField.getText(),
                UploadUI.this);
        progressBar.setVisible(true);
        progressBar.setValue(0);
        uploadBtn.setText("上传中..");
        uploadBtn.setEnabled(false);

    }
    @Override
    public void onUploadFinished(boolean finishedSuccessful) {
        uploadBtn.setText("上传");
        uploadBtn.setEnabled(true);
        progressBar.setValue(0);
        progressBar.setVisible(false);
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
        });    }
}
