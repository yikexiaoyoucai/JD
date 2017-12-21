package cui.com.jd.adp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cui.com.jd.R;
import cui.com.jd.bean.Bean2;
import cui.com.jd.bean.Cxgwc;

/**
 * Created by ZMoffice on 2017.12.18.
 */

public class Expadp extends BaseExpandableListAdapter {
    private Context context;
    Cxgwc cxgwc;
    private String[] group;
    private String[][] child;
    private HashMap<Integer, Boolean> groupHashMap;
    private List<HashMap<Integer, Boolean>> childList;
    private List<List<Bean2>> dataList;

    public Expadp(Context context, Cxgwc cxgwc) {
        this.context = context;
        this.cxgwc = cxgwc;
        initData();
    }

    private void initData() {
        int j=cxgwc.getData().size();
        group = new String[j];
        child = new String[j][];
        groupHashMap = new HashMap<>();
        childList = new ArrayList<>();
        dataList = new ArrayList<>();

        for (int i = 0; i < j; i++) {
            group[i] =cxgwc.getData().get(i).getSellerName();
            groupHashMap.put(i, false);

             int o=cxgwc.getData().get(i).getList().size();
            String[] strings = new String[o];
            HashMap<Integer, Boolean> map = new HashMap<>();
            ArrayList<Bean2> been = new ArrayList<>();
            for (int y = 0; y < o; y++) {
                strings[y] = cxgwc.getData().get(i).getList().get(y).getTitle();
                map.put(y, false);
                Bean2 bean = new Bean2(cxgwc.getData().get(i).getList().get(y).getBargainPrice()+"", "1");
                Log.i("price",cxgwc.getData().get(i).getList().get(y).getBargainPrice()+"");
                been.add(bean);
            }

            child[i] = strings;
            childList.add(map);
            dataList.add(been);
        }
    }

    @Override
    public int getGroupCount() {
        return group.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.group_item, null);
            holder = new GroupViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.group_tv);
            holder.ck = (CheckBox) convertView.findViewById(R.id.group_ck);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.tv.setText(group[groupPosition]);
        holder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupHashMap.put(groupPosition, !groupHashMap.get(groupPosition));
                //设置二级列表的选中状态,根据一级列表的状态来改变
                setChildCheckAll();
                //计算选中的价格和数量
                String shopPrice = getShopPrice();
                //判断商品是否全部选中
                boolean b = selectAll();
                adapterData.Data(v, shopPrice, b);

            }

        });

        holder.ck.setChecked(groupHashMap.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.child_item, null);
            holder = new ChildViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.child_tv);
            holder.ck = (CheckBox) convertView.findViewById(R.id.child_ck);
            holder.jianshao = (TextView) convertView.findViewById(R.id.jianshao);
            holder.zengjia = (TextView) convertView.findViewById(R.id.zengjia);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        holder.tv.setText(child[groupPosition][childPosition]);
        holder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Integer, Boolean> hashMap = childList.get(groupPosition);
                hashMap.put(childPosition, !hashMap.get(childPosition));
                //判断二级列表是否全部选中
                ChildisChecked(groupPosition);
                //计算选中的价格和数量
                String shopPrice = getShopPrice();
                //判断商品是否全部选中
                boolean b = selectAll();
                adapterData.Data(v, shopPrice, b);
            }
        });
        final ChildViewHolder finalHolder = holder;
        holder.zengjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Bean2> been = dataList.get(groupPosition);
                String num = finalHolder.number.getText().toString();
                int i = Integer.parseInt(num);
                ++i;
                been.get(childPosition).setNumber(i + "");
                //计算选中的价格和数量
                String shopPrice = getShopPrice();
                //判断商品是否全部选中
                boolean b = selectAll();
                adapterData.Data(v, shopPrice, b);
                notifyDataSetChanged();
            }
        });
        holder.jianshao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Bean2> been = dataList.get(groupPosition);
                String num = finalHolder.number.getText().toString();
                int i = Integer.parseInt(num);
                if (i > 1) {
                    --i;
                }
                been.get(childPosition).setNumber(i + "");
                //计算选中的价格和数量
                String shopPrice = getShopPrice();
                //判断商品是否全部选中
                boolean b = selectAll();
                adapterData.Data(v, shopPrice, b);
                notifyDataSetChanged();
            }
        });

        holder.number.setText(dataList.get(groupPosition).get(childPosition).getNumber().toString());

        holder.ck.setChecked(childList.get(groupPosition).get(childPosition));
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        TextView tv;
        CheckBox ck;
    }

    class ChildViewHolder {
        TextView tv;
        CheckBox ck;
        TextView jianshao;
        TextView zengjia;
        TextView number;
    }

    //设置二级列表的选中状态,根据一级列表的状态来改变
    private void setChildCheckAll() {
        for (int i = 0; i < childList.size(); i++) {
            HashMap<Integer, Boolean> integerBooleanHashMap1 = childList.get(i);
            Set<Map.Entry<Integer, Boolean>> entries = integerBooleanHashMap1.entrySet();
            for (Map.Entry<Integer, Boolean> entry : entries) {
                entry.setValue(groupHashMap.get(i));
            }
        }
        notifyDataSetChanged();
    }

    //判断二级列表是否全部选中
    private void ChildisChecked(int groupPosition) {
        boolean ischecked = true;
        HashMap<Integer, Boolean> hashMap = childList.get(groupPosition);
        Set<Map.Entry<Integer, Boolean>> entries = hashMap.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (!entry.getValue()) {
                ischecked = false;
                break;
            }
        }
        groupHashMap.put(groupPosition, ischecked);
        notifyDataSetChanged();
    }

    //全选
    public void checkAllShop(boolean checked) {
        Set<Map.Entry<Integer, Boolean>> entries = groupHashMap.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(checked);
        }
        //调用让二级列表全选的方法
        setChildCheckAll();
        notifyDataSetChanged();
    }

    //计算价格
    public String getShopPrice() {
        int price = 0;
        int number = 0;
        for (int y = 0; y < childList.size(); y++) {
            HashMap<Integer, Boolean> integerBooleanHashMap1 = childList.get(y);
            Set<Map.Entry<Integer, Boolean>> entries = integerBooleanHashMap1.entrySet();
            for (Map.Entry<Integer, Boolean> entry : entries) {
                if (entry.getValue()) {
                    Bean2 bean = dataList.get(y).get(entry.getKey());
                    //   price += Integer.parseInt(bean.getPrice())
                    number += Integer.parseInt(bean.getNumber());
                }
            }
        }
        return price + "," + number;
    }

    //编辑一级和二级列表,如果全部选中,全选按钮也选中
    public boolean selectAll() {
        boolean isChecked = true;
        for (int y = 0; y < childList.size(); y++) {
            HashMap<Integer, Boolean> hashMap = childList.get(y);
            Set<Map.Entry<Integer, Boolean>> entries = hashMap.entrySet();
            for (Map.Entry<Integer, Boolean> entry : entries) {
                if (!entry.getValue()) {
                    isChecked = false;
                    break;
                }
            }
        }
        return isChecked;
    }

    private AdapterData adapterData;

    public interface AdapterData {
        void Data(View v, String str, boolean b);

    }

    public void getAdapterData(AdapterData adapterData) {
        this.adapterData = adapterData;
    }
}
