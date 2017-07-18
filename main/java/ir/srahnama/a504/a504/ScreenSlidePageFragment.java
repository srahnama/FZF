package ir.srahnama.a504.a504;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * Created by Shahab Rahnama on 18/07/2017.
 */

public class ScreenSlidePageFragment extends Fragment {
    private String text;
    private Cursor cu;
    private Context ourcontext;
    public void setText(String text){

        this.text=text;
    }
    private String _Lesson;
    public void setLesson(String text){

        this._Lesson=text;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page,container, false);
        SQLController SQLC = new SQLController(ourcontext);
        SQLC.open();
        cu = SQLC.fetch();

        ((TextView)rootView.findViewById(R.id.tv1)).setText(cu.getColumnName(1));

        return rootView;
    }

}