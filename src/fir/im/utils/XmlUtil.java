package fir.im.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/25
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
public class XmlUtil {
    private DocumentBuilderFactory factory = null;
    private DocumentBuilder builder = null;
    public static XmlUtil xmlUtil;
    private Document document;
    private File xmlFile =  new File(Storage.getXmlPath()) ;

    public void readXml(){
        try {
            NodeList nl = document.getElementsByTagName("VALUE");
            for (int i = 0; i < nl.getLength(); i++) {
                System.out.print("KEY:"+ document.getElementsByTagName("KEY").item(i).getFirstChild().getNodeValue());
                System.out.println("VAL:"+ document.getElementsByTagName("VAL").item(i).getFirstChild().getNodeValue());
            }

        } catch (Exception e) {
            System.out.println("exception:" + e.getMessage());
        }
    }

    public String getKey(String key){
        String val = "";
        try {
            NodeList nl = document.getElementsByTagName("VALUE");
            for (int i = 0; i < nl.getLength(); i++) {
                String xmlKey = document.getElementsByTagName("KEY").item(i).getFirstChild().getNodeValue();
                String xmlVal = document.getElementsByTagName("VAL").item(i).getFirstChild().getNodeValue();
                if(xmlKey.equals(key)){
                    val = xmlVal;
                }
            }

        } catch (Exception e) {
            System.out.println("exception:" + e.getMessage());
        }
        return val;
    }

    public void setKey(String key, String val){
//        System.out.println("key:"+key+"#val:"+val);
        if(val == null) return;
        try {
            Boolean isFoundNode = false;
            NodeList nl = document.getElementsByTagName("VALUE");
            for (int i = 0; i < nl.getLength(); i++) {
                Node nodeKey =  document.getElementsByTagName("KEY").item(i).getFirstChild();
                Node nodeVal =  document.getElementsByTagName("VAL").item(i).getFirstChild();
                if(nodeKey.getNodeValue().equals(key)){
                    nodeVal.setNodeValue(val);
                    isFoundNode = true;
                }
            }
//            System.out.println("0");
            if(!isFoundNode){
                Element valE= document.createElement("VALUE");
                Element keyE= document.createElement("KEY");
                Element vE = document.createElement("VAL");
                keyE.setNodeValue(key);
                keyE.setTextContent(key);
                vE.setNodeValue(val);
                vE.setTextContent(val);
                valE.appendChild(keyE);
                valE.appendChild(vE);
                nl.item(0).getParentNode().appendChild(valE);
            }
            writeXml(document,Storage.getXmlPath()) ;
        } catch (Exception e) {
            System.out.println("exception:" + e.getMessage());
        }
    }

    public void writeXml(Document document,String filename){
        try {
            //Document document = builder.parse(new File("E:\\testFiles\\test.xml"));
            document.normalize();

            /** 将document中的内容写入文件中 */
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //编码
            DOMSource source = new DOMSource(document);
            PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public XmlUtil(){
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile);
        }catch (Exception e){
             System.out.println("read xml error");
        }
        xmlUtil = this;
    }
    public static XmlUtil getInstance(){
        if(xmlUtil == null){
             return new XmlUtil();
        } else{
            return xmlUtil;
        }
    }


}
