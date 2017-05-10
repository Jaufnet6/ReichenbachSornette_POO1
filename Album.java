
public class Album {

	private int IDalbum;
	private String name;
	private Picture[] pics;
	//Picture which is displayed on gallery to represent the album
	//is the first picture added OR a default picture if album still is empty
	private Picture homePic;
	
	public Album(){
		//Default name
		this.name="New album";
		this.pics= new Picture[0];
		homePic = new defaultAlbumPic();
	}
	
	public Album(String name) {
		//Useful for addAlbum method in Gallery class
		if(name==""){
			this.name="New album";
		} else {
			this.name = name;
		}
		this.pics= new Picture[0];
		homePic = new defaultAlbumPic();
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
	
	public Picture[] getPics() {
		return pics;
	}
	
	public void setPics(Picture[] pics){
		this.pics=pics;
		homePic=pics[0];
	}
	
	public int getSize(){
		return pics.length;
	}
	
	
	public void addPic(Picture newPic){
		//Create a new array of pics with 1 more slot
		Picture newPics[] = new Picture[getSize()+1];
		//New picture is added at the end of the new array
		newPics[newPics.length]=newPic;
		//New array is replacing the previous one
		setPics(newPics);
	}
	
	public void removePic(Picture deletedPic){
		Picture[] beforeRemove = getPics();
		Picture[] afterRemove = new Picture[beforeRemove.length-1];
		//Searching the pic to remove
		for(int i=0; i<beforeRemove.length; i++){
			if(beforeRemove[i].getID() == deletedPic.getID()){
				beforeRemove[i]=null;
			}	
		}
		//Creating the new album without the deleted pic
		for(int i=0; i<beforeRemove.length; i++){
			for(int j=0; j<afterRemove.length; j++){
				if(beforeRemove[i] != null){
					afterRemove[j]=beforeRemove[i];
				}
			}
		}
		//Setting the new array of pics in the current album
		setPics(afterRemove);
	}
	
	
	
}
