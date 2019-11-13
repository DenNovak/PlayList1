import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
public class Main {


    private static ArrayList<Album> albums = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        LinkedList<Song> playList = new LinkedList<>();

        Album album = new Album("Pink Ployed");
        album.getAlbum().add(new Song("Party", "3:24"));
        album.getAlbum().add(new Song("privet", "4:14"));
        album.getAlbum().add(new Song("Rap", "2:14"));
        album.getAlbum().add(new Song("Hello", "3:47"));
        albums.add(album);

        Album senocdAlbum = new Album("A better man");
        senocdAlbum.getAlbum().add(new Song("Alive", "6:45"));
        senocdAlbum.getAlbum().add(new Song("Again", "5:21"));
        senocdAlbum.getAlbum().add(new Song("Past", "3:25"));
        senocdAlbum.getAlbum().add(new Song("Goodbye", "4:32"));
        albums.add(senocdAlbum);
        Album thirdAlbum = new Album("Rap God");
        thirdAlbum.getAlbum().add(new Song("Stan", "3:43"));
        thirdAlbum.getAlbum().add(new Song("Paradise", "4:12"));
        thirdAlbum.getAlbum().add(new Song("Hihihi", "3:24"));
        thirdAlbum.getAlbum().add(new Song("Sea", "4:56"));
        albums.add(thirdAlbum);

        printBasicMenu();

        boolean quit = false;
        boolean goingForward = true;
        while (!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    listAlbums();
                    break;
                case 2:
                    System.out.println("Enter the song name you want to add");
                    String title = scanner.next();
                    scanner.nextLine();
                    if (findAlbumBySong(title) != null) {
                        addSong(playList, findAlbumBySong(title), title);
                    }
                    break;
                case 4:
                    printBasicMenu();
                    break;
                case 3:
                    listPlayList(playList);
                    break;
            }
        }

        ListIterator<Song> iterator = playList.listIterator();
        printListMenu();
        while (quit) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    quit = false;
                    break;
                case 1:
                    if(!goingForward) {
                        if(iterator.hasNext()) {
                            iterator.next();
                        }
                        goingForward = true;
                    }
                    if (iterator.hasNext()) {
                        System.out.println("Now Playing: " + iterator.next().getTitle());
                    } else {
                        System.out.println("Cannot go farther.");
                    }
                    break;
                case 2:
                    if(goingForward) {
                        if(iterator.hasPrevious()) {
                            iterator.previous();
                        }
                        goingForward = false;
                    }
                    if (iterator.hasPrevious()) {
                        System.out.println("Now playing: " + iterator.previous().getTitle());
                    } else {
                        System.out.println("Cannot go backwords anymore");
                    }
                    break;
                case 3:
                    if(iterator.hasPrevious()) {
                        System.out.println("Replaying: " + iterator.previous().getTitle());
                    }
                    break;
                    case 4:
                    listPlayList(playList);
                    break;
                case 5:
                    System.out.println("Removing...");
                    iterator.remove();
                    break;
                case 6:
                    printListMenu();
                    break;

            }
        }

    }




    private static boolean songInList(LinkedList<Song> linkedList,String songName) {
        ListIterator<Song> listIterator = linkedList.listIterator();

        if(linkedList.isEmpty()) {
            System.out.println("No songs in the Playlist");
        } else {
                for(int i =0 ; i < linkedList.size(); i++) {
                    if(listIterator.next().getTitle().equals(songName)) {
                        System.out.println("The song is already added.");
                        return true;
                    }
                    }
            System.out.println("No such song was found in your list");
            }
        return false;
        }


    private static int songExists(Album album, String songName) {
        for (int i = 0; i < album.getAlbum().size(); i++) {
            if (album.getAlbum().get(i).getTitle().equals(songName)) {
                System.out.println("The song exists.");
                return album.getAlbum().indexOf(album.getAlbum().get(i));
            }
        }
        System.out.println("The song was not found");
        return -1;
    }

    private static void addSong(LinkedList<Song> linkedList,Album album, String songName) {

        if((!songInList(linkedList, songName))) {
            linkedList.add(album.getAlbum().get(songExists(album, songName)));
            System.out.println("Song was added");
        } else {
            System.out.println("Something went wrong...");
        }
    }

    private static void listPlayList(LinkedList<Song> linkedList) {
        ListIterator<Song> listIterator = linkedList.listIterator();
        if(linkedList.isEmpty()) {
            System.out.println("There are no songs in your list yet");
        } else {
            while (listIterator.hasNext()) {
                Song newSong = listIterator.next();
                System.out.println("Song: " + newSong.getTitle() +  " --> " + newSong.getDuration());
            }
        }
    }

        private static void printBasicMenu() {
            System.out.println("Available actions: \nPress: ");
            System.out.println("0 - to Move on to playlist.\n" +
                    "1 - To see all available albums.\n" +
                    "2 - To add a song to playlist\n" +
                    "3 - To see your playlist\n" +
                    "4 - print menu options.\n");
        }

        private static void printListMenu() {
            System.out.println("Available actions: \nPress: ");
            System.out.println("0 - to quit.\n" +
                                "1 - skip forward to next song.\n" +
                                "2 - skip backwards to previous song.\n" +
                                "3 - replay the current song.\n" +
                                "4 - List the songs in the playList.\n"
                               +"5 - remove the current song from playlist.\n" +
                                "6 - print menu options.\n");
        }

        private static void listAlbums() {
            System.out.println("Albums: ");
            for(int i = 0; i < albums.size(); i++) {
                System.out.println((i+1) + "." + albums.get(i).getAlbumName());
                for(int j=0; j < albums.get(i).getAlbum().size(); j++) {
                    System.out.println("Song number " + (j+1) + "." + albums.get(i).getAlbum().get(j).getTitle());
                }
            }
        }

        private static Album findAlbumBySong(String songName) {
            for (Album album : albums) {
                for (int j = 0; j < album.getAlbum().size(); j++) {
                    if (album.getAlbum().get(j).getTitle().equals(songName)) {
                        return album;
                    }
                }
            }
            System.out.println("No such song was found");
        return null;
        }

}