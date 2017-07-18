package ir.srahnama.a504.a504;

import android.content.Context;
import android.os.Bundle;
import android.os.WorkSource;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Words extends FragmentActivity {
    private String _Lesson;

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle extras = getIntent().getExtras();
        _Lesson= extras.getString("lesson");
        Toast.makeText(Words.this,_Lesson,Toast.LENGTH_SHORT).show();
        mPager = (ViewPager) findViewById(R.id.pager);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter  {

        private Context mContext;
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            Toast.makeText(Words.this,"hi "+position+"",Toast.LENGTH_SHORT).show();


            //TextView tv1 = (TextView) findViewById(R.id.tv1);
           // tv1.setText(position+"");

           // return new ScreenSlidePageFragment();
            ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
            fragment.setText(position+"");
            fragment.setLesson(_Lesson);
            return fragment;
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


}
