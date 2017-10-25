package linkai20171024.test.bwie.com.shopmodel;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/10/18.
 */

public class SelectPresenter implements SelectModel.SelectLister{
    private SelectModel selectModel;
    private SelectView selectView;

    public SelectPresenter(SelectView selectView) {
        this.selectView = selectView;
        selectModel=new SelectModel();
        selectModel.setSelectListerl(this);
    }
    public void select(String uid)
    {
        selectModel.select(uid);
    }
    @Override
    public void loginSuccess(String code, String msg, List<SelectBean.DataBean> data) {
        selectView.loginSuccess(code,msg,data);
    }

    @Override
    public void loginFail(String code, String msg) {
        selectView.loginFail(code,msg);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        selectView.onFailure(call,e);
    }
}
