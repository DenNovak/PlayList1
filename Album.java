import java.util.ArrayList;

public class Album {

    private String albumName;
    private ArrayList<Song> album;

    public Album(String albumName) {
        this.albumName = albumName;
        this.album = new ArrayList<Song>();
    }

    public String getAlbumName() {
        return albumName;
    }

    public ArrayList<Song> getAlbum() {
        return album;
    }

    public static Album createAlbum(String name) {
        return new Album(name);
    }

    public void printSongs() {
        System.out.println("Songs list");
        for(int i = 0; i < this.album.size(); i++) {
            System.out.println((i+1) + "." + this.album.get(i).getTitle() + "-> Duration: " + this.album.get(i).getDuration());
        }
    }


 }
