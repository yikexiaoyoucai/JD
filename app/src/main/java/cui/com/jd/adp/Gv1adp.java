package cui.com.jd.adp;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cui.com.jd.R;
import cui.com.jd.bean.Baba;
import cui.com.jd.bean.Fl;

/**
 * Created by ZMoffice on 2017.12.14.
 */

public class Gv1adp extends BaseAdapter {
    List<Fl.DataBean> list;
    Context context;

    public Gv1adp(List<Fl.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context, R.layout.itemgv2,null);
        Uri uri=Uri.parse(list.get(i).getIcon());
        SimpleDraweeView img=v.findViewById(R.id.gvimg);
        img.setImageURI(uri);
        return v;
    }
}
