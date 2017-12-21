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

public class Rv_adp2 extends RecyclerView.Adapter<Rv_adp2.Vh> {
    Context context;
    Bean bean;
    setlisten li;
    public Rv_adp2(Context context, Bean bean) {
        this.context = context;
        this.bean = bean;
    }

    public void setLi(setlisten li) {
        this.li = li;
    }

    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context,R.layout.itemrv2,null);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                li.setonclick(view, (Integer) view.getTag());
            }
        });
        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(Vh holder, int position) {
        String img=bean.getTuijian().getList().get(position).getImages();
        String[] imgs=img.split("\\|");
        Uri uri=Uri.parse(imgs[0]);
        holder.mImg.setImageURI(uri);
        holder.mName1.setText(bean.getTuijian().getList().get(position).getTitle());
        holder.mPrice1.setText("￥"+bean.getTuijian().getList().get(position).getBargainPrice());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return bean.getTuijian().getList().size();
    }

    class Vh extends RecyclerView.ViewHolder {

        private SimpleDraweeView mImg;
        private TextView mName1;
        private TextView mPrice1;
        public Vh(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img2);
            mName1 = itemView.findViewById(R.id.name2);
            mPrice1 = itemView.findViewById(R.id.price2);
        }
    }
    public interface setlisten
    {
        void setonclick(View v,int i);
    }
}
