package fir.im.dialog;

import fir.im.config.Constants;
import fir.im.ui.UploadUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/25
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
public class FirDialog extends JDialog{
    public FirDialog() {
        super();
        this.setContentPane(new UploadUI());
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        this.setSize(Constants.DIALOG_WIDTH, Constants.getDIALOG_HEIGHT);
        this.setLocation((int) (d.getWidth() - this.getWidth()) / 2, (int) (d.getHeight() - this.getHeight()) / 2);
        this.setUndecorated(true);
    }
}
