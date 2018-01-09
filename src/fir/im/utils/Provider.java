package fir.im.utils;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/15
 * Time: 下午6:48
 * To change this template use File | Settings | File Templates.
 */
public class Provider {
    public static Provider provider;

    public String getProviderIde() {
        return providerIde;
    }

    public void setProviderIde(String providerIde) {
        this.providerIde = providerIde;
    }

    public String providerIde = "";
    public Provider(){
        provider = this;
    }


    public static Provider getInstance(){
        if(provider == null) return new Provider();
        return provider;
    }
}
