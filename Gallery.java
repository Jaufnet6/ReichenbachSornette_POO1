import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

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
	
}
