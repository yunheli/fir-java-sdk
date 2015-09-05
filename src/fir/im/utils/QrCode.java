package fir.im.utils;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/4
 * Time: 下午10:54
 * To change this template use File | Settings | File Templates.
 */
public class QrCode {
    private static QrCode qrcode;
    public QrCode(){
        qrcode = this;
    }
    public static QrCode getInstance(){
        if(qrcode == null) return new QrCode();
        return qrcode;
    }

    public String generateUrl(String url){
        return "http://qr.liantu.com/api.php?m=20&fg=475a62&el=L&text="+url+"&w=200";
    }
}
