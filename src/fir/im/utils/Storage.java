package fir.im.utils;

import fir.im.config.Constants;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/25
 * Time: 下午11:56
 * To change this template use File | Settings | File Templates.
 */
public class Storage {

    public static File getAppDataFolder()
    {
        String os = System.getProperty("os.name").toUpperCase();
        String dataFile = (os.contains("MAC")) ? "Library/Caches/fir.im" : ".firim";

        return new File(System.getProperty("user.home"), dataFile);
    }

    public static File getTemIconFolder(){
        String os = System.getProperty("os.name").toUpperCase();
        String dataFile = (os.contains("MAC")) ? "Library/Caches/fir.im/temp" : ".firim/temp";
        FileOperate.getInstance().newFolder(dataFile);
        return new File(System.getProperty("user.home"), dataFile);
    }

    public static String getXmlPath(){
        String folderPath =  getAppDataFolder().getPath();
        FileOperate.getInstance().newFolder(folderPath);
        String path = new StringBuilder().append(folderPath).append("/").append(Constants.XML_PATH).toString();
//        System.out.println("file path#"+path);
        if(FileOperate.getInstance().isExist(path)){
            return path;
        }

        String content = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>\n" +
                "<RESULT>\n" +
                "    <VALUE>　　\n" +
                "        <KEY>NAME</KEY>　　\n" +
                "        <VAL>AA</VAL>\n" +
                "    </VALUE>\n" +
                "    <VALUE>　　\n" +
                "        <KEY>PATH</KEY> 　\n" +
                "        <VAL>AAAA</VAL>\n" +
                "    </VALUE>\n" +
                "</RESULT>" ;
        FileOperate.getInstance().newFile(path,content);
        return  path;
    }
}
