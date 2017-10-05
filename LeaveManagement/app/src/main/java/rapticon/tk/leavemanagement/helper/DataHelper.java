package rapticon.tk.leavemanagement.helper;

import java.util.ArrayList;

import rapticon.tk.leavemanagement.model.BaseElement;
import rapticon.tk.leavemanagement.model.Leave;

public class DataHelper {


    public static void insertDummyData() {
        ArrayList<BaseElement> leaveArrayList = new ArrayList<>();

        Leave leave1 = new Leave();
        leave1.setEndDate("1/1/41");
        leave1.setReason("hi");
        leave1.setStartDate("1/1/4");

        Leave leave2 = new Leave();
        leave2.setEndDate("1/1/41");
        leave2.setReason("hi");
        leave2.setStartDate("1/1/4");

        Leave leave3 = new Leave();
        leave3.setEndDate("1/1/41");
        leave3.setReason("hi");
        leave3.setStartDate("1/1/4");

        leaveArrayList.add(leave1);
        leaveArrayList.add(leave2);
        leaveArrayList.add(leave3);


        SharedPrefHelper.setLeaveList(leaveArrayList);
    }
}
