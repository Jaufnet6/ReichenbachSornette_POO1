
public class Gallery {

	private String path;
	private Album[] albums;
	private Picture[] pics;	
	
	public Gallery(Album[] albums, Picture[] pics){
		this.albums=albums;
		this.pics=pics;
	}

	public Album[] getAlbums() {
		return albums;
	}

	public void setAlbums(Album[] albums) {
		this.albums = albums;
	}

	public Picture[] getPics() {
		return pics;
	}

	public void setPics(Picture[] pics) {
		this.pics = pics;
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
