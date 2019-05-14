package com.example.g2mdx.ui.fragment.calender;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CalenderPresenter {

    private CalenderView view;

    private String title = "";
    private String day = "";
    private String startTime = "";
    private String endTime = "";
    private String fullDate = "";


    CalenderPresenter(CalenderView view) {
        this.view = view;
    }

    public void setTitle(String title) {
        this.title = title.trim();
        checkInputs();
    }


    void setDay(String day) {
        this.day = day;
        checkInputs();
    }

    void setStartTime(String startTime) {
        this.startTime = startTime;
        checkInputs();
    }

    void setEndTime(String endTime) {
        this.endTime = endTime;
        checkInputs();
    }

    void getFullDate() {
        try {
            String firstDate = day + " " + startTime;
            String secondDate = day + " " + endTime;
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date startDate = dateFormatter.parse(firstDate);
            Date endDate = dateFormatter.parse(secondDate);
            long startMillis = startDate.getTime();
            long endMillis = endDate.getTime();

            view.startPhoneCalender(title, startMillis, endMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void checkInputs() {
        if (title.isEmpty() || day.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            view.enableButton(false);
        } else {
            view.enableButton(true);
        }
    }

}
