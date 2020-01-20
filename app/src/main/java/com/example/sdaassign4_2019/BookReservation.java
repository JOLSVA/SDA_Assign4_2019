package com.example.sdaassign4_2019;

import java.util.HashMap;
import java.util.Map;

public class BookReservation {

        private String Availability;
        private String Book_Title;
        private String BookingTime;
        private String RequestTime;
        private String User_ID;
        public Map<String, Object> mapBookUpdate = new HashMap<>();

        public BookReservation() {
        }

       // public String getAvailability() {
        //    return Availability;
        //}
        //public String getBookTitle() {
        //    return Book_Title;
        //}

        //public String getBookingTime() {
        //    return BookingTime;
        //}

        //public String getRequestTime() {
        //    return RequestTime;
        //}

        //public String getUserID() {
        //    return User_ID;
        //}


    public BookReservation(String availability, String book_Title, String bookingTime, String requestTime, String user_ID) {
            this.Availability = availability;
            this.Book_Title = book_Title;
            this.BookingTime = bookingTime;
            this.RequestTime = requestTime;
            this.User_ID = user_ID;
        }



}
