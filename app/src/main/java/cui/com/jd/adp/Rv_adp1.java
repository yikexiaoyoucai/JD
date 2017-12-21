package cui.com.jd.adp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import cui.com.jd.R;
import cui.com.jd.bean.Bean;

/**
 * Created by ZMoffice on 2017.12.13.
 */

public class Rv_adp1 extends RecyclerView.Adapter<Rv_adp1.Vh> {
    Context context;
    Bean bean;

    public Rv_adp1(Context context, Bean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context,R.layout.itemrv1,null);
        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(Vh holder, int position) {
        String img=bean.getMiaosha().getList().get(position).getImages();
        String[] imgs=img.split("\\|");
        Uri uri=Uri.parse(imgs[1]);
        holder.mImg.setImageURI(uri);
        holder.mName1.setText("￥"+bean.getMiaosha().getList().get(position).getBargainPrice());
    }

    @Override
    public int getItemCount() {
        return bean.getMiaosha().getList().size();
    }

    class Vh extends RecyclerView.ViewHolder {

        private SimpleDraweeView mImg;
        private TextView mName1;

        public Vh(View itemView) {
            super(itemView);
            mImg = (SimpleDraweeView) itemView.findViewById(R.id.img);
            mName1 = (TextView) itemView.findViewById(R.id.name1);
        }
    }
}
