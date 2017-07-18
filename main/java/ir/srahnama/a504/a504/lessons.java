package ir.srahnama.a504.a504;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class lessons extends ListActivity   {


    private String [] _lessons ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);



       refresh();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub

        super.onListItemClick(l, v, position, id);
        //Toast.makeText(lessons.this,position+"",Toast.LENGTH_SHORT).show();
        //Log.d("position",position+"");
        Intent i=new Intent(lessons.this,Words.class);

        //i.putExtra("table",table);
        i.putExtra("lesson", position+"");

        startActivity(i);

    }

    public class list extends ArrayAdapter<String> {
        public list(){
            super(lessons.this,R.layout.list_lessons,_lessons);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View row = convertView;
            if(row== null){
                LayoutInflater in = getLayoutInflater();
                row=in.inflate(R.layout.list_lessons,parent,false);
            }
            final TextView tv = (TextView)row.findViewById(R.id .textView2);
            tv.setText(_lessons[position]);



            return (row);
        }


        @Override
        public boolean isEnabled(int position) {
            return true;
        }

    }

    private void refresh(){
        int s = 42;
        _lessons=new String[s];
        for(int i=0;i<s;i++){
            _lessons[i]="Lesson " +(i+1) ;
        }
        setListAdapter(new list());
    }

}
