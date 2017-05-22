
import java.io.Serializable;

public class Contact implements Serializable{

    private String[] numbers;
    private String mobileNumber;
    private String homeNumber;
    private String faxNumber;
    private int counterForNumbers;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String birthday;
    private String note;
    private String address;
    
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
    

//    //Adds a new number in the contact
//    public void addNewPhoneNumber(String newNumber){
//        counterForNumbers++;
//        String[] beforeAdding = numbers;
//        String[] afterAdding = new String[counterForNumbers];
//
//        for(int i = 0; i < beforeAdding.length; i++){
//            afterAdding[i] = beforeAdding[i];
//        }
//        afterAdding[afterAdding.length-1] = newNumber;
//
//        numbers = afterAdding;
//    }
//
//    //Removes a number in the contact
//    public void removePhoneNumber(String deletedNumber){
//        counterForNumbers--;
//        String[] beforeAdding = numbers;
//        String[] afterAdding = new String[counterForNumbers];
//
//        //deletes the number in the array
//        for(int i = 0; i < beforeAdding.length; i++){
//            if(beforeAdding[i] == deletedNumber)
//                beforeAdding[i] = "";
//        }
//
//        //copies the values of the old array in the new one.
//        for(int i = 0; i < beforeAdding.length; i++){
//            for(int j = 0; j < afterAdding.length; i++){
//                if(afterAdding[j] == ""){
//                    if(beforeAdding[i] != "")
//                        afterAdding[j] = beforeAdding[i];
//                }
//            }
//        }
//
//        numbers = afterAdding;
//    }

}











