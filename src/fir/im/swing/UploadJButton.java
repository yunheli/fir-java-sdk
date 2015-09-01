package fir.im.swing;

import fir.im.utils.Resource;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/1
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */
public class UploadJButton extends JButton{
    public UploadJButton(){
        ImageIcon imBack = new ImageIcon(Resource.getInstance().getResource("tuploadbtn.png"));
        this.setIcon(imBack);
        this.setBorderPainted(false);
        setBounds(13, 441, 117, 29);
        this.setSize(imBack.getIconWidth(),imBack.getIconHeight());
        this.setToolTipText("开始上传");
    }
}
