package nan.com.jobhuntlog.Cards;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import nan.com.jobhuntlog.Adapter.StageOptionsAdapter;
import nan.com.jobhuntlog.Application.App;
import nan.com.jobhuntlog.Constant.Constant;
import nan.com.jobhuntlog.DevelopKit.UI.NNButton;
import nan.com.jobhuntlog.DevelopKit.UI.NNDropdownSpinner;
import nan.com.jobhuntlog.DevelopKit.UI.NNEditText;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.Objects.HistoryObject;
import nan.com.jobhuntlog.Objects.PositionObject;
import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-12.
 */
public class PositionCreateCard extends LinearLayout {

    public NNEditText titleEditText;
    public NNEditText locationEditText;


    public NNButton datePickerBut;
    public NNButton timePickerBut;
    public NNEditText notesforeventEditText;

    public DatePickerDialog datePickerDialog;
    public TimePickerDialog timePickerDialog;

    public NNDropdownSpinner stagespinner;


    public static int NEW=0;
    public static int EDIT=1;


    public String title;
    public String location;
    public String date;
    public String time;
    public String notes;
    public String stage;

    public int type;

    public NNButton bottomokbut;
    public NNButton bottomcancelbut;

    public Fragment nn_fragment;
    public Context nn_context;


    LayoutParams contentParam;
    //according to status
    public PositionCreateCard(final Context context, Fragment fragment, int type){
        this(context);
        nn_context=context;
        nn_fragment=fragment;
        this.type=type;


        //botom linearlayout
        LayoutParams botbarparam =new LayoutParams(Util.dptopx(Util.compromiseScreenWidth(250)), LayoutParams.WRAP_CONTENT);
        botbarparam.setMargins(0,Util.dptopx(15),0,0);

        LinearLayout bottomlayout=new LinearLayout(context);
        bottomlayout.setLayoutParams(botbarparam);
        bottomlayout.setOrientation(LinearLayout.HORIZONTAL);

        //shared param bottom two button
        LinearLayout.LayoutParams bottomparam=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        bottomparam.weight=1;
        bottomparam.setMargins(Util.dptopx(5),Util.dptopx(5),Util.dptopx(5),Util.dptopx(5));

        //the last button is create
        bottomokbut=new NNButton(context);
        bottomokbut.setLayoutParams(bottomparam);
        bottomokbut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if(type==NEW){
            bottomokbut.setText("Create");
            bottomokbut.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    title=titleEditText.getText();
                    location=locationEditText.getText();
                    date=datePickerBut.getText().toString();
                    time=timePickerBut.getText().toString();
                    notes=notesforeventEditText.getText();

                    if(!title.isEmpty()&&!location.isEmpty()&&!notes.isEmpty()&&!date.equals("Pick Date")&&!time.equals("Pick Time")) {
                        //create new item in SQLite & dismiss dialog
                        Date createdate = new Date();
                        String cDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(createdate);
                        PositionObject positionObject = new PositionObject(title, location, cDate, date, time, notes, stage);
                        App.getDataBaseManager().createPosition(positionObject);
                        //add create history
                        HistoryObject historyobj=new HistoryObject(title,"Action:"+Constant.HistoryAction.CREATE.toString()+" Stage:"+stage,cDate);
                        App.getDataBaseManager().createHistory(historyobj);
                        ((NNFragment)nn_fragment).dismissDialog(Constant.TAG_HOME_DIALOG_CREATE_POSITIVE);
                        ((NNFragment)nn_fragment).onRefreshAll();
                    }else{
                        Toast.makeText(context,"Error,Please check your input and try again.",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{
            bottomokbut.setText("Update");

        }


        bottomcancelbut=new NNButton(context);
        bottomcancelbut.setTag(Constant.TAG_HOME_DIALOG_CREATE_POSITIVE);
        bottomcancelbut.setLayoutParams(bottomparam);
        bottomcancelbut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NNFragment)nn_fragment).dismissDialog(Constant.TAG_HOME_DIALOG_CREATE_POSITIVE);
            }
        });
        bottomcancelbut.setText("Cancel");

        bottomlayout.addView(bottomcancelbut);
        bottomlayout.addView(bottomokbut);

        this.addView(bottomlayout);
    }

    public PositionCreateCard(Context context) {
        super(context);

        this.setPadding(Util.dptopx(3),Util.dptopx(3),Util.dptopx(3),Util.dptopx(3));
        this.setBackgroundColor(ContextCompat.getColor(context, R.color.white_smoke));
        this.setOrientation(LinearLayout.VERTICAL);

        FrameLayout.LayoutParams viewgroupParam = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(viewgroupParam);



        LayoutParams labelParam =new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        labelParam.setMargins(0,Util.dptopx(5),0,0);
        contentParam =new LayoutParams(Util.dptopx(Util.compromiseScreenWidth(250)), Util.dptopx(40));
        contentParam.setMargins(0,Util.dptopx(5),0,0);

        //title label
        TextView titleLabel =new TextView(context);
        titleLabel.setText("Title");
        titleLabel.setLayoutParams(labelParam);


        //title info
        titleEditText =new NNEditText(context,"Input Title");

        //location label
        TextView locaitonLabel =new TextView(context);
        locaitonLabel.setText("Location");
        locaitonLabel.setLayoutParams(labelParam);


        //location info
        locationEditText=new NNEditText(context,"Input Location");


        //coming event label
        TextView comeeventLabel =new TextView(context);
        comeeventLabel.setText("Coming Event");
        comeeventLabel.setLayoutParams(labelParam);

        //date picker
        datePickerBut = new NNButton(context,NNButton.GRAY);
        datePickerBut.setText("Pick Date");
        datePickerBut.setLayoutParams(contentParam);
        datePickerBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //time picker
        timePickerBut = new NNButton(context,NNButton.GRAY);
        timePickerBut.setLayoutParams(contentParam);
        timePickerBut.setText("Pick Time");
        timePickerBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        notesforeventEditText=new NNEditText(context,"Input Note for Event");
        notesforeventEditText.setLayoutParams(contentParam);

        //coming event date picker
        datePickerDialog =new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String[] less10strings=new String[] {"01","02","03","04","05","06","07","08","09"};
                String monthstring = (monthOfYear <=8) ? less10strings [monthOfYear] : String.valueOf(monthOfYear);
                if (monthOfYear >= 9) {
                    monthstring =  String.valueOf(monthOfYear + 1);
                }
                String daystring = (dayOfMonth <=9) ? less10strings [dayOfMonth-1] : String.valueOf(dayOfMonth);
                view.updateDate(year,monthOfYear,dayOfMonth);
                datePickerBut.setText(year + "/" + monthstring + "/" + daystring);
            }
        },Util.getYear(),Util.getMonth(),Util.getDay());
        datePickerDialog.getDatePicker().setMaxDate(Util.millisecondsTimestamp());
        datePickerDialog.getDatePicker().setMinDate(-2208898420000L);


        timePickerDialog=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String[] less10strings=new String[] {"00","01","02","03","04","05","06","07","08","09"};
                String minutestring = (minute <=9) ? less10strings [minute] : String.valueOf(minute);
                String hourOfDaystring=(hourOfDay <=9) ? less10strings [hourOfDay] : String.valueOf(hourOfDay);
                //settimebut.setText(hourOfDaystring+ "/" + minutestring);
                timePickerBut.setText(hourOfDaystring + ":" + minutestring);
            }
        },12,0,true);

        stagespinner=new NNDropdownSpinner(context, new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stagespinner.setTag(position);
                stage=Constant.stagelist.get(position);
                //Toast.makeText(nn_context, Constant.stagelist.get(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        stagespinner.setLayoutParams(contentParam);
        StageOptionsAdapter stageAdapter = new StageOptionsAdapter(context, Constant.stagelist);
        stagespinner.spinner.setAdapter(stageAdapter);


        TextView stagelabel =new TextView(context);
        stagelabel.setText("Stage");
        stagelabel.setLayoutParams(labelParam);


        this.addView(titleLabel);
        this.addView(titleEditText);
        this.addView(locaitonLabel);
        this.addView(locationEditText);
        this.addView(comeeventLabel);
        this.addView(datePickerBut);
        this.addView(timePickerBut);
        this.addView(notesforeventEditText);
        this.addView(stagelabel);
        this.addView(stagespinner);
    }

}