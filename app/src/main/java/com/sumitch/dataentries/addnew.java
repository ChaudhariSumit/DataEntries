package com.sumitch.dataentries;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;

import java.util.Calendar;

public class addnew extends AppCompatActivity {

    DatabaseHelper myDB;

    EditText editdate, editlot_no, editbooks, editamount, editcomm, edittds, editctds;
    Button bttnreset, bttncalc;
    FloatingActionButton fbsubmit;

    Calendar currentDate;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        myDB=new DatabaseHelper (this);

        editdate=(EditText)findViewById(R.id.edtdate);
        editlot_no=(EditText)findViewById(R.id.edtlotno);
        editbooks=(EditText)findViewById(R.id.edtbooks);
        editamount=(EditText)findViewById(R.id.edtamount);
        bttncalc=(Button)findViewById(R.id.btncalc);

        editcomm=(EditText)findViewById(R.id.edtcomm);
        edittds=(EditText)findViewById(R.id.edttds);
        editctds=(EditText)findViewById(R.id.edtctds);
        bttnreset=(Button)findViewById(R.id.btnreset);

        fbsubmit=(FloatingActionButton)findViewById(R.id.fabsubmit);

        editlot_no.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange (View v, boolean hasFoucs){
                if (editlot_no.getText().length()<1) {
                    editlot_no.setError("Empty");
                }
            }
        });

        currentDate=Calendar.getInstance();

        day=currentDate.get(Calendar.DAY_OF_MONTH);
        month=currentDate.get(Calendar.MONTH);
        year=currentDate.get(Calendar.YEAR);

        month=month+1;

        editdate.setText(day+"/"+month+"/"+year);

        editlot_no.requestFocus();

        pickDate();
        calcData();
        rstData();
        addData();
    }

    public void pickDate(){
        editdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(addnew.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayofMonth) {
                                monthofYear=monthofYear+1;
                                editdate.setText(dayofMonth+"/"+monthofYear+"/"+year);
                            }
                        },year, month, day);
                        datePickerDialog.show();
                    }
                });
    }

    public void calcData(){
        bttncalc.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        int entamount=Integer.parseInt(editamount.getText().toString());

                        int calccomm=entamount*4/100;
                        int calctds=calccomm/20;
                        int calcctds=calccomm-calctds;

                        editcomm.setText(""+calccomm);
                        edittds.setText(""+calctds);
                        editctds.setText(""+calcctds);
                    }
                }
        );

    }


    public void rstData(){
        bttnreset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editdate.setText(day+"/"+month+"/"+year);
                        editlot_no.setText("");
                        editbooks.setText("");
                        editamount.setText("");
                        editcomm.setText("");
                        edittds.setText("");
                        editctds.setText("");
                        editlot_no.requestFocus();
                    }
                }
        );
    }

    public void addData(){
        fbsubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(editdate.getText().toString(),
                                editlot_no.getText().toString(),
                                editbooks.getText().toString(),
                                editamount.getText().toString(),
                                editcomm.getText().toString(),
                                edittds.getText().toString(),
                                editctds.getText().toString());
                        if(isInserted=true)
                            Toast.makeText(getApplicationContext(),
                                    getApplicationContext().getResources().getString(R.string.toast_data_added),
                                    Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(),
                                    getApplicationContext().getResources().getString(R.string.toast_data_fail),
                                    Toast.LENGTH_SHORT).show();
                        editdate.setText(day+"/"+month+"/"+year);
                        editlot_no.setText("");
                        editbooks.setText("");
                        editamount.setText("");
                        editcomm.setText("");
                        edittds.setText("");
                        editctds.setText("");
                        editlot_no.requestFocus();
                    }
                }
        );
    }
}
