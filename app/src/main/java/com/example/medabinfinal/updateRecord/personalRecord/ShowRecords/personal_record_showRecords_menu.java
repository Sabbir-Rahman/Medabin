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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

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
    LineChart lineChartWeight,lineChartHeight,lineChartBp,linechartGlucose;
    LineDataSet lineDataSetWeight = new LineDataSet(null,null);
    LineDataSet lineDataSetHeight = new LineDataSet(null,null);
    LineDataSet lineDataSetBpHigh = new LineDataSet(null,null);
    LineDataSet lineDataSetBpLow = new LineDataSet(null,null);
    LineDataSet lineDataSetGlucoseBeforeFast = new LineDataSet(null,null);
    LineDataSet lineDataSetGlucoseAfterFast = new LineDataSet(null,null);
    ArrayList<ILineDataSet> datasetWeight = new ArrayList<>();
    ArrayList<ILineDataSet> datasetHeight = new ArrayList<>();
    ArrayList<ILineDataSet> datasetBp = new ArrayList<>();
    ArrayList<ILineDataSet> datasetGlucose = new ArrayList<>();
    PieChart foodChart,timechart;

    LineData lineDataWeight,lineDataHeight,lineDataBp,lineDataGlucose;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_show_records_menu);

        db = new PersonalRecordDatabase(this);
        sqLiteDatabase = db.getReadableDatabase();


        //weight chart
        lineChartWeight = findViewById(R.id.linechartWeight);
        lineChartHeight = findViewById(R.id.linechartHeight);
        lineChartBp = findViewById(R.id.linechartBp);
        linechartGlucose = findViewById(R.id.linechartGlucose);
        foodChart = findViewById(R.id.piechartFood);
        timechart = findViewById(R.id.piechartTime);


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

        createBpChart();
        createGlucoseChart();
        createFoodChart();
        createTimeChart();


    }

    public void createTimeChart()
    {


        timechart.setUsePercentValues(true);
        timechart.getDescription().setEnabled(false);
        timechart.setExtraOffsets(5,10,5,5);

        //rotate by touch
        timechart.setDragDecelerationFrictionCoef(0.95f);

        timechart.setDrawHoleEnabled(true);
        timechart.setHoleColor(Color.WHITE);

        //transparate circle radius for 3d ed=ffect
        timechart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(db.getLastDailyTimeSleepTime(),"Sleep"));
        yValues.add(new PieEntry(db.getLastDailyTimeReadTime(),"Read"));
        yValues.add(new PieEntry(db.getLastDailyTimeWorkingTime(),"Working"));
        yValues.add(new PieEntry(db.getLastDailyTimeExerciseTime(),"Exercise"));
        yValues.add(new PieEntry(db.getLastDailyTimeOthersTime(),"Others"));
        yValues.add(new PieEntry(db.getLastDailyTimeUnknownTime(),"Uknown"));



        Description description = new Description();
        description.setText("Daily time in % of "+db.getLastDailyTimeDate());
        description.setTextSize(15f);
        timechart.setDescription(description);


        timechart.animateY(2500, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues,"Daily time");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        timechart.setData(data);




    }




    public void createFoodChart()
    {


        foodChart.setUsePercentValues(true);
        foodChart.getDescription().setEnabled(false);
        foodChart.setExtraOffsets(5,10,5,5);

        //rotate by touch
        foodChart.setDragDecelerationFrictionCoef(0.95f);

        foodChart.setDrawHoleEnabled(true);
        foodChart.setHoleColor(Color.WHITE);

        //transparate circle radius for 3d ed=ffect
        foodChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(db.getLastCarbohydrate(),"Carbohydrate"));
        yValues.add(new PieEntry(db.getLastProtein(),"Protein"));
        yValues.add(new PieEntry(db.getLastFat(),"Fat"));
        yValues.add(new PieEntry(db.getLastVitamin(),"Vitamin"));
        yValues.add(new PieEntry(db.getLastMineral(),"Minerals"));
        yValues.add(new PieEntry(db.getLastWater(),"Water"));
        yValues.add(new PieEntry(db.getLastUnknown(),"Unknown"));


        Description description = new Description();
        description.setText("Food consumption of "+db.getLastFooddate());
        description.setTextSize(15f);
        foodChart.setDescription(description);


        foodChart.animateY(1500, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        foodChart.setData(data);




    }



    public void createGlucoseChart()
    {
        linechartGlucose.setOnChartGestureListener(this);
        linechartGlucose.setOnChartValueSelectedListener(this);

        linechartGlucose.setDragEnabled(true);
        linechartGlucose.setScaleEnabled(true);
        linechartGlucose.setPinchZoom(true);

        lineDataSetGlucoseBeforeFast.setValues(getGlucoseValueBeforeFast());
        lineDataSetGlucoseAfterFast.setValues(getGlucoseValueAfterFast());
        lineDataSetGlucoseBeforeFast.setLabel("Glucose Before fast");
        lineDataSetGlucoseAfterFast.setLabel("Glucose After fast");
        datasetGlucose.clear();
        datasetGlucose.add(lineDataSetGlucoseBeforeFast);
        datasetGlucose.add(lineDataSetGlucoseAfterFast);
        lineDataGlucose = new LineData(datasetGlucose);
        linechartGlucose.clear();
        linechartGlucose.setData(lineDataGlucose);
        linechartGlucose.animateXY(2000,2000, Easing.EaseInExpo,Easing.EaseInExpo);
        linechartGlucose.setViewPortOffsets(60, 80, 50, 60);

        linechartGlucose.invalidate();

        lineDataSetGlucoseBeforeFast.setLineWidth(4);
        lineDataSetGlucoseBeforeFast.setFillAlpha(80);
        lineDataSetGlucoseBeforeFast.setDrawFilled(true);
        lineDataSetGlucoseBeforeFast.setFillColor(Color.YELLOW);
        lineDataSetGlucoseBeforeFast.setColor(Color.BLUE);

        lineDataSetGlucoseAfterFast.setLineWidth(4);
        lineDataSetGlucoseAfterFast.setFillAlpha(80);
        lineDataSetGlucoseAfterFast.setDrawFilled(true);
        lineDataSetGlucoseAfterFast.setFillColor(Color.BLUE);
        lineDataSetGlucoseAfterFast.setColor(Color.GREEN);

        LimitLine upper_limit = new LimitLine(8f, "Danger line after fast");
        upper_limit.setLineWidth(2f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);
        upper_limit.setLineColor(Color.RED);

        LimitLine lower_limit = new LimitLine(5.9f, "Danger line before fast");
        lower_limit.setLineWidth(2f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);
        lower_limit.setLineColor(Color.RED);

        YAxis leftAxis = linechartGlucose.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaximum(15f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

        linechartGlucose.getAxisRight().setEnabled(false);


        List<String> glucoseDateList = new ArrayList<>();
        glucoseDateList = db.getGlucoseDates();
        String[] values = new String[glucoseDateList.size()] ;



        for (int i =0;i<glucoseDateList.size();i++)
        {
            values[i]= glucoseDateList.get(i);

        }



        XAxis xAxisHeight = linechartGlucose.getXAxis();
        xAxisHeight.setValueFormatter(new ClaimsXAxisValueFormatterGlucose(values));
        xAxisHeight.setGranularity(1f);




    }


    public void createBpChart()
    {
        //weight chart

        lineChartBp.setOnChartGestureListener(this);
        lineChartBp.setOnChartValueSelectedListener(this);

        lineChartBp.setDragEnabled(true);
        lineChartBp.setScaleEnabled(true);
        lineChartBp.setPinchZoom(true);

        //two values
        lineDataSetBpHigh.setValues(getDataValueBPHigh());
        lineDataSetBpLow.setValues(getDataValueBpLow());

        lineDataSetBpHigh.setLabel("Bp High");
        lineDataSetBpLow.setLabel("Bp Low");
        datasetBp.clear();
        datasetBp.add(lineDataSetBpHigh);
        datasetBp.add(lineDataSetBpLow);

        lineDataBp = new LineData(datasetBp);

        lineChartBp.clear();
        lineChartBp.setData(lineDataBp);
        lineChartBp.animateXY(2000,2000, Easing.EaseInExpo,Easing.EaseInExpo);
        lineChartBp.setViewPortOffsets(100, 160, 100, 60);

        lineChartBp.invalidate();


        lineDataSetBpHigh.setLineWidth(4);
        lineDataSetBpHigh.setFillAlpha(80);
        lineDataSetBpHigh.setDrawFilled(true);
        lineDataSetBpHigh.setFillColor(Color.RED);
        lineDataSetBpHigh.setColor(Color.GREEN);

        lineDataSetBpLow.setLineWidth(4);
        lineDataSetBpLow.setFillAlpha(80);
        lineDataSetBpLow.setDrawFilled(true);
        lineDataSetBpLow.setFillColor(Color.GREEN);
        lineDataSetBpLow.setColor(Color.BLACK);

        LimitLine upper_limit = new LimitLine(120f, "Good High Value");
        upper_limit.setLineWidth(2f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);
        upper_limit.setLineColor(Color.BLUE);

        LimitLine lower_limit = new LimitLine(80f, "Good low value");
        lower_limit.setLineWidth(2f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);
        lower_limit.setLineColor(Color.BLUE);

        YAxis leftAxis = lineChartBp.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaximum(180f);
        leftAxis.setAxisMinimum(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

        lineChartBp.getAxisRight().setEnabled(false);



//
        List<String> bpDateList = new ArrayList<>();
        bpDateList = db.getBpDates();
        List<String> bpTimeList = new ArrayList<>();
        bpTimeList = db.getBpTimes();
        String[] valuesDate = new String[bpDateList.size()] ;
        String[] valuesTime = new String[bpTimeList.size()] ;


        for (int i =0;i<bpDateList.size();i++)
        {
            valuesDate[i]= bpDateList.get(i);
            valuesTime[i]= bpTimeList.get(i);

        }



        XAxis xAxisBp = lineChartBp.getXAxis();
        xAxisBp.setValueFormatter(new ClaimsXAxisValueFormatterBp(valuesDate,valuesTime));
        xAxisBp.setLabelRotationAngle(-45);
        xAxisBp.setGranularity(1f);


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

    private ArrayList<Entry> getDataValueBPHigh()
    {
        ArrayList<Entry> dataValue = new ArrayList<>();
        String[] columns = {COL_ID,COL_BP_HIGH};

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_BP,columns,null,null,null,null,COL_DATE);

        for(int i = 0; i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            dataValue.add(new Entry(cursor.getFloat(0),cursor.getFloat(1)));

        }

        return dataValue;

    }

    private ArrayList<Entry> getDataValueBpLow()
    {
        ArrayList<Entry> dataValue = new ArrayList<>();
        String[] columns = {COL_ID,COL_BP_LOW};

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_BP,columns,null,null,null,null,COL_DATE);

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

    private ArrayList<Entry> getGlucoseValueBeforeFast()
    {
        ArrayList<Entry> dataValue = new ArrayList<>();
        String[] columns = {COL_ID,COL_GLUCOSE_BEFORE_FAST};

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_GLUCOSE,columns,null,null,null,null,COL_DATE);

        for(int i = 0; i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            dataValue.add(new Entry(cursor.getFloat(0),cursor.getFloat(1)));

        }

        return dataValue;

    }

    private ArrayList<Entry> getGlucoseValueAfterFast()
    {
        ArrayList<Entry> dataValue = new ArrayList<>();
        String[] columns = {COL_ID,COL_GLUCOSE_AFTER_FAST};

        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_GLUCOSE,columns,null,null,null,null,COL_DATE);

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

    public class ClaimsXAxisValueFormatterGlucose extends ValueFormatter {


        PersonalRecordDatabase db;

        private String[] mValue;

        int count =0;

        public ClaimsXAxisValueFormatterGlucose(String[] values) {

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

    public class ClaimsXAxisValueFormatterBp extends ValueFormatter {


        PersonalRecordDatabase db;

        private String[] mValueDate;
        private String[] mValueTime;

        int count =0;

        public ClaimsXAxisValueFormatterBp(String[] valuesDate,String[] valuesTime) {

            this.mValueDate = valuesDate;
            this.mValueTime = valuesTime;
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
            return mValueDate[(int)value-1]+"\n"+mValueTime[(int)value-1];

        }
    }


}