package com.example.sdaassign4_2019;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */

public class Settings extends Fragment {

    private static final String BORROWER_NAME = "BORROWER_NAME";
    private static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    private static final String BORROWER_ID = "BORROWER_ID";

    private EditText mBorrowerName, mEmailAddress, mBorrowerId;

    public Settings() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        final SharedPreferences prefs = getActivity().getSharedPreferences("user-details", MODE_PRIVATE);

        //find the editText widgets
        mBorrowerName = view.findViewById(R.id.userName);
        mEmailAddress = view.findViewById(R.id.email);
        mBorrowerId = view.findViewById(R.id.borrowerID);

        //display shared preferences in the widget
        mBorrowerName.setText(prefs.getString(BORROWER_NAME, ""));
        mEmailAddress.setText(prefs.getString(EMAIL_ADDRESS, ""));
        mBorrowerId.setText(prefs.getString(BORROWER_ID, ""));

        //Task 3 to check if the user saved setting and to send the check result to the adapter

        // find the buttons
        final Button saveButton = view.findViewById(R.id.button);
        final Button resetButton = view.findViewById(R.id.button_reset);

        // save user details
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String emailAddress = mEmailAddress.getText().toString();
                String borrowerName = mBorrowerName.getText().toString();
                String borrowerID = mBorrowerId.getText().toString();

                //validate the user details entered
                if (!emailAddress.contains("@") && emailAddress.length() != 0) {
                    Toast.makeText(getContext(), "Incorrect email address", Toast.LENGTH_LONG).show();
                } else if (borrowerName.length() == 0) {
                    Toast.makeText(getContext(), "Borrower Name is missing", Toast.LENGTH_LONG).show();
                } else if (borrowerID.length() == 0) {
                    Toast.makeText(getContext(), "Borrower ID is missing", Toast.LENGTH_LONG).show();
                } else if (emailAddress.length() == 0) {
                    Toast.makeText(getContext(), "Email Address is missing", Toast.LENGTH_LONG).show();
                } else {
                    // save user details to shared preferences
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(BORROWER_NAME, borrowerName);
                    editor.putString(EMAIL_ADDRESS, emailAddress);
                    editor.putString(BORROWER_ID, borrowerID);
                    editor.apply();
                    Toast.makeText(getContext(), "User details successfully saved.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // reset user details
        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // reset shared preference
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(BORROWER_NAME, "");
                editor.putString(EMAIL_ADDRESS, "");
                editor.putString(BORROWER_ID, "");
                editor.apply();

                // display reset shared preference
                mBorrowerName.setText(prefs.getString(BORROWER_NAME,"" ));
                mEmailAddress.setText(prefs.getString(EMAIL_ADDRESS,"" ));
                mBorrowerId.setText(prefs.getString(BORROWER_ID,"" ));

            }
        });

        return view;

    }

}

