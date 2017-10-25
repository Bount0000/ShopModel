package linkai20171024.test.bwie.com.shopmodel;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/10/18.
 */

public interface SelectView {
    void loginSuccess(String code, String msg, List<SelectBean.DataBean> data);
    void loginFail(String code, String msg);
    void onFailure(Call call, IOException e);
}
