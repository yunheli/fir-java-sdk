package fir.im.dialog;

import com.sun.awt.AWTUtilities;
import fir.im.config.Constants;
import fir.im.ui.AppInfoUI;
import fir.im.ui.AppUploadingUI;
import fir.im.ui.LoginUI;
import fir.im.utils.OsUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/25
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
public class FirDialog extends JDialog {

    private static FirDialog firDialog;
    public FirDialog() {
        super();


        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        this.setSize(Constants.DIALOG_WIDTH, Constants.getDIALOG_HEIGHT);
        this.setLocation((int) (d.getWidth() - this.getWidth()) / 2, (int) (d.getHeight() - this.getHeight()) / 2);
        this.setUndecorated(true);

        firDialog = this;
//        if(KeyManager.getInstance().getToken().isEmpty()){
//            this.setContentPane(new SettingUI());
//        }else{
//            this.setContentPane(new UploadUI());
//        }
        this.setContentPane(new LoginUI());

        if(OsUtil.isTransparency())
        {
            AWTUtilities.setWindowOpaque(this, false);
        }


    }

    public  static FirDialog getInstance(){
        if (firDialog == null){
            return new FirDialog();
        }
        return firDialog;
    }


}
