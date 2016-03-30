package demo.httpconnection.com.httpconnectionsamples;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by linhao on 16/3/26.
 */
public class JsonAdapter extends BaseAdapter {
    private List<Person> list;
    private Context mContext;

    private LayoutInflater mInflate;

    private Handler handler = new Handler();

    public JsonAdapter(List<Person> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflate = LayoutInflater.from(mContext);
    }
    public JsonAdapter(Context context){

        this.mContext = context;
        mInflate = LayoutInflater.from(mContext);
    }
    public void setData(List<Person> data){
        this.list = data;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null){
            convertView = mInflate.inflate(R.layout.item,null );
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        Person person = list.get(position);
        holder.name.setText(person.getName());
        holder.age.setText(""+person.getAge());

        List<SchoolInfo> school = person.getSchoolInfo();
        SchoolInfo schoolInfo1 = school.get(0);
        SchoolInfo schoolInfo2 = school.get(1);

        holder.school1.setText(schoolInfo1.getSchool_name());
        holder.school1.setText(schoolInfo2.getSchool_name());

        new HttpImage(person.getUrl(),handler,holder.imageView).start();

        return convertView;
    }

    class Holder {
        TextView name;
        TextView age;
        TextView school1;
        TextView school2;
        ImageView imageView;

        public Holder(View v) {
            name = (TextView) v.findViewById(R.id.name);
            age = (TextView) v.findViewById(R.id.age);
            school1 = (TextView) v.findViewById(R.id.school1);
            school2 = (TextView) v.findViewById(R.id.school2);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }
}
