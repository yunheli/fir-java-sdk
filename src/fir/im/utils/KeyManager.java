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
    public final String BROWSER_STATE = "BROWSER_STATE" ;
    public final String MD5 = "MD5";
    public final String AUTO_UPLOAD = "AUTO_UPLOAD";
    public final String APP_ID = "APP_ID";
    public String apiToken;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
        XmlUtil.getInstance().setKey(APP_ID,  appId);
    }

    public String appId;

    public String getAutoUpload() {
        return autoUpload;
    }

    public void setAutoUpload(String autoUpload) {
        this.autoUpload = autoUpload;
        XmlUtil.getInstance().setKey(AUTO_UPLOAD,  autoUpload);
    }

    public String autoUpload;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
        XmlUtil.getInstance().setKey(MD5,  md5);
    }

    public String md5;

    public String getBrowserState() {
        return browserState;
    }

    public void setBrowserState(String browserState) {    //open or close
        this.browserState = browserState;
        XmlUtil.getInstance().setKey(BROWSER_STATE,  browserState);
    }

    public String browserState;
    public Boolean isEclipse = false;

    public String getApkPath() {
        return apkPath;
    }

    public void isEclipseFuc(Boolean flag){
       isEclipse = flag;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
        XmlUtil.getInstance().setKey(APK_PATH,  apkPath);
    }

    public String apkPath;
    public KeyManager(){
        apiToken = XmlUtil.getInstance().getKey(API_TOKEN);
        apkPath =    XmlUtil.getInstance().getKey(APK_PATH);
        browserState = XmlUtil.getInstance().getKey(BROWSER_STATE);
        md5 = XmlUtil.getInstance().getKey(MD5);
        autoUpload = XmlUtil.getInstance().getKey(AUTO_UPLOAD);
        appId = XmlUtil.getInstance().getKey(APP_ID);
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

