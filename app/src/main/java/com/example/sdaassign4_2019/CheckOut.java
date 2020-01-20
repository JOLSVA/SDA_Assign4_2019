package com.example.sdaassign4_2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class CheckOut extends AppCompatActivity {

    TextView mDisplaySummary, mDisplayConfirmation,mDisplayAvailability;

    Calendar mDateAndTime = Calendar.getInstance();
    Calendar mCurrentDateAndTime = Calendar.getInstance();

    Button mDateSelection, mOrderButton;

    private DatabaseReference bookDb;

    String Availability, Book_Title, BookingTime, RequestTime, User_ID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        //find text views
        mDisplayConfirmation = findViewById(R.id.confirm);
        mDisplayAvailability = findViewById(R.id.availability);
        mDisplaySummary = findViewById(R.id.orderSummary);

        //sharedpreference to obtain the book title
        SharedPreferences mUserName = getSharedPreferences("user-details", MODE_PRIVATE);
        String userName = mUserName.getString("BORROWER_NAME", "");
        final String userId = mUserName.getString("BORROWER_ID", "");

        Intent myOrder = new Intent(this, LibraryViewAdapter.class);
        Bundle extras = getIntent().getExtras();
        final String bookTitle = Objects.requireNonNull(extras.getString("BOOK_TITLE"));

        //set the toolbar we have overridden
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //adds a home button to the toolbar.
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //find the buttons
        mDateSelection = findViewById(R.id.date);
        mOrderButton = findViewById(R.id.orderButton);

        //setting up the database
        bookDb = FirebaseDatabase.getInstance().getReference().child("BookReservation");

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference bookDb2 = bookDb.child(bookTitle);
                Map<String, Object> mapBookUpdate = new HashMap<>();
                //map2.put("msg", mMessageEditText.getText().toString());
                mapBookUpdate.put("RequestTime", mDateAndTime.getTime().toString());
                mapBookUpdate.put("BookingTime", mCurrentDateAndTime.getTime().toString());
                mapBookUpdate.put("Book_Title", bookTitle);
                mapBookUpdate.put("User_ID", userId);
                mapBookUpdate.put("Availability", "not available");
                bookDb2.updateChildren(mapBookUpdate);
            }
        });

        //implements the event listener on on the database when the data updates then automatically
        //update the conversation listview.
        DatabaseReference bookAvailabilityRef = bookDb.child(bookTitle);

        bookAvailabilityRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                updateBookReservation(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDisplayConfirmation.setText("Check out " + bookTitle);
    }

    public void updateBookReservation(DataSnapshot dataSnapshot) {

        Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {
            Availability = (String) (i.next()).getValue();
            Book_Title = (String) (i.next()).getValue();
            BookingTime = (String) (i.next()).getValue();
            RequestTime = (String) (i.next()).getValue();
            User_ID = (String) (i.next()).getValue();
        }
            //Log.d("dataSnapshot", "let us see" + Availability + Book_Title + BookingTime + RequestTime + User_ID);

        if (Book_Title == null){
            mDisplayAvailability.setText("The book is available.");
            mDateSelection.setEnabled(true);
            mOrderButton.setEnabled(true);
        }
        else {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
            Date bookingDate, requestDate = null, expiryDate = new Date();

            try {
                //bookingDate = format.parse(BookingTime);
                requestDate = format.parse(RequestTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Expiry date is set assuming 2 week's borrowing time
            Calendar cal = Calendar.getInstance();
            cal.setTime(requestDate);
            cal.add(Calendar.DATE, 14);
            expiryDate = cal.getTime();

            if ((Availability == "available") || (Availability == null)) {
                mDisplayAvailability.setText("The book is available.");
                mDateSelection.setEnabled(true);
                mOrderButton.setEnabled(true);
            } else {
                mDisplayAvailability.setText("The book is not available. It's avaiable on the " + expiryDate.toString());
                mDateSelection.setEnabled(false);
                mOrderButton.setEnabled(false);
            }
        }
    }

    //source SDA_2019 android course examples ViewGroup demo
    public void onDateClicked(View v) {

        DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mDateAndTime.set(Calendar.YEAR, year);
                mDateAndTime.set(Calendar.MONTH, monthOfYear);
                mDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateAndTimeDisplay();
            }
        };

        new DatePickerDialog(CheckOut.this, mDateListener,
                mDateAndTime.get(Calendar.YEAR),
                mDateAndTime.get(Calendar.MONTH),
                mDateAndTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateDateAndTimeDisplay() {

        //date time year
        SharedPreferences mUserName = getSharedPreferences("user-details", MODE_PRIVATE);
        String userName = mUserName.getString("BORROWER_NAME", "");
        String userId = mUserName.getString("BORROWER_ID", "");

        Intent myOrder = new Intent(this, LibraryViewAdapter.class);
        Bundle extras = getIntent().getExtras();
        final String bookTitle = Objects.requireNonNull(extras.getString("BOOK_TITLE"));

        CharSequence currentTime = DateUtils.formatDateTime(this, mCurrentDateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME|DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_NUMERIC_DATE|DateUtils.FORMAT_SHOW_YEAR);
        CharSequence SelectedDate = DateUtils.formatDateTime(this, mDateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR);
        String finalSummary = userName + " (user ID:" + userId + ") is to borrow " + bookTitle + " from " + SelectedDate + ". The current time is " + currentTime;

        mDisplaySummary.setText(finalSummary);

    }
}
