package fir.im.dialog;

import com.sun.awt.AWTUtilities;
import fir.im.config.Constants;
import fir.im.model.Binary;
import fir.im.swing.ProgressPanel;
import fir.im.ui.AppInfoUI;
import fir.im.ui.AppUploadingUI;
import fir.im.ui.LoginUI;
import fir.im.utils.FileOperate;
import fir.im.utils.KeyManager;
import fir.im.utils.OsUtil;
import fir.im.utils.SearchFile;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

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


        if(KeyManager.getInstance().getApkPath() != null && !KeyManager.getInstance().getApkPath().isEmpty() && FileOperate.getInstance().isExist(KeyManager.getInstance().getApkPath())){
            String path =  KeyManager.getInstance().getApkPath();
            Binary.getInstance().initPath(path);
            SearchFile.getInstance().initPath(path);
            AppInfoUI.getInstance().initBinary(Binary.getInstance());
            KeyManager.getInstance().setApkPath(path);
        }else{
            this.setContentPane(new LoginUI());
        }


        if(OsUtil.isTransparency())
        {
            AWTUtilities.setWindowOpaque(this, false);
        }



    }

    public void setIdeEnvironmentEclipse(Boolean flag){
        KeyManager.getInstance().isEclipseFuc(true);
    }

    public  static FirDialog getInstance(){
        if (firDialog == null){
            return new FirDialog();
        }
        return firDialog;
    }


}
