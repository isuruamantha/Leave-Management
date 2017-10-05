package rapticon.tk.leavemanagement.model;

/**
 * Created by Isuru Amantha on 10/2/2017.
 */

public class User extends BaseElement {

    String firstName;
    Boolean isHr;
    String nic;
    String lastName;
    String phoneNumber;
    String email;
    String dob;
    String password;
    String totalLeavs;
    int totalLeaves;




    public Boolean getHr() {
        return isHr;
    }

    public void setHr(Boolean hr) {
        isHr = hr;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTotalLeavs() {
        return totalLeavs;
    }

    public void setTotalLeavs(String totalLeavs) {
        this.totalLeavs = totalLeavs;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
