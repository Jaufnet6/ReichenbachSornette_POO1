import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class DefaultGalleryFrame extends JFrame{

    private JTextField txtSearch;
    //Mac: /Users/black and white/Desktop/App/albums
    //Windows: C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\albums
    private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\albums";
    //Mac : /Users/black and white/Desktop/App/defaultPictures/album.png
    //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\defaultPictures\\album.png
    private Icon defaultAlbum = new ImageIcon("C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\defaultPictures\\album.png");
    //Mac : /Users/black and white/Desktop/App/gallery
    //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery
    private String imgFolder = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery";
    private JButton search;
    private JButton cancel;
    private JScrollPane scroll;
    private JScrollPane scrollEdit;
    private JScrollPane scrollSearch;
    private JPanel albumsPanel = new JPanel();
    private JPanel searchPanel;
    private JPanel allPicturesPanel;
    private JLabel lblBackground;
    private Album newAlbum;
    private Album currentAlbum;
    private String[] currentPics;



    public DefaultGalleryFrame() throws ClassNotFoundException, IOException {
        super("Albums");
        getContentPane().setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
        setResizable(false);
        getContentPane().setLayout(null);
        albumsPanel=loadAlbums();

        //Top part		
        JButton addAlbum = new JButton("New album");
        addAlbum.setFont(new Font("Avenir Next", Font.BOLD, 13));
        addAlbum.setForeground(Color.BLACK);
        addAlbum.addActionListener(new AddAlbumListener());
        addAlbum.setBackground(Color.LIGHT_GRAY);
        addAlbum.setBounds(5, 5, 100, 50);
        getContentPane().add(addAlbum);

        txtSearch = new JTextField();
        txtSearch.setFont(new Font("Avenir Next", Font.BOLD, 13));
        txtSearch.setForeground(new Color(102, 102, 102));
        txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
        txtSearch.setText("Search for an album...");
        txtSearch.setBounds(110, 5, 255, 50);
        getContentPane().add(txtSearch);
        txtSearch.setColumns(10);

        search = new JButton("Search");
        search.setFont(new Font("Avenir Next", Font.BOLD, 13));
        search.setBounds(370, 5, 100, 50);
        getContentPane().add(search);
        search.setBackground(Color.LIGHT_GRAY);
        search.setForeground(Color.BLACK);
        search.addActionListener(new SearchAlbumListener());

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Avenir Next", Font.BOLD, 13));
        cancel.setBounds(370, 5, 100, 50);
        getContentPane().add(cancel);
        cancel.setBackground(Color.LIGHT_GRAY);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(new CancelListener());

        //Middle part
        scroll = new JScrollPane(albumsPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0,60,480,616);
        getContentPane().add(scroll);

        //Bottom part
        JButton switchToPictures = new JButton("PICTURES");
        switchToPictures.setFont(new Font("Avenir Next", Font.BOLD, 13));
        switchToPictures.setForeground(Color.BLACK);
        switchToPictures.addActionListener(new PicturesButtonListener());
        switchToPictures.setBackground(Color.LIGHT_GRAY);
        switchToPictures.setBounds(0, 676, 240, 50);
        getContentPane().add(switchToPictures);

        JLabel albums = new JLabel("ALBUMS");
        albums.setFont(new Font("Avenir Next", Font.BOLD, 13));
        albums.setForeground(Color.WHITE);
        albums.setBackground(Color.DARK_GRAY);
        albums.setHorizontalAlignment(SwingConstants.CENTER);
        albums.setBounds(234, 676, 240, 50);
        getContentPane().add(albums);

        JButton homeButton = new JButton("HOME");
        homeButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
        homeButton.setForeground(Color.BLACK);
        homeButton.setBounds(0, 723, 480, 55);
        homeButton.addActionListener(new HomeListener());
        getContentPane().add(homeButton);

        //MAc : /Users/black and white/Desktop/App/Backgrounds/background.png
        //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\background.png
        lblBackground = new JLabel(new ImageIcon("C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\background.png"));
        lblBackground.setBounds(0, 0, 480, 778);
        getContentPane().add(lblBackground);

    }

    //Invisible if no research done
    class CancelListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            try{
                albumsPanel = loadAlbums();
                scrollSearch.setVisible(false);
                scroll = new JScrollPane(albumsPanel);
                scroll.setBounds(0,60,480,616);
                getContentPane().add(scroll);
                getContentPane().add(lblBackground);
                txtSearch.setText("Search for an album...");
                search.setVisible(true);
                cancel.setVisible(false);

            } catch(ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }           
        }
    }

    //Go back to homescreen
    class HomeListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            HomeScreen hs;
            try {
                hs = new HomeScreen();
                setVisible(false);
                hs.setVisible(true);
            } catch (ClassNotFoundException | InterruptedException | IOException e) {
                e.printStackTrace();
            }

        }

    }

    private JPanel loadSearchAlbum(String request) throws ClassNotFoundException, IOException{
        String albPath;
        File albFolder = new File(path);
        Album albums[] = new Album[albFolder.listFiles().length];
        Album albumsFound[] = new Album[albums.length];
        int cpt=0;
        int cptFound=0;

        //Search if each album contains the requested string and stock them if they do
        File[] files = new File(path).listFiles();
        for(File file : files){
            if(file.isFile()){
                albPath=file.getAbsolutePath();
                try {
                    albums[cpt] = readFile(albPath);
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
                if(albums[cpt].getName().toLowerCase().contains(request)){
                    albumsFound[cptFound] = albums[cpt];
                    cptFound++;
                }
            }
            cpt++;
        }

        //Creating the panel that displays the searched albums
        JPanel myPanel = new JPanel();
        JButton[] buttons = new JButton[cptFound];
        JLabel[] albumNames = new JLabel[cptFound];
        String[] albumIcons = new String[cptFound];
        Icon buttonImg;
        int x = 40;
        //Position for the first album
        int yAlb = 20;
        //Position for the first label
        int yLab = 200;
        int cptX = 0;

        //Filling the panel
        for (int i = 0; i < cptFound; i++) {
            albumIcons[i] = albumsFound[i].getHomePic();
            buttonImg = new ImageIcon(albumIcons[i]);
            buttons[i] = new JButton(buttonImg);
            buttons[i].setName(albumsFound[i].getName());
            buttons[i].addActionListener(new AlbumButtonListener());
            albumNames[i] = new JLabel(albumsFound[i].getName());
            albumNames[i].setHorizontalAlignment(SwingConstants.CENTER);
            albumNames[i].setFont(new Font("Avenir Next", Font.BOLD, 13));
            albumNames[i].setForeground(Color.DARK_GRAY);

            switch(cptX){
            case 0:
                buttons[i].setBounds(x,yAlb,175,175);
                albumNames[i].setBounds(x,yLab,175,20);
                myPanel.add(buttons[i]);
                myPanel.add(albumNames[i]);
                break;
            case 1:
                buttons[i].setBounds(x+199,yAlb,175,175);
                albumNames[i].setBounds(x+199,yLab,175,20);
                myPanel.add(buttons[i]);
                myPanel.add(albumNames[i]);
                break;
            }
            cptX++;
            if(cptX==2){
                cptX=0;
                yAlb+=220;
                yLab+=220;
            }
        }

        //If nothing found
        JLabel nothing = new JLabel("No album matching.");
        nothing.setHorizontalAlignment(SwingConstants.CENTER);
        nothing.setBounds(0,60,474,450);
        nothing.setFont(new Font("Avenir Next", Font.BOLD, 13));

        if(cptFound==0)
            myPanel.add(nothing);

        myPanel.setLayout(null);
        myPanel.setPreferredSize(new Dimension(450,yAlb+220));

        return myPanel;

    }

    class SearchAlbumListener implements ActionListener{

        String request;

        public void actionPerformed(ActionEvent arg0) {
            try {
                request = txtSearch.getText().toLowerCase();
                if(request.equals("search for an album...")){
                    return;
                }else {
                    searchPanel = loadSearchAlbum(request);
                    scroll.setVisible(false);;
                    scrollSearch = new JScrollPane(searchPanel);
                    scrollSearch.setBounds(0,60,480,616);
                    getContentPane().add(scrollSearch);
                    getContentPane().add(lblBackground);
                    search.setVisible(false);
                    cancel.setVisible(true);
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();			
            }
        }

    }

    class AddAlbumListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            AlbumCreationFrame acf;
            try {
                acf = new AlbumCreationFrame();
                acf.setVisible(true);
                setVisible(false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    //Open PicturesFrame
    class PicturesButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            PicturesFrame picFrame;
            try {
                picFrame = new PicturesFrame();
                picFrame.setVisible(true);
                setVisible(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Clicking on an album display its pictures
    class AlbumButtonListener implements ActionListener{

        String albName;

        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            String name = button.getName();
            File albFolder = new File(path);
            Album[] albums = new Album[albFolder.listFiles().length];
            String albPath;
            int cpt=0;

            //Searching for the album user clicked
            File[] files = new File(path).listFiles();
            for(File file : files){
                if(file.isFile()){
                    albPath=file.getAbsolutePath();
                    try {
                        albums[cpt] = readFile(albPath);
                    } catch (ClassNotFoundException | IOException e1) {
                        e1.printStackTrace();
                    }
                    if(albums[cpt].getName().equals(name)){
                        currentAlbum = albums[cpt];
                        albName = currentAlbum.getName();
                        currentPics = new String[currentAlbum.getPics().length];                      
                        break;
                    }
                }
                cpt++;
            }

            //Getting my album images
            Icon[] albumImages = new ImageIcon[currentAlbum.getPics().length];
            for (int i = 0; i < currentAlbum.getPics().length; i++) {
                albumImages[i] = new ImageIcon(currentAlbum.getPics()[i]);
                currentPics[i] = currentAlbum.getPics()[i];
            }

            //New Frame, which is displaying my pics
            JFrame albumFrame = new JFrame();

            JLabel albumName = new JLabel();
            albumName.setText(currentAlbum.getName());
            albumName.setFont(new Font("Avenir Next", Font.BOLD | Font.ITALIC, 20));
            albumName.setForeground(Color.WHITE);
            albumName.setHorizontalAlignment(SwingConstants.CENTER);
            albumName.setBounds(97, 8, 280, 50);

            JTextField albumNameTxt = new JTextField();
            albumNameTxt.setText(currentAlbum.getName());
            albumNameTxt.setFont(new Font("Avenir Next", Font.BOLD | Font.ITALIC, 20));
            albumNameTxt.setForeground(Color.BLACK);
            albumNameTxt.setHorizontalAlignment(SwingConstants.CENTER);
            albumNameTxt.setBounds(97, 8, 280, 50);
            albumNameTxt.setVisible(false);

            //Filling the panel
            JPanel picturesPanel = new JPanel();
            try {
                picturesPanel = loadAlbumPics(albumImages);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
                                    
            JScrollPane scroll = new JScrollPane(picturesPanel);
            
            JButton edit = new JButton("EDIT");
            edit.setBackground(Color.LIGHT_GRAY);
            edit.setForeground(Color.BLACK);
            edit.setFont(new Font("Avenir Next", Font.BOLD, 13));
            edit.setBounds(370, 5, 100, 50);

            JButton cancel = new JButton("CANCEL");
            cancel.setForeground(Color.DARK_GRAY);
            cancel.setFont(new Font("Avenir Next", Font.BOLD, 13));
            cancel.setBounds(5, 5, 100, 50);
            cancel.setVisible(false);

            JLabel needTxt = new JLabel("Error : please enter a name.");
            needTxt.setForeground(Color.RED);
            needTxt.setFont(new Font("Avenir Next", Font.BOLD, 13));
            needTxt.setBounds(130, 30, 260, 50);
            needTxt.setVisible(false);

            //Go back to DefaultGalleryFrame
            JButton back = new JButton("BACK");
            back.setForeground(Color.DARK_GRAY);
            back.setFont(new Font("Avenir Next", Font.BOLD, 13));
            back.setBounds(5, 5, 100, 50);
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    albumFrame.setVisible(false);
                    setVisible(true);
                    getContentPane().add(lblBackground);
                }
            });

            //Go back to homescreen
            JButton homeButton = new JButton("HOME");
            homeButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
            homeButton.setForeground(Color.BLACK);
            homeButton.setBounds(0, 723, 480, 55);
            homeButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    HomeScreen hs;
                    try {
                        hs = new HomeScreen();
                        albumFrame.setVisible(false);
                        hs.setVisible(true);
                    } catch (ClassNotFoundException | InterruptedException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            //Deleting the current album
            JButton delete = new JButton("DELETE");
            delete.setBackground(Color.LIGHT_GRAY);
            delete.setForeground(Color.BLACK);
            delete.setFont(new Font("Avenir Next", Font.BOLD, 13));
            delete.setBounds(370, 5, 100, 50);
            delete.setVisible(false);
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    File file = new File(path+"\\"+albName+".txt");
                    try {					
                        //Confirmation
                        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete \""+albName+"\" ?");
                        if(option==JOptionPane.YES_OPTION){
                            file.delete();
                            //Opening a new Gallery frame after deleting the album
                            DefaultGalleryFrame dgf = new DefaultGalleryFrame();
                            albumFrame.setVisible(false);
                            dgf.setVisible(true);
                        }
                    } catch (ClassNotFoundException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            //Save changes
            JButton save = new JButton("SAVE");
            save.setFont(new Font("Avenir Next", Font.BOLD, 13));
            save.setForeground(Color.BLACK);
            save.setBackground(Color.LIGHT_GRAY);
            save.setBounds(0, 676, 480, 50);
            save.setVisible(false);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    File file = new File(path+"\\"+currentAlbum.getName()+".txt");      
                    if(!albumNameTxt.getText().equals("")){
                        albumNameTxt.setVisible(false);
                        albumName.setVisible(true);
                        delete.setVisible(false);
                        edit.setVisible(true);
                        cancel.setVisible(false);
                        back.setVisible(true);
                        save.setVisible(false);
                        needTxt.setVisible(false);
                        albumName.setText(albumNameTxt.getText());
                        //Delete old album
                        file.delete();
                        newAlbum.renameAlbum(albumNameTxt.getText());
                        newAlbum.setPics(currentPics);
                        if(currentPics.length==0)
                        	newAlbum.setHomePic(newAlbum.defaultAlbumPic);
                        else
                        	newAlbum.setHomePic(currentPics[0]);              	
                        try {
                            saveInFile(newAlbum);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        DefaultGalleryFrame dgf;
                        try {
                            dgf = new DefaultGalleryFrame();
                            albumFrame.setVisible(false);
                            dgf.setVisible(true);
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                     
                    }
                    else{
                        needTxt.setVisible(true);
                    }

                }

            });

            //Go back to albumframe
            cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    albumNameTxt.setVisible(false);
                    albumName.setVisible(true);
                    delete.setVisible(false);
                    edit.setVisible(true);
                    cancel.setVisible(false);
                    back.setVisible(true);
                    save.setVisible(false);
                    needTxt.setVisible(false);
                    scrollEdit.setVisible(false);
                    scroll.setVisible(true);
                }
            });

            //Editing current album
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	newAlbum = new Album(currentAlbum.getName());
                	JPanel allPicsPanel = new JPanel();
                    albumNameTxt.setVisible(true);
                    albumName.setVisible(false);
                    delete.setVisible(true);
                    edit.setVisible(false);
                    cancel.setVisible(true);
                    back.setVisible(false);
                    save.setVisible(true);
                    try {
                        allPicsPanel = loadAllPics();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    scrollEdit = new JScrollPane(allPicsPanel);
                    scrollEdit.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollEdit.setBounds (0,60,480,617);
                    albumFrame.getContentPane().add(scrollEdit);
                    albumFrame.getContentPane().add(lblBackground);
                    scroll.setVisible(false);
                    scrollEdit.setVisible(true);
                }
                
                //Load pictures from Gallery folder
                private JPanel loadAllPics() throws IOException{
                    JPanel myPanel = new JPanel();
                    myPanel.setBackground(Color.WHITE);
                    File picFolder = new File(imgFolder);
                    Icon[] images = new ImageIcon[picFolder.listFiles().length];
                    JButton[] buttons = new JButton[picFolder.listFiles().length];
                    String imgPath;

                    //Position for the first image
                    int x=22;
                    int y=20;
                    int cptX=0;

                    //Loading pictures
                    for(int i=0;i<images.length;i++){
                        imgPath=imgFolder+"\\"+Integer.toString(i)+".jpg";
                        images[i] = new ImageIcon(imgPath);
                        buttons[i] = new JButton(images[i]);
                        buttons[i].setName(imgPath);
                        buttons[i].addActionListener(new AddPicsToAlbum());

                        switch (cptX){
                        case 0:
                            buttons[i].setBounds(x, y, 125, 125);
                            myPanel.add(buttons[i]);
                            break;
                        case 1:
                            buttons[i].setBounds(x+143, y, 125, 125);
                            myPanel.add(buttons[i]);
                            break;
                        case 2:
                            buttons[i].setBounds(x+287, y, 125, 125);
                            myPanel.add(buttons[i]);
                            break;
                        }
                        cptX++;
                        if(cptX==3){
                            cptX=0;
                            y += 143;
                        }
                    }
                    myPanel.setLayout(null);
                    //y+145 => y(total y size)+125(img size)+20(initial border)
                    myPanel.setPreferredSize(new Dimension(450,y+145));
                    return myPanel;
                }
                
                class AddPicsToAlbum implements ActionListener{

                    public void actionPerformed(ActionEvent e) {
                        //Copy the clicked button
                        JButton button = (JButton) e.getSource();
                        //Stock pictures paths in an array
                        String[] newAddedPics = new String[currentPics.length+1];
                        String[] newDeletedPics = null;
                        String img = button.getName();
                        int cpt=0;
                        boolean del=false;
                        
                        //If pic is already in the album
                        for(int i=0; i<currentPics.length; i++){
                        	
                            if(currentPics[i].equals(img)){
                                int yes = JOptionPane.showConfirmDialog(null, "This picture is already in the album. Do you want to delete it ?");
                                if(yes == JOptionPane.YES_OPTION){
                                    newDeletedPics = new String[currentPics.length-1];
                                    for(int j=0; j<currentPics.length; j++){                                        
                                        if(j!=i){
                                            newDeletedPics[cpt]=currentPics[j];
                                            cpt++;
                                        }
                                    }  
                                    del=true;
                                    break;
                                }           
                            }
                        }
                        
                        //For new pictures
                        for(int i=0;i<currentPics.length;i++){
                            newAddedPics[i]=currentPics[i];
                        }
                        newAddedPics[currentPics.length] = img;
                                     
                        if(!del){
                            currentPics=newAddedPics;
                            button.setEnabled(false);
                        }
                        else
                            currentPics=newDeletedPics;
                                               
                    }
                }
            });

            albumFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            albumFrame.setSize(480, 800);
            albumFrame.setResizable(false);	
            albumFrame.getContentPane().setLayout(null);
            albumFrame.getContentPane().setBackground(Color.DARK_GRAY);
            albumFrame.getContentPane().add(back);
            albumFrame.getContentPane().add(cancel);
            albumFrame.getContentPane().add(edit);
            albumFrame.getContentPane().add(delete);
            albumFrame.getContentPane().add(albumName);
            albumFrame.getContentPane().add(albumNameTxt);
            albumFrame.getContentPane().add(save);
            albumFrame.getContentPane().add(needTxt);
            albumFrame.getContentPane().add(homeButton);

            picturesPanel.setBackground(Color.WHITE);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setBounds (0,60,480,663);           
            albumFrame.getContentPane().add(scroll);
            albumFrame.getContentPane().add(lblBackground);
            albumFrame.setVisible(true);
            setVisible(false);

        }
    }

    //Load pictures inside an album
    private JPanel loadAlbumPics(Icon[] a) throws IOException{
        JPanel myPanel = new JPanel();
        JLabel[] labels = new JLabel[a.length];

        int x=22;
        int y=20;
        int cptX=0;

        for(int i=0; i<a.length;i++){
            labels[i] = new JLabel(a[i]);

            switch (cptX){
            case 0:
                labels[i].setBounds(x, y, 125, 125);
                myPanel.add(labels[i]);
                break;
            case 1:
                labels[i].setBounds(x+143, y, 125, 125);
                myPanel.add(labels[i]);
                break;
            case 2:
                labels[i].setBounds(x+287, y, 125, 125);
                myPanel.add(labels[i]);
                break;
            }
            cptX++;
            if(cptX==3){
                cptX=0;
                y+=143;
            }
        }
        myPanel.setLayout(null);
        myPanel.setPreferredSize(new Dimension(450,y+200));
        return myPanel;
    }

    //Loading albums already created
    private JPanel loadAlbums() throws ClassNotFoundException, IOException{
        JPanel myPanel = new JPanel();
        myPanel.setBackground(Color.WHITE);
        File albFolder = new File(path);
        Album[] albums = new Album[albFolder.listFiles().length];
        JButton[] buttons = new JButton[albFolder.listFiles().length];
        JLabel[] albumNames = new JLabel[albFolder.listFiles().length];
        String[] albumIcons = new String[albFolder.listFiles().length];
        String albPath;
        Icon buttonImg;
        int cpt = 0;
        int x = 40;
        //Position for the first album
        int yAlb = 20;
        //Position for the first label
        int yLab = 200;
        int cptX = 0;

        File[] files = new File(path).listFiles();
        //Filling the panel
        for(File file : files){
            if(file.isFile()){
                albPath=file.getAbsolutePath();
                albums[cpt] = readFile(albPath);
                albumIcons[cpt] = albums[cpt].getHomePic();
                buttonImg = new ImageIcon(albumIcons[cpt]);
                buttons[cpt] = new JButton(buttonImg);
                buttons[cpt].setName(albums[cpt].getName());
                buttons[cpt].addActionListener(new AlbumButtonListener());
                albumNames[cpt] = new JLabel(albums[cpt].getName());
                albumNames[cpt].setFont(new Font("Avenir Next", Font.BOLD, 13));
                albumNames[cpt].setHorizontalAlignment(SwingConstants.CENTER);

                switch(cptX){
                case 0:
                    buttons[cpt].setBounds(x,yAlb,175,175);
                    albumNames[cpt].setBounds(x,yLab,175,20);
                    myPanel.add(buttons[cpt]);
                    myPanel.add(albumNames[cpt]);
                    break;
                case 1:
                    buttons[cpt].setBounds(x+199,yAlb,175,175);
                    albumNames[cpt].setBounds(x+199,yLab,175,20);
                    myPanel.add(buttons[cpt]);
                    myPanel.add(albumNames[cpt]);
                    break;
                }
                cptX++;
                if(cptX==2){
                    cptX=0;
                    yAlb+=220;
                    yLab+=220;
                }
                cpt++;
            }
        }
        myPanel.setLayout(null);
        //to display without too much space at the of the panel
        if(cptX == 0) 
            yAlb -= 220;
        myPanel.setPreferredSize(new Dimension(450,yAlb+220));

        return myPanel;
    }

    //Read an already saved album
    private Album readFile(String path) throws ClassNotFoundException, IOException{  
        Album alb;
        FileInputStream fichier = new FileInputStream(path);
        BufferedInputStream bfichier = new BufferedInputStream(fichier);
        ObjectInputStream obfichier = new ObjectInputStream(bfichier);
        alb = (Album) obfichier.readObject();
        obfichier.close();
        return alb;
    }

    private void saveInFile(Album album) throws IOException{ 

        FileOutputStream fos = new FileOutputStream(path+"\\"+album.getName()+".txt");  
        BufferedOutputStream bfos = new BufferedOutputStream(fos);
        ObjectOutputStream obfos = new ObjectOutputStream(bfos);
        obfos.writeObject(album);
        obfos.close();

    }

}
