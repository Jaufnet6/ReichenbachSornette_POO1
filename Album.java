import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Album implements Serializable{

	private final String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Gallery";
	private final String homePicPath = path+"\\defaultPicture\\album.png";
	
	
	private String name;
	private String[] pics = new String[0];
	//Picture which is displayed on gallery to represent the album
	//is the first picture added OR a default picture if album still is empty
	private String homePic = new String(homePicPath);
	
	public Album(){
		//Default name
		this.name="New album ";
//		for (int i = 0; i < pics.length; i++) {
//			this.pics[i]="C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\defaultPictures\\albumPic.png";
//		}
	}
	
	public Album(String name) {
		if(name==""){
			this.name="New album";
		} else {
			this.name = name;
		}
//		for (int i = 0; i < pics.length; i++) {
//			this.pics[i]="C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\defaultPictures\\albumPic.png";
//		}
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
	}
	
	public int getSize(){
		return pics.length;
	}
	
	
//	public void addPic(Icon newPic){
//		//Create a new array of pics with 1 more slot
//		Icon newPics[] = new Icon[getSize()+1];
//		//New picture is added at the end of the new array
//		newPics[newPics.length]=newPic;
//		//New array is replacing the previous one
//		setPics(newPics);
//	}

	public String getHomePic() {
		return homePic;
	}

	public void setHomePic(String homePic) {
		this.homePic = homePic;
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
