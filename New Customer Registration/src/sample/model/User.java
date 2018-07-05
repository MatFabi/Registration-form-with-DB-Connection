package sample.model;

public class User {

    private String firstName;
    private String lastName;
    private String telNumber;
    private String zipCode;
    private String info;
    private String personalId;
    private String userName;
    private String password;

    public User(){

    }
    public User(String firstName, String lastName, String telNumber, String personalId, String zipCode, String info, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.personalId=personalId;
        this.zipCode = zipCode;
        this.info = info;
        this.userName=userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPersonalid() {
        return personalId;
    }

    public void setPersonalid(String personalid) {
        this.personalId = personalid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
