package com.example.module;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StoreActivity extends AppCompatActivity implements View.OnClickListener {
    private int ii = 0;
    String dateText, timeText;
    TextView dateTextView;
    TextView timeTextView;
    private static final String TAG = "storeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Button dateButton = (Button)findViewById(R.id.pickDate);
        Button timeButton = (Button)findViewById(R.id.pickTime);
        Button btn=(Button)findViewById(R.id.button);
        dateTextView = (TextView)findViewById(R.id.dateTextView);
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        btn.setOnClickListener(this);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });
    }

    public void getCode(int i){
        String time = "";
        NumberPlate plate = new NumberPlate();
        time = dateText + timeText;
        plate.setStr(time);
        ImageView ivCode=(ImageView)findViewById(R.id.imageView);
        //EditText etContent=(EditText)findViewById(R.id.editText);
        //plate.setLocation(etContent.getText().toString());
        plate.setNumber(i);

        BarcodeEncoder encoder = new BarcodeEncoder();
        try{
            Bitmap bit = encoder.encodeBitmap(plate.toJson()
                    , BarcodeFormat.QR_CODE,250,250);
            ivCode.setImageBitmap(bit);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        ii++;
        getCode(ii);
    }

    private void handleDateButton() {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                dateText = DateFormat.format("yyyy-MM-dd ", calendar1).toString();
                dateTextView.setText(dateText);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }

    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        // boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i(TAG, "onTimeSet: " + hour + minute);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR_OF_DAY, hour);
                calendar1.set(Calendar.MINUTE, minute);
                calendar1.set(Calendar.SECOND, 0);
                timeText = DateFormat.format("HH:mm:ss", calendar1).toString();
                timeTextView.setText(timeText);
            }
        }, HOUR, MINUTE, true);

        timePickerDialog.show();
    }
}
