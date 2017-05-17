import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Gallery {

	private String folderPath = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Gallery";
	private Album[] albums;
	private String[] picsPath;
	
	public Gallery(Album[] albums, String[] picsPath) throws IOException{
		this.albums=albums;
		this.picsPath=loadPics();
	}

	public Album[] getAlbums() {
		return albums;
	}

	public void setAlbums(Album[] albums) {
		this.albums = albums;
	}

	public String[] getPics() {
		return picsPath;
	}
	
//	public String getPicName() throws IOException{
//		
//	}

	public void setPics(String[] picsPath) {
		this.picsPath = picsPath;
	}
	
	private String[] loadPics() throws IOException{
		FileReader fr = new FileReader(folderPath);
		BufferedReader bfr = new BufferedReader(fr);
		File picFolder = new File(folderPath);
		
		for(int i=0;i<picFolder.length();i++){
			picsPath[i]=bfr.readLine();
		}
		return picsPath;
	}
	
	public void addAlbum(String newAlbumName){
		Album newAlbum;
		//if user types nothing : default name
		if(newAlbumName==""){
			newAlbum = new Album("New album");
		} else {
			newAlbum = new Album(newAlbumName);
		}
		//Create a new array of albums with 1 more slot
		Album newAlbums[] = new Album[getAlbums().length+1];
		//New album is added at the end of the new array
		newAlbums[newAlbums.length]=newAlbum;
		//New array is replacing the previous one
		setAlbums(newAlbums);
	}
	
	public void removeAlbum(Album deletedAlbum){
		Album[] beforeRemove = getAlbums();
		Album[] afterRemove = new Album[beforeRemove.length-1];
		//Searching the album to remove
		for(int i=0; i<beforeRemove.length; i++){
			if(beforeRemove[i].getID() == deletedAlbum.getID()){
				beforeRemove[i]=null;
			}	
		}
		//Creating the new gallery without the deleted album
		for(int i=0; i<beforeRemove.length; i++){
			for(int j=0; j<afterRemove.length; j++){
				if(beforeRemove[i] != null){
					afterRemove[j]=beforeRemove[i];
				}
			}
		}
		//Setting the new array of album in the current gallery
		setAlbums(afterRemove);
	}
	
}
