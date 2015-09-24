package fir.im.model;


import fir.im.dialog.CustomTipDialog;
import fir.im.utils.Notice;
import net.dongliu.apk.parser.ApkParser;
import net.dongliu.apk.parser.bean.ApkMeta;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/10
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class Binary {
    public String name;
    public String icon;
    public String bundleId;
    public String aShort;
    public String versionName;
    public String versionCode;
    public String filePath;
    public static Binary binary;

    public Binary(){
        binary = this;
    }

    public Binary(String url) throws Exception{
        binary = this;
        this.filePath = url;
        parseApk(url);
    }

    public static Binary getInstance(){
        if(binary == null) return new Binary();
        return binary;
    }

    public void initPath(String url) throws Exception{
        this.filePath = url;
        if(this.filePath.isEmpty())  return;
        parseApk(this.filePath);
    }

    public void parseApk(String url) throws Exception{
        ApkParser apkParser = null;

        apkParser = new ApkParser(new File(url));
        ApkMeta apkMeta = apkParser.getApkMeta();

//            System.out.println(apkMeta.getLabel());
//            System.out.println(apkMeta.getPackageName());
//            System.out.println(apkMeta.getVersionCode());
//            System.out.println(apkMeta.getLabel());
//            System.out.println(apkMeta.getIcon().getPath());

        this.versionName = apkMeta.getVersionName()!= null ? apkMeta.getVersionName().toString(): "1.0";
        this.versionCode = apkMeta.getVersionCode()!= null ? apkMeta.getVersionCode().toString(): "1.0";
        this.bundleId = apkMeta.getPackageName();
        this.name = apkMeta.getLabel();
        this.icon = apkMeta.getIcon().getPath();
        apkParser.close();
    }
}
