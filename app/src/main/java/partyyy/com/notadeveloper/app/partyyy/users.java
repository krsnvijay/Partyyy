package partyyy.com.notadeveloper.app.partyyy;

import java.util.ArrayList;

/**
 * Created by Chirag on 26-Feb-17.
 */

public class users {

    String name;
    String number;
    String email;
    Boolean b;
    String orgname;
    ArrayList<String> myparties= new ArrayList<>();

    public ArrayList<String> getMyparties() {
        return myparties;
    }

    public void setMyparties(ArrayList<String> myparties) {
        this.myparties = myparties;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b = b;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public users() {
    }

    public users(String name, String number, String email, Boolean b, String orgname,ArrayList myparties) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.b=b;
        this.orgname = orgname;
        this.myparties = myparties;
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
