package com.example.guru_semiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemoWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_write);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        Button btnWrite = (Button)findViewById(R.id.btnWrite);
        Button btnPhoto = (Button)findViewById(R.id.btnPhoto);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), 2));
        viewPager.setCurrentItem(0);

        View.OnClickListener movePageListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int tag = (int)view.getTag();
                viewPager.setCurrentItem(tag);
            }
        };

       btnWrite.setOnClickListener(movePageListener);
        btnWrite.setTag(0);
        btnPhoto.setOnClickListener(movePageListener);
        btnPhoto.setTag(1);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        int tabSize;

        public MyPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count;
        }
        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new WriteFragment();
                case 1:
                    return new PhotoFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return this.tabSize;
        }
    }
}