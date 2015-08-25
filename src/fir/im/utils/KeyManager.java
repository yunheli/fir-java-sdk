package fir.im.utils;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/25
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
public class KeyManager {
    public static KeyManager keyManager;
    public final String API_TOKEN = "API_TOKEN";
    public final String APK_PATH = "APK_PATH";
    public String apiToken;

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
        XmlUtil.getInstance().setKey(APK_PATH,  apkPath);
    }

    public String apkPath;
    public KeyManager(){
        keyManager = this;
    }

    public static KeyManager getInstance(){
        if(keyManager == null){
            return new KeyManager();
        }
        return keyManager;
    }

    public String getToken(){
        return  apiToken;
    }

    public void setToken(String token){
        apiToken = token;
        XmlUtil.getInstance().setKey(API_TOKEN,  token);
    }
}

