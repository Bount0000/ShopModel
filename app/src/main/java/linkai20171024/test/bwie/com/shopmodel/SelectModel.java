package linkai20171024.test.bwie.com.shopmodel;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/10/18.
 */

public class SelectModel {
    private List<SelectBean.DataBean> data;
    private String msg;
    private String code;
    public void select(String uid)
    {
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().get().url("http://120.27.23.105/product/getCarts?uid="+uid).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                selectListerl.onFailure(call,e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
             if(response!=null&&response.isSuccessful())
             {
                 String str = response.body().string();
                 System.out.println("-----------"+str);
                 if(str.equals("null"))
                 {
                     return;
                 }
                 else
                 {
                     jiexi(str);
                     if("0".equals(code))
                     {
                         selectListerl.loginSuccess(code,msg,data);
                     }
                     else
                     {
                         selectListerl.loginFail(code,msg);
                     }
                 }

             }
            }

            private void jiexi(String str) {
                Gson gson=new Gson();
                SelectBean selectBean = gson.fromJson(str, SelectBean.class);
                code = selectBean.code;
                msg = selectBean.msg;
                data = selectBean.data;
            }
        });
    }
    private SelectLister selectListerl;

    public void setSelectListerl(SelectLister selectListerl) {
        this.selectListerl = selectListerl;
    }

    public interface SelectLister
    {
        void loginSuccess(String code, String msg, List<SelectBean.DataBean> data);
        void loginFail(String code, String msg);
        void onFailure(Call call, IOException e);
    }

}
