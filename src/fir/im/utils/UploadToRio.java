package fir.im.utils;

import fir.im.config.Constants;
import fir.im.dialog.CustomTipDialog;
import fir.im.model.UploadTicket;
import fir.im.ui.AppInfoUI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/8/7
 * Time: 下午1:48
 * To change this template use File | Settings | File Templates.
 */
public class UploadToRio {
    public static final String FIR_BASE_URL = Constants.FIR_ADMIN;


    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public  String versionId;
    public String token;
    public String appName;
    public String appId;
    public String versionName;
    public String versionCode;
    public String changeLog;
    public String appShort;
    public String appOid;
    public UploadTicket uploadTicket;

    public UploadToRio(String appId,String token,String appName,String versionName,String versionCode,String changeLog) throws Exception{
        this.appId = appId;
        this.appName = appName;
        this.versionCode = versionCode;
        this.changeLog = changeLog;
        this.versionName = versionName;
        this.token = token;

        uploadTicket = new UploadTicket(getUploadToken()) ;

    }

    /**
     * 获取上传token
     * @return   JSONObject
     */
    public JSONObject getUploadToken() throws Exception{
            HttpClient httpClient = new DefaultHttpClient() ;
            String url = FIR_BASE_URL + "/apps" ;
            String type = "android";
            HttpPost post = new HttpPost(url);
            List<NameValuePair> parameters ;

            ArrayList<BasicNameValuePair> postParameters = new ArrayList<BasicNameValuePair>();
            postParameters.add(new BasicNameValuePair("type", type));
            postParameters.add(new BasicNameValuePair("bundle_id", this.appId));
            postParameters.add(new BasicNameValuePair("api_token",this.token));
            post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
            HttpResponse response = null;
            response = httpClient.execute(post) ;
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            if(response.getStatusLine().getStatusCode() == 401){
                CustomTipDialog.warnTip("token无效,请重设token ::>_<::");
                AppInfoUI.getInstance().tipFlag = true;
            }
            if(responseString.indexOf("Locked forbidden")>=0){
                CustomTipDialog.warnTip("您的帐号被锁定::>_<::，请联系dev@fir.im");
                AppInfoUI.getInstance().tipFlag = true;
            }

            JSONObject jo;
            jo = new JSONObject(responseString);
            return jo;

    }

    /**
     * 上传Binary
     * @return
     */
    public JSONObject uploadBinary(){

        return new JSONObject();
    }

    /**
     * 上传icon
     * @return
     */
    public JSONObject uploadIcon(){
        return new JSONObject() ;
    }

}
