package linkai20171024.test.bwie.com.shopmodel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mycartutils.bean.ChildBean;
import com.bwie.mycartutils.bean.GroupBean;
import com.bwie.mycartutils.utils.CartUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements SelectView{
    private ExpandableListView car_rv;
    private String uid;
    private CheckBox cb_select;
    private TextView pay;
    private TextView sumPrice;
    private List<GroupBean> gList;
    private List<List<ChildBean>> cList;

    /*  private List<List<ChildBean>> cList;
      private List<GroupBean> groupList;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
    }

    private void initDate() {
        SelectPresenter presnetr=new SelectPresenter(this);
        presnetr.select("114");
        gList = new ArrayList<>();
        cList = new ArrayList<>();
    }
    private void initView()
    {
        car_rv = (ExpandableListView) findViewById(R.id.car_rv);
        cb_select = (CheckBox) findViewById(R.id.cb_select);
        pay = (TextView) findViewById(R.id.pay);
        sumPrice = (TextView) findViewById(R.id.sumprice);
    }

    @Override
    public void loginSuccess(String code, String msg, final List<SelectBean.DataBean> data) {
     runOnUiThread(new Runnable() {


         private List<ChildBean> clist;

         @Override
         public void run() {
             for (int i = 0; i <data.size() ; i++) {
                 SelectBean.DataBean dataBean = data.get(i);
                 String sellerName = dataBean.sellerName;
                 gList.add(new GroupBean(sellerName,false));
                 List<SelectBean.DataBean.ListBean> list = dataBean.list;
                 List<ChildBean> clist = new ArrayList<ChildBean>();
                 for (int j = 0; j <list.size() ; j++) {
                     SelectBean.DataBean.ListBean listBean = list.get(j);
                     String title = listBean.title;
                     Toast.makeText(MainActivity.this, "===="+title, Toast.LENGTH_SHORT).show();
                     int num = listBean.num;
                     double price = listBean.price;
                     String images = listBean.images;
                     String[] split = images.split("\\|");
                     clist.add(new ChildBean(title,price,split[0],false,num));
                 }
                 cList.add(clist);
             }
             CartUtils.setCartData(MainActivity.this, gList,cList,car_rv,cb_select,pay,sumPrice);
         }
     });
    }

    @Override
    public void loginFail(String code, String msg) {

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }
}
