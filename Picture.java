
public class Picture {

	private int IDpic;
	private String path;
	
	public Picture(int iDpic, String path) {
		super();
		IDpic = iDpic;
		this.path = path;
	}
	
	public int getID() {
		return IDpic;
	}
	public void setID(int iD) {
		IDpic = iD;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}

class defaultAlbumPic extends Picture {
	
	public defaultAlbumPic(){
		super(0, "path to folder");
	}
}

class defaultContactPic extends Picture {
	
	public defaultContactPic(){
		super(1, "path to folder");
	}
}