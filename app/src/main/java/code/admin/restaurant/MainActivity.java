package code.admin.restaurant;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    class RestaurantAdapter extends ArrayAdapter<Restaurant>{
        public RestaurantAdapter(Context context, int textViewResourceId){
            super(context, textViewResourceId);
        }
        public RestaurantAdapter(){
            super(MainActivity.this, android.R.layout.simple_list_item_1, restaurantList);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            if(row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, null);
            }
            Restaurant r = restaurantList.get(position);
            ((TextView)row.findViewById(R.id.title)).setText(r.getName());
            ((TextView)row.findViewById(R.id.address)).setText(r.getAddress());
            ImageView icon =(ImageView) row.findViewById(R.id.icon);
            String type = r.getType();
            if(type.equals("Take out"))
                icon.setImageResource(R.drawable.lettertsignshape);
            else if(type.equals("Sit down"))
                icon.setImageResource(R.drawable.giftbox);
            else
                icon.setImageResource(R.drawable.crown);
            return  row;
        }
    }
     List<Restaurant> restaurantList = new ArrayList<Restaurant>();
     RestaurantAdapter adapter = null;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(onSave);

        ListView list = (ListView) findViewById(R.id.restaurants);

        adapter = new RestaurantAdapter();
        list.setAdapter(adapter );
    }

    private class Restaurant {
        private String name = "";
        private String address = "";
        private String type = "";


        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return (name);
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return (address);
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return (type);
        }
        public String toString(){
            return (getName() + " - " + getAddress());
        }

    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r =new Restaurant();
            EditText name = (EditText) findViewById(R.id.name);
            EditText address = (EditText) findViewById(R.id.addr);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            Toast.makeText(MainActivity.this,
                    r.getName() + " "+ r.getAddress() + " " + r.getType(), Toast.LENGTH_SHORT).show();
            RadioGroup type = (RadioGroup) findViewById(R.id.type);
            switch (type.getCheckedRadioButtonId()) {
                case R.id.take_out:
                    r.setType("Take out");
                    break;
                case R.id.sit_down:
                    r.setType("Sit down");
                    break;
                case R.id.delivery:
                    r.setType("delivery");
                    break;
            }
            restaurantList.add(r);
            adapter.notifyDataSetChanged();
        }

    };


}


