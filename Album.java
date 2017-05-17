import java.io.Serializable;

public class Album implements Serializable{

	private int IDalbum;
	private String name;
	private String[] pics;
	//Picture which is displayed on gallery to represent the album
	//is the first picture added OR a default picture if album still is empty
	private String homePic = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Gallery\\defaultPicture\\album.png";
	
	public Album(){
		//Default name
		this.name="New album";
		this.pics= new String[0];
	}
	
	public Album(String name) {
		//Useful for addAlbum method in Gallery class
		if(name==""){
			this.name="New album";
		} else {
			this.name = name;
		}
		this.pics= new String[0];
	}

	public int getID() {
		return IDalbum;
	}
	public void setID(int iD) {
		IDalbum = iD;
	}
	
	public String getName() {
		return name;
	}
	
	//Setter
	public void renameAlbum(String name) {
		this.name = name;
	}
	
	public String[] getPics() {
		return pics;
	}
	
	public void setPics(String[] pics){
		this.pics=pics;
		homePic=pics[0];
	}
	
	public int getSize(){
		return pics.length;
	}
	
	
	public void addPic(String newPic){
		//Create a new array of pics with 1 more slot
		String newPics[] = new String[getSize()+1];
		//New picture is added at the end of the new array
		newPics[newPics.length]=newPic;
		//New array is replacing the previous one
		setPics(newPics);
	}
	
//	public void removePic(String deletedPic){
//		String[] beforeRemove = getPics();
//		String[] afterRemove = new String[beforeRemove.length-1];
//		//Searching the pic to remove
//		for(int i=0; i<beforeRemove.length; i++){
//			if(beforeRemove[i].getPicName() == deletedPic.getPicName()){
//				beforeRemove[i]=null;
//			}	
//		}
//		//Creating the new album without the deleted pic
//		for(int i=0; i<beforeRemove.length; i++){
//			for(int j=0; j<afterRemove.length; j++){
//				if(beforeRemove[i] != null){
//					afterRemove[j]=beforeRemove[i];
//				}
//			}
//		}
//		//Setting the new array of pics in the current album
//		setPics(afterRemove);
//	}
	
	
	
}
