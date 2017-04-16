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


}
