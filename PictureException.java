
public class PictureException extends Exception{

	public PictureException(){
		super("Error : there is a problem with the pictures.");
	}
	
	public PictureException(String message){
		super(message);
	}
	
}
