package com.example.medabinfinal.walkthrough;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.medabinfinal.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }


    //three arrays

    int images[]={
            R.drawable.bell,
            R.drawable.alarm,
            R.drawable.cost,
            R.drawable.healthy,
            R.drawable.notepad,
            R.drawable.rank,
            R.drawable.record_database,
            R.drawable.hospitalinfo

    };

    int headings[]={
            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title,
            R.string.fourth_slide_title,
            R.string.fifth_slide_title,
            R.string.sixth_slide_title,
            R.string.seventh_slide_title,
            R.string.eighth_slide_title


    };

    int descriptions[]={

            R.string.first_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc,
            R.string.fourth_slide_desc,
            R.string.fifth_slide_desc,
            R.string.sixth_slide_desc,
            R.string.seventh_slide_desc,
            R.string.eighth_slide_desc
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //request the system to use some layout

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);

        //writing view before id because we have to find as each object
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_description);

        //setting these three variable by the array

        imageView.setImageResource(images[position]);
        heading.setText(headings [position]);
        desc.setText(descriptions[position]);


        container.addView(view);




        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
