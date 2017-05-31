import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Album implements Serializable{

	private final String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Gallery";
	private final String homePicPath = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\defaultPictures\\album.png";
	
	
	private String name;
	private String[] pics = new String[0];
	//Picture which is displayed on gallery to represent the album
	//is the first picture added OR a default picture if album still is empty
	private String homePic = new String(homePicPath);
	
	public Album(){
		//Default name
		this.name="New album ";
	}
	
	public Album(String name) {
		if(name==""){
			this.name="New album";
		} else {
			this.name = name;
		}
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
	
	public String getHomePic() {
		return homePic;
	}

	public void setHomePic(String homePic) {
		this.homePic = homePic;
	}
		
}
