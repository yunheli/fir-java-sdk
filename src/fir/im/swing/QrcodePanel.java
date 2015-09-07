package fir.im.swing;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import fir.im.utils.Resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/7
 * Time: 下午9:56
 * To change this template use File | Settings | File Templates.
 */
public class QrcodePanel extends JPanel{

    ImageIcon icon = new ImageIcon("http://qr.liantu.com/api.php?m=20&fg=475a62&el=L&text=www.fir.im&w=200");

    String link = "";
    public QrcodePanel(){
         this.setSize(100,100);
    }

    protected void paintComponent(Graphics g) {
        if (link.isEmpty()) return;
        Image image = null;
        try {
            URL url = new URL("http://qr.liantu.com/api.php?m=0&el=L&text="+link+"&w=100&bg=F9CD0A");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(image, 0, 0,getSize().width,getSize().height, this);
//        setSize(icon.getIconWidth(), icon.());

    }

    public void setLink(String link){
        this.link = link;
        repaint();
    }
}
