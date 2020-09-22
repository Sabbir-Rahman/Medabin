package com.example.medabinfinal.updateRecord.personalRecord.ShowRecords;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.PersonalRecordDatabase;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class personal_record_showRecords_menu extends AppCompatActivity implements OnChartGestureListener,OnChartValueSelectedListener {

    private static final String DATABASE_NAME = "Personal_Record.db";
    private static final String DATABASE_TABLE_HEIGHT = "height_table";
    private static final String DATABASE_TABLE_WEIGHT = "weight_table";
    private static final String DATABASE_TABLE_BP = "bp_table";
    private static final String DATABASE_TABLE_GLUCOSE = "glucose_table";
    private static final String DATABASE_TABLE_FOOD = "food_table";
    private static final String DATABASE_TABLE_DISEASE = "disease_table";
    private static final String DATABASE_TABLE_DAILY_TIME = "daily_time_table";

    private static  String COL_ID = "Id";
    private static final String COL_DATE = "Date_dd_mm_yyyy";
    private static final String COL_TIME = "Time";
    private static final String COl_FEET = "Feet";
    private static final String COl_INCH = "Inch";


    private static final String COL_BP_HIGH = "Bp_High";
    private static final String COL_BP_LOW = "Bp_Low";


    private static final String COL_CARBOHYDRATE = "Carbohydrate_percentage";
    private static final String COL_PROTEIN = "Protein_percentage";
    private static final String COL_FAT = "Fat_percentage";
    private static final String COL_VITAMIN = "Vitamin_percentage";
    private static final String COL_WATER = "Water_percentage";
    private static final String COL_MINERAL = "Mineral_percentage";
    private static final String COL_UNKNOWN_FOOD_TYPE = "Unknown_food_type_percentage";

    private static final String  COL_WEIGHT = "Weight_kg";

    private static final String COL_GLUCOSE_BEFORE_FAST = "Glucose_before_fast";
    private static final String COL_GLUCOSE_AFTER_FAST = "Glucose_after_fast";

    private static final String COL_FEVER = "Feel_fever";
    private static final String COL_STOMACH_PAIN = "Stomach_pain";
    private static final String COL_BODY_PAIN = "Body_pain";
    private static final String COL_HEAD_PAIN = "Head_pain";

    private static final String COL_SLEEP_TIME = "Sleep_time";
    private static final String COL_READ_TIME = "Read_time";
    private static final String COL_WORK_TIME = "Working_time";
    private static final String COL_EXERCISE_TIME = "Exercise_time";
    private static final String COL_OTHERS_TIME = "Others_time";
    private static final String COL_UNKNOWN_TIME = "Unknown_time";
    private static final String COl_HEIGHT_FEET_INCH = "Height";

    PersonalRecordDatabase db;
    SQLiteDatabase sqLiteDatabase;
    LineChart lineChartWeight,lineChartHeight;
    LineDataSet lineDataSetWeight = new LineDataSet(null,null);
    LineDataSet lineDataSetHeight = new LineDataSet(null,null);
    ArrayList<ILineDataSet> datasetWeight = new ArrayList<>();
    ArrayList<ILineDataSet> datasetHeight = new ArrayList<>();
    LineData lineDataWeight,lineDataHeight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_show_records_menu);

        db = new PersonalRecordDatabase(this);
        sqLiteDatabase = db.getReadableDatabase();


        //weight chart
        lineChartWeight = findViewById(R.id.linechartWeight);
        lineChartHeight = findViewById(R.id.linechartHeight);


        lineChartWeight.setOnChartGestureListener(this);
        lineChartWeight.setOnChartValueSelectedListener(this);

        lineChartWeight.setDragEnabled(true);
        lineChartWeight.setScaleEnabled(true);
        lineChartWeight.setPinchZoom(true);

        lineDataSetWeight.setValues(getDataValueWeight());
        lineDataSetWeight.setLabel("Weight");
        datasetWeight.clear();
        datasetWeight.add(lineDataSetWeight);
        lineDataWeight = new LineData(datasetWeight);
        lineChartWeight.clear();
        lineChartWeight.setData(lineDataWeight);
        lineChartWeight.animateXY(2000,2000, Easing.EaseInExpo,Easing.EaseInExpo);
        lineChartWeight.setViewPortOffsets(60, 80, 50, 60);

        lineChartWeight.invalidate();

        lineDataSetWeight.setLineWidth(4);
        lineDataSetWeight.setFillAlpha(80);
        lineDataSetWeight.setDrawFilled(true);
        lineDataSetWeight.setFillColor(Color.GREEN);
        lineDataSetWeight.setColor(Color.GREEN);


        List<String> weightDateList = new ArrayList<>();
           weightDateList = db.getWeightDates();
        String[] values = new String[weightDateList.size()] ;



        for (int i =0;i<weightDateList.size();i++)
        {
            values[i]= weightDateList.get(i);

        }



        XAxis xAxisWeight = lineChartWeight.getXAxis();
        xAxisWeight.setValueFormatter(new ClaimsXAxisValueFormatter(values));
        xAxisWeight.setGranularity(1f);


        //height chart

        lineChartHeight.setOnChartGestureListener(this);
        lineChartHeight.setOnChartValueSelectedListener(this);

        lineChartHeight.setDragEnabled(true);
        lineChartHeight.setScaleEnabled(true);
        lineChartHeight.setPinchZoom(true);

        lineDataSetHeight.setValues(getDataValueHeight());
        lineDataSetHeight.setLabel("Height in feet");
        datasetHeight.clear();
        datasetHeight.add(lineDataSetHeight);
        lineDataHeight = new LineData(datasetHeight);
        lineChartHeight.clear();
        lineChartHeight.setData(lineDataHeight);
        lineChartHeight.animateXY(2000,2000, Easing.EaseInExpo,Easing.EaseInExpo);
        lineChartHeight.setViewPortOffsets(60, 80, 50, 60);

        lineChartHeight.invalidate();

        lineDataSetHeight.setLineWidth(4);
        lineDataSetHeight.setFillAlpha(80);
        lineDataSetHeight.setDrawFilled(true);
        lineDataSetHeight.setFillColor(Color.BLUE);
        lineDataSetHeight.setColor(Color.BLUE);


        List<String> heightDataList = new ArrayList<>();
        heightDataList = db.getHeightDates();
        String[] valuesHeight = new String[heightDataList.size()] ;



        for (int i =0;i<heightDataList.size();i++)
        {
            valuesHeight[i]= heightDataList.get(i);

        }



        XAxis xAxisHeight = lineChartHeight.getXAxis();
        xAxisHeight.setValueFormatter(new ClaimsXAxisValueFormatterHeight(valuesHeight));
        xAxisHeight.setGranularity(1f);



    }


    private ArrayList<Entry> getDataValueWeight()
    {
        ArrayList<Entry> dataValue = new ArrayList<>();
        String[] columns = {COL_ID,COL_WEIGHT};

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_WEIGHT,columns,null,null,null,null,COL_DATE);

        for(int i = 0; i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            dataValue.add(new Entry(cursor.getFloat(0),cursor.getFloat(1)));

        }

        return dataValue;

    }


    private ArrayList<Entry> getDataValueHeight()
    {
        ArrayList<Entry> dataValue = new ArrayList<>();
        String[] columns = {COL_ID,COl_HEIGHT_FEET_INCH};

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_HEIGHT,columns,null,null,null,null,COL_DATE);

        for(int i = 0; i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            dataValue.add(new Entry(cursor.getFloat(0),cursor.getFloat(1)));

        }

        return dataValue;

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public class ClaimsXAxisValueFormatter extends ValueFormatter {


        PersonalRecordDatabase db;

        private String[] mValue;

        int count =0;

        public ClaimsXAxisValueFormatter(String[] values) {

            this.mValue = values;
        }

        int i = -1 ;

        @Override
        public String getAxisLabel(float value, AxisBase axis) {

            db = new PersonalRecordDatabase(getBaseContext());


/*
Depends on the position number on the X axis, we need to display the label, Here, this is the logic to convert the float value to integer so that I can get the value from array based on that integer and can convert it to the required value here, month and date as value. This is required for my data to show properly, you can customize according to your needs.
*/


                count++;
            System.out.println( "count check"+count+" value check weight "+value);
            return db.getWeightDates().get((int)value-1);

        }
    }

    public class ClaimsXAxisValueFormatterHeight extends ValueFormatter {


        PersonalRecordDatabase db;

        private String[] mValue;

        int count =0;

        public ClaimsXAxisValueFormatterHeight(String[] values) {

            this.mValue = values;
        }

        int i = -1 ;

        @Override
        public String getAxisLabel(float value, AxisBase axis) {

            db = new PersonalRecordDatabase(getBaseContext());


/*
Depends on the position number on the X axis, we need to display the label, Here, this is the logic to convert the float value to integer so that I can get the value from array based on that integer and can convert it to the required value here, month and date as value. This is required for my data to show properly, you can customize according to your needs.
*/


            count++;
            System.out.println( "count check"+count+" value check "+value);
            return mValue[(int)value-1];

        }
    }


}