package partyyy.com.notadeveloper.app.partyyy;

/**
 * Created by Chirag on 14-Mar-17.
 */

public class party {
    String name;
    String picture;
    String email;
    String number;
    String description;
    int tickets;

    public party(String name, String picture, String email, String number, String description, int tickets) {
        this.name = name;
        this.picture = picture;
        this.email = email;
        this.number = number;
        this.description = description;
        this.tickets = tickets;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
}
