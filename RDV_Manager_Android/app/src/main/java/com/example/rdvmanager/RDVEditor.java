package com.example.rdvmanager;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class RDVEditor extends AppCompatActivity {
    TextView id;
    EditText etTitle;

    int day,month,year;
    Button btnPickDate;
    EditText etDate;
    DatePickerDialog.OnDateSetListener onDate;

    int hours, minutes;
    Button btnPickTime;
    EditText etTime;
    TimePickerDialog.OnTimeSetListener onTime;

    EditText etContact;
    EditText etAddress;
    EditText etPhone;

    Boolean fromAdd;

    DataBase myHelper;

    static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 62;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rdv_creator);

        id = (TextView) findViewById(R.id._id);

        etTitle = (EditText) findViewById(R.id.etTitle);

        btnPickDate = (Button) findViewById(R.id.btnPickDate);
        etDate = (EditText) findViewById(R.id.etDate);

        onDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                year = year;
                month = month;
                day = dayOfMonth;
                etDate.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));

            }
        };

        btnPickTime = (Button) findViewById(R.id.btnPickTime);
        etTime = (EditText) findViewById(R.id.etTime);
        onTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                hours = hour;
                minutes = minute;

                etTime.setText(new StringBuilder().append(hours).append(":").append(minutes));
            }
        };

        etAddress = findViewById(R.id.etAddress);
        etContact = (EditText) findViewById(R.id.etContact);
        etPhone = (EditText) findViewById(R.id.etPhone);
        myHelper = new DataBase(this);
        myHelper.open();

        Intent intent = getIntent();
        fromAdd = intent.getBooleanExtra("fromAdd", false);
        if (!fromAdd) {
            Bundle b = intent.getExtras();
            RDV selectedRDV = b.getParcelable("SelectedRDV");
            id.setText(String.valueOf(selectedRDV.getId()));
            etTitle.setText(selectedRDV.getTitle());
            etDate.setText(selectedRDV.getDate());
            etTime.setText(selectedRDV.getTime());
            etAddress.setText(selectedRDV.getAddress());
            etContact.setText(selectedRDV.getContact());
            etPhone.setText(Integer.toString(selectedRDV.getPhoneNumber()));
        }
    }

    public void pickDate(View v){
        showDatePicker();
    }

    private void showDatePicker() {

        DatePickerFragment date= new DatePickerFragment();

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        Bundle args = new Bundle();
        args.putInt("year",year);
        args.putInt("month",month);
        args.putInt("day",day);

        date.setArguments(args);
        date.setCallBack(onDate);
        date.show(getSupportFragmentManager(),"Date Picker");
    }

    public void pickTime(View view){
        showTimePicker();
    }

    private void showTimePicker() {
        TimePickerFragment time= new TimePickerFragment();
        final Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        Bundle args = new Bundle();
        args.putInt("hours",hours);
        args.putInt("minutes",minutes);
        time.setArguments(args);
        time.setCallBack(onTime);
        time.show(getSupportFragmentManager(),"Time Picker");
    }

    public void launchMaps(View view) {
        String map = "http://maps.google.co.in/maps?q=" + etAddress.getText() ;
        Uri gmmIntentUri = Uri.parse(map);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        // if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        // }
    }

    public void saveRDV(View view) {
        String title= etTitle.getText().toString();
        String date=etDate.getText().toString();
        String time=etTime.getText().toString();
        String address=etAddress.getText().toString();
        String contact=etContact.getText().toString();
        String phone=etPhone.getText().toString();
        int phoneNumber = parseInt(phone);

        if (fromAdd) {
            RDV rdv = new RDV(title, date, time, address, contact, phoneNumber);
            myHelper.addContact(rdv);
            Intent main = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
        }
        else {
            long pId = parseLong(id.getText().toString());
            RDV rdv = new RDV(pId, title, date, time, address, contact, phoneNumber);
            int n = myHelper.update(rdv);

            Intent main = new Intent(this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
        }

    }

    public void checkPermission(View view) {
        int permissionCheck= ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE);
        if (permissionCheck== PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:{
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void phoneCall(View v)   {
        checkPermission(v);
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel: +33"+etPhone.getText()));//change the number
        startActivity(callIntent);
    }
    public void onCancelClick(View v){
//Intent intent = new Intent(this,MainActivity.class);
        // startActivity(intent);
        finish();
    }
}
