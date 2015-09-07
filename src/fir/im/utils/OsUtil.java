package fir.im.utils;

import com.sun.awt.AWTUtilities;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/26
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class OsUtil {
    public static final String OS = System.getProperty("os.name");
    private static boolean supportsTransparency(String osName) {
        return ((osName != null) && (!(osName.contains("nix")))
                && (!(osName.contains("nux"))) && (!(osName.contains("aix"))));
    }

    public static Boolean isTransparency(){
        Boolean _transparency = false;
        try {
            Class.forName("com.sun.awt.AWTUtilities");

            if ((supportsTransparency(OS))
                    && (AWTUtilities
                    .isTranslucencySupported(AWTUtilities.Translucency.PERPIXEL_TRANSLUCENT))) {
                _transparency = true;
            } else
                throw new UnsupportedOperationException(
                        "Translucency Unsupported");
        } catch (Exception e) {
            _transparency = false;
        }
        return _transparency;
    }

    public static Dimension getScreenSize(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        return d;
    }
}
