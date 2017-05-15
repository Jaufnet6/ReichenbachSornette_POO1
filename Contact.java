import java.io.Serializable;

public class Contact implements Serializable{

    private int[] numbers;
    private int number;
    private int counterForNumbers;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String birthday;
    private String note;

    public Contact(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        counterForNumbers = 0;
    }

    //Setters and getters
    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return number;
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

    //Adds a new number in the contact
    public void addNewPhoneNumber(int newNumber){
        counterForNumbers++;
        int[] beforeAdding = numbers;
        int[] afterAdding = new int[counterForNumbers];

        for(int i = 0; i < beforeAdding.length; i++){
            afterAdding[i] = beforeAdding[i];
        }
        afterAdding[afterAdding.length-1] = newNumber;

        numbers = afterAdding;
    }

    //Removes a number in the contact
    public void removePhoneNumber(int deletedNumber){
        counterForNumbers--;
        int[] beforeAdding = numbers;
        int[] afterAdding = new int[counterForNumbers];

        //deletes the number in the array
        for(int i = 0; i < beforeAdding.length; i++){
            if(beforeAdding[i] == deletedNumber)
                beforeAdding[i] = 0;
        }

        //copies the values of the old array in the new one.
        for(int i = 0; i < beforeAdding.length; i++){
            for(int j = 0; j < afterAdding.length; i++){
                if(afterAdding[j] == 0){
                    if(beforeAdding[i] != 0)
                        afterAdding[j] = beforeAdding[i];
                }
            }
        }

        numbers = afterAdding;
    }

}











