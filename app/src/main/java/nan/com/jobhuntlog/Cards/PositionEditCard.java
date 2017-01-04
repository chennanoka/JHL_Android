package nan.com.jobhuntlog.Cards;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import nan.com.jobhuntlog.Adapter.StageOptionsAdapter;
import nan.com.jobhuntlog.Application.App;
import nan.com.jobhuntlog.Constant.Constant;
import nan.com.jobhuntlog.DevelopKit.UI.NNButton;
import nan.com.jobhuntlog.DevelopKit.UI.NNDropdownSpinner;
import nan.com.jobhuntlog.DevelopKit.UI.NNEditText;
import nan.com.jobhuntlog.Objects.HistoryObject;
import nan.com.jobhuntlog.Objects.PositionObject;
import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-31.
 */
public class PositionEditCard extends ScrollView {

    public PositionObject nn_positionObject;

    public NNEditText titleEditText;
    public NNEditText locationEditText;


    public NNButton datePickerBut;
    public NNButton timePickerBut;
    public NNEditText notesforeventEditText;

    public DatePickerDialog datePickerDialog;
    public TimePickerDialog timePickerDialog;

    public NNDropdownSpinner stagespinner;

    public String title;
    public String location;
    public String date;
    public String time;
    public String notes;
    public String stage;

    public Date parsedDate;

    public Context nn_context;
    public PositionObject nn_postionObject;

    public NNButton bottomcancelbut;
    LinearLayout.LayoutParams contentParam;

    public PositionEditCard(Context context,Fragment fragment,PositionObject positionObject) {
        super(context);
        ViewGroup.LayoutParams positionparam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(positionparam);
        this.setPadding(Util.dptopx(10),Util.dptopx(10),Util.dptopx(10),Util.dptopx(10));

        this.nn_context=context;
        this.nn_postionObject=positionObject;

        RelativeLayout contentContainer = new RelativeLayout(context);
        contentContainer.setPadding(Util.dptopx(5),Util.dptopx(5),Util.dptopx(5),Util.dptopx(5));
        ViewGroup.LayoutParams contentContainerParam=new  ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentContainer.setLayoutParams(contentContainerParam);


        LinearLayout.LayoutParams labelParam =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        labelParam.setMargins(0,Util.dptopx(5),0,0);
        contentParam =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Util.dptopx(40));
        contentParam.setMargins(0,Util.dptopx(5),0,0);

        LinearLayout positionEditCard = new LinearLayout(context);
        positionEditCard.setOrientation(LinearLayout.VERTICAL);
        positionEditCard.setBackgroundResource(R.drawable.layerlist_frame_grayround);
        positionEditCard.setPadding(Util.dptopx(5),Util.dptopx(5),Util.dptopx(5),Util.dptopx(5));
        positionEditCard.setLayoutParams(contentContainerParam);
        positionEditCard.setId(Util.generateViewId());

        //title label
        TextView titleLabel =new TextView(context);
        titleLabel.setText("Title");
        titleLabel.setLayoutParams(labelParam);


        //title info
        titleEditText =new NNEditText(context);
        titleEditText.setText(positionObject.getTitle());


        //location label
        TextView locaitonLabel =new TextView(context);
        locaitonLabel.setText("Location");
        locaitonLabel.setLayoutParams(labelParam);


        //location info
        locationEditText=new NNEditText(context);
        locationEditText.setText(positionObject.getLocation());

        //coming event label
        TextView comeeventLabel =new TextView(context);
        comeeventLabel.setText("Coming Event");
        comeeventLabel.setLayoutParams(labelParam);

        //date picker
        datePickerBut = new NNButton(context,NNButton.GRAY);
        datePickerBut.setText(positionObject.getEventDate());
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
        timePickerBut.setText(positionObject.getEventTime());
        timePickerBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        int year = Util.getYear();
        int month = Util.getMonth();
        int day = Util.getDay();
        int hour=12;
        int minutes=0;

        try {
            parsedDate =new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(positionObject.getEventDate()+" "+positionObject.getEventTime());

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(parsedDate);
            year=calendar.get(Calendar.YEAR);
            month=calendar.get(Calendar.MONTH);
            day=calendar.get(Calendar.DAY_OF_MONTH);
            hour=calendar.get(Calendar.HOUR_OF_DAY);
            minutes=calendar.get(Calendar.MINUTE);

        } catch (ParseException e) {
            parsedDate=null;
            Toast.makeText(context,"Sorry,can not parse time",Toast.LENGTH_LONG).show();
        }


        notesforeventEditText=new NNEditText(context,"Input Note for Event");
        notesforeventEditText.setLayoutParams(contentParam);
        notesforeventEditText.setText(positionObject.getEventNote());

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
        },year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(Util.millisecondsTimestamp());
        datePickerDialog.getDatePicker().setMinDate(-2208898420000L);


        timePickerDialog=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String[] less10strings=new String[] {"00","01","02","03","04","05","06","07","08","09"};
                String minutestring = (minute <=9) ? less10strings [minute] : String.valueOf(minute);
                String hourOfDaystring=(hourOfDay <=9) ? less10strings [hourOfDay] : String.valueOf(hourOfDay);
                timePickerBut.setText(hourOfDaystring + ":" + minutestring);
            }
        },hour,minutes,true);



        TextView stagelabel =new TextView(context);
        stagelabel.setText("Stage");
        stagelabel.setLayoutParams(labelParam);

        stagespinner=new NNDropdownSpinner(context, new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stagespinner.setTag(position);
                stage= Constant.stagelist.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        stagespinner.setLayoutParams(contentParam);
        StageOptionsAdapter stageAdapter = new StageOptionsAdapter(context, Constant.stagelist);
        stagespinner.spinner.setAdapter(stageAdapter);
        int selection=0;
        for (int i = 0; i < Constant.stagelist.size(); i++) {
            if (Constant.stagelist.get(i).equalsIgnoreCase(positionObject.getStage())) {
                selection=i;
                break;
            }
        }
        stagespinner.spinner.setSelection(selection);

        LinearLayout buttonsContainer=new LinearLayout(context);
        RelativeLayout.LayoutParams buttonsParam=new  RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonsParam.setMargins(0,Util.dptopx(10),0,0);
        buttonsParam.addRule(RelativeLayout.BELOW,positionEditCard.getId());
        buttonsParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonsContainer.setLayoutParams(buttonsParam);

        NNButton cancelbutton = new NNButton(context);
        LinearLayout.LayoutParams cancelbuttonParam=new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cancelbuttonParam.weight=1;
        cancelbutton.setLayoutParams(cancelbuttonParam);
        cancelbutton.setText("Cancel");
        cancelbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((FragmentActivity)nn_context).getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    ((FragmentActivity)nn_context).getSupportFragmentManager().popBackStack ();
                }
            }
        });

        NNButton updatebutton = new NNButton(context);
        LinearLayout.LayoutParams updatebuttonParam=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        updatebuttonParam.weight=1;
        updatebutton.setLayoutParams(updatebuttonParam);
        updatebutton.setText("Update");
        updatebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                title=titleEditText.getText();
                location=locationEditText.getText();
                date=datePickerBut.getText().toString();
                time=timePickerBut.getText().toString();
                notes=notesforeventEditText.getText();
                if(!title.isEmpty()&&!location.isEmpty()&&!notes.isEmpty()&&!date.equals("Pick Date")&&!time.equals("Pick Time")) {
                    nn_postionObject.setTitle(title);
                    nn_postionObject.setLocation(location);
                    nn_postionObject.setEventDate(date);
                    nn_postionObject.setEventTime(time);
                    nn_postionObject.setEventNote(notes);
                    nn_postionObject.setStage(stage);
                    boolean result=App.getDataBaseManager().updatePosition(nn_postionObject);
                    if(result){

                        //update history
                        Date createdate = new Date();
                        String cDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(createdate);
                        HistoryObject historyobj=new HistoryObject(title,"Action:"+Constant.HistoryAction.UPDATE.toString()+" Stage:"+stage,cDate);
                        App.getDataBaseManager().createHistory(historyobj);

                        Toast.makeText(nn_context,"Updated!",Toast.LENGTH_LONG).show();
                        if (((FragmentActivity)nn_context).getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            ((FragmentActivity)nn_context).getSupportFragmentManager().popBackStack ();
                        }
                    }else{
                        Toast.makeText(nn_context,"Failed!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(nn_context,"Error,Please check your input and try again.",Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonsContainer.addView(cancelbutton);
        buttonsContainer.addView(updatebutton);


        positionEditCard.addView(titleLabel);
        positionEditCard.addView(titleEditText);
        positionEditCard.addView(locaitonLabel);
        positionEditCard.addView(locationEditText);
        positionEditCard.addView(comeeventLabel);
        positionEditCard.addView(datePickerBut);
        positionEditCard.addView(timePickerBut);
        positionEditCard.addView(notesforeventEditText);
        positionEditCard.addView(stagelabel);
        positionEditCard.addView(stagespinner);


        contentContainer.addView(positionEditCard);
        contentContainer.addView(buttonsContainer);

        this.addView(contentContainer);
    }

}
