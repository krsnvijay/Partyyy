package partyyy.com.notadeveloper.app.partyyy;

/**
 * Created by Chirag on 26-Feb-17.
 */

public class users {

    String name;
    String number;
    String email;


    public users() {
    }

    public users(String name, String number, String email) {
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
