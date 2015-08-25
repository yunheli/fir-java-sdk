package fir.im.utils;

import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/25
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
public class Resource {
    public static Resource resource ;
    public  URL  getResource(String uri) {
        URL fileURL = this.getClass().getResource("/images/"+ uri);
        return fileURL;
    }
    public Resource(){
        resource = this;
    }
    public static Resource getInstance(){
       if(resource == null){
         return new Resource();
       } else{
           return  resource;
       }
    }
}

