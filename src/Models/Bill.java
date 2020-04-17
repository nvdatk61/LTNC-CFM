/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Calendar;

/**
 *
 * @author Datnv
 */
public class Bill {
    private String IDBill;
    private String Status;
    private int Discount;
    private int TotalPrice;
    private String IDTable;
    private String UserStaff;
    private String TimeCheckIn;
    private String TimeCheckOut;
    private String Date;

    public Bill(String IDBill, String Date, String TimeCheckIn, String TimeCheckOut, String IDTable, String Status, int Discount, int TotalPrice, String UserStaff) {
        this.IDBill = IDBill;
        this.Status = Status;
        this.Discount = Discount;
        this.TotalPrice = TotalPrice;
        this.IDTable = IDTable;
        this.UserStaff = UserStaff;
        this.TimeCheckIn = TimeCheckIn;
        this.TimeCheckOut = TimeCheckOut;
        this.Date = Date;
    }
    

    public String getIDBill() {
        return IDBill;
    }

    public void setIDBill(String IDBill) {
        this.IDBill = IDBill;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int Discount) {
        this.Discount = Discount;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getIDTable() {
        return IDTable;
    }

    public void setIDTable(String IDTable) {
        this.IDTable = IDTable;
    }

    public String getUserStaff() {
        return UserStaff;
    }

    public void setUserStaff(String UserStaff) {
        this.UserStaff = UserStaff;
    }

    public String getTimeCheckIn() {
        return TimeCheckIn;
    }

    public void setTimeCheckIn(String TimeCheckIn) {
        this.TimeCheckIn = TimeCheckIn;
    }
    public static void TimeCheckIn(){
        Calendar c = Calendar.getInstance();
//        showTime(c);
            Time tin= new Time();
            tin.showTime(c);
    }
       
    public String getTimeCheckOut() {
        return TimeCheckOut;
    }

    public void setTimeCheckOut(String TimeCheckOut) {
        this.TimeCheckOut = TimeCheckOut;
    }
    public static void TimeCheckOut(){
        Calendar c = Calendar.getInstance();
            Time tout=new Time();
            tout.showTime(c);
    }
}
