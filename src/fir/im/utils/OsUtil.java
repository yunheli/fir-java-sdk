package fir.im.utils;

import com.sun.awt.AWTUtilities;

import java.awt.*;
import java.io.FileInputStream;
import java.security.MessageDigest;

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

    public static void openUrlInBrowser(String url){
        try {
            Desktop.getDesktop().browse(
                    new java.net.URI(url));
        } catch (Exception ex) {
        }
    }

    /**
     * 获取文件的MD5
     * @param path
     * @return
     */
    public static String getMd5(String path){
        String value = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(path);

            byte[] dataBytes = new byte[1024];

            int nRead = 0;
            while ((nRead = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nRead);
            };
            byte[] mBytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mBytes.length; i++) {
                sb.append(Integer.toString((mBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Digest(in hex format):: " + sb.toString());
            value = sb.toString();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return value;
    }
}
