package partyyy.com.notadeveloper.app.partyyy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chirag on 26-Feb-17.
 */

public class users {
    String uid;
    String name;

    String number;
    String email;
    Boolean isorganizer;
    String orgname;
    ArrayList<String> myparties= new ArrayList<>();
    HashMap<String,party.BookedTickets> mytickets= new HashMap<>();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public HashMap<String, party.BookedTickets> getMytickets() {
        return mytickets;
    }

    public void setMytickets(HashMap<String, party.BookedTickets> mytickets) {
        this.mytickets = mytickets;
    }

    public Boolean getIsorganizer() {
        return isorganizer;
    }

    public void setIsorganizer(Boolean isorganizer) {
        this.isorganizer = isorganizer;
    }

    public ArrayList<String> getMyparties() {
        return myparties;
    }

    public void setMyparties(ArrayList<String> myparties) {
        this.myparties = myparties;
    }


    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public users() {
    }

    public users(String uid, String name, String number, String email) {
        this.uid = uid;
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String phone) {
        this.number = phone;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class mytickets
    {
         String name;
         String orderprice;
         String date;
         String loct;
         String time;
         String time1;
         String stagnumber;
         String couplenumber;
         public mytickets()
         {

         }

        public mytickets(String name, String orderprice, String date, String loct, String time, String time1, String stagnumber, String couplenumber) {
            this.name = name;
            this.orderprice = orderprice;
            this.date = date;
            this.loct = loct;
            this.time = time;
            this.time1 = time1;
            this.stagnumber = stagnumber;
            this.couplenumber = couplenumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrderprice() {
            return orderprice;
        }

        public void setOrderprice(String orderprice) {
            this.orderprice = orderprice;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLoct() {
            return loct;
        }

        public void setLoct(String loct) {
            this.loct = loct;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime1() {
            return time1;
        }

        public void setTime1(String time1) {
            this.time1 = time1;
        }

        public String getStagnumber() {
            return stagnumber;
        }

        public void setStagnumber(String stagnumber) {
            this.stagnumber = stagnumber;
        }

        public String getCouplenumber() {
            return couplenumber;
        }

        public void setCouplenumber(String couplenumber) {
            this.couplenumber = couplenumber;
        }
    }
}
