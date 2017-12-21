package cui.com.jd.adp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cui.com.jd.R;
import cui.com.jd.bean.Baba;
import cui.com.jd.bean.Bean;

/**
 * Created by ZMoffice on 2017.12.14.
 */

public class Rv_adp4 extends RecyclerView.Adapter<Rv_adp4.Vh> {
    List<Baba.DataBean> list;
    Context context;

    public Rv_adp4(List<Baba.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.itemrv4,null);
        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(Vh holder, int position) {
         holder.mBabaname.setText(list.get(position).getName());
        List<Baba.DataBean.ListBean> list2=new ArrayList<>();
        for(int j=0;j<list.get(position).getList().size();j++)
        {
            list2.add(list.get(position).getList().get(j));
        }
         Gv2adp adp=new Gv2adp(list2,context);
         holder.mGv2.setAdapter(adp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Vh extends RecyclerView.ViewHolder {

        private TextView mBabaname;
        private GridView mGv2;

        public Vh(View itemView) {
            super(itemView);
            mBabaname =itemView.findViewById(R.id.babaname);
            mGv2 = itemView.findViewById(R.id.gv2);
        }
    }
}
