package com.example.rdvmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private DataBase myData;

    ListView lvRDVs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myData=new DataBase(this);
        myData.open();

        lvRDVs =  (ListView) findViewById(R.id.lvRDVs);
        lvRDVs.setEmptyView(findViewById(R.id.tvEmpty));

        chargeData();

        registerForContextMenu(lvRDVs);

        lvRDVs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String idItem= ((TextView)view.findViewById(R.id._id)).getText().toString();
                String titleItem= ((TextView)view.findViewById(R.id.title)).getText().toString();
                String dateItem= ((TextView)view.findViewById(R.id.tvDate)).getText().toString();
                String timeItem= ((TextView)view.findViewById(R.id.tvTime)).getText().toString();
                String addressItem= ((TextView)view.findViewById(R.id.tvAddress)).getText().toString();
                String contactItem= ((TextView)view.findViewById(R.id.tvContact)).getText().toString();
                String phoneNumItem= ((TextView)view.findViewById(R.id.tvPhone)).getText().toString();
                RDV pRDV= new RDV(Long.parseLong(idItem),titleItem,dateItem,timeItem,addressItem,contactItem,parseInt(phoneNumItem));
                Intent intent = new Intent(getApplicationContext(), RDVEditor.class);
                intent.putExtra("SelectedRDV",pRDV);


                intent.putExtra("fromAdd",false);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.rdv_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.new_rdv:{
                Intent intent=new Intent(this, RDVEditor.class);
                intent.putExtra("fromAdd",true);
                startActivity(intent);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void chargeData(){
        final String[] from = new String[]{DataBase._ID,
                DataBase.TITLE, DataBase.DATE, DataBase.TIME, DataBase.ADDRESS, DataBase.CONTACT, DataBase.PHONEN, DataBase.STATE};
        final int[]to= new int[]{R.id._id,R.id.title,R.id.tvDate,R.id.tvTime,R.id.tvAddress,R.id.tvContact,R.id.tvPhone};

        Cursor c = myData.getAllRDVs();
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,R.layout.rdv_view,c,from,to,0);
        adapter.notifyDataSetChanged();
        lvRDVs.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if (item.getItemId()==R.id.delete){
            myData.delete(info.id);
            chargeData();
            return true;
        }
        return super.onContextItemSelected(item);
    }

}