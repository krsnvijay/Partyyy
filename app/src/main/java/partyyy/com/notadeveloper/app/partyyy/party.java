package partyyy.com.notadeveloper.app.partyyy;

/**
 * Created by Chirag on 14-Mar-17.
 */

public class party {
    String title;
    String picture;
    String dates;
    String time;
    String time1;
    String email;
    String number;
    String address1;
    String address2;
    String address3;
    String pincode;
    String location;
    String uid;
    String nam;

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public party(String title, String picture, String dates, String time, String time1, String email, String number, String address1, String address2, String address3, String pincode, String location, String description, int tickets, String uid, String nam) {

        this.title = title;
        this.picture = picture;
        this.dates = dates;
        this.time = time;
        this.time1 = time1;
        this.email = email;
        this.number = number;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.pincode = pincode;
        this.location = location;
        this.description = description;
        this.tickets = tickets;
        this.uid = uid;
        this.nam = nam;
    }

    String description;
    int tickets;


}
