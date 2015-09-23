import com.sun.javaws.progress.Progress;
import fir.im.dialog.FirDialog;
import fir.im.dialog.TipDialog;
import fir.im.dialog.WarningDialog;
import fir.im.swing.ProgressPanel;
import fir.im.utils.Provider;
import fir.im.utils.XmlUtil;

import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/25
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        FirDialog firDialog = new FirDialog();
        firDialog.show();
        firDialog.setIdeEnvironmentEclipse(true);

        Provider.getInstance().setProviderIde("eclipse");
        //! Sundae x = new Sundae();
        System.out.print("main hello world");
//        XmlUtil.getInstance().setKey("TTT","哈哈");
//        System.out.print("======"+XmlUtil.getInstance().getKey("TTT"));

    }
}
