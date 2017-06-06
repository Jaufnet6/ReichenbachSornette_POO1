
import java.io.Serializable;

public class Contact implements Serializable{

    private String mobileNumber;
    private String homeNumber;
    private String faxNumber;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String birthday;
    private String note;
    private String address;
    //Mac : /Users/black and white/Desktop/App/Backgrounds/contactPic.png
    //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\contactPic.png
    private String picPath;
    
    
	//Setters and getters
    public void setMobileNumber(String number){
        this.mobileNumber = number;
    }
    public String getMobileNumber(){
        return mobileNumber;
    }
    public void setHomeNumber(String number){
        this.homeNumber = number;
    }
    public String getHomeNumber(){
        return homeNumber;
    }
    public void setFaxNumber(String number){
        this.faxNumber = number;
    }
    public String getFaxNumber(){
        return faxNumber;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public String getCompanyName(){
        return companyName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public String getBirthday(){
        return birthday;
    }
    public void setNote(String note){
        this.note = note;
    }
    public String getNote(){
        return note;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String pic) {
		this.picPath = pic;
	}

}











