

package hotelprogram;


import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Rahim
 */
public class HotelProgram {

    private static final boolean MainMenu = true;
    private static final boolean SubMenu = true;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Room[] myHotel = new Room[10];
        myHotel[0] = new Room();
        myHotel[1] = new Room();
        myHotel[2] = new Room();
        myHotel[3] = new Room();
        myHotel[4] = new Room();
        myHotel[5] = new Room();
        myHotel[6] = new Room();
        myHotel[7] = new Room();
        myHotel[8] = new Room();
        myHotel[9] = new Room();
        int roomNum = 0;
        
                System.out.println("************************************");
                System.out.println("Hello and Welcome to our Hotel GOSO");
                System.out.println("\n*************************************");
        initialise(myHotel);
        while (MainMenu) {
            while (SubMenu) {
                
                
                System.out.println("*************************************");
                System.out.println("N: Book A New Room.");
                System.out.println("E: Display Empty Rooms.");
                System.out.println("V: View all Rooms.");
                System.out.println("D: Delete customer from room.");
                System.out.println("F: Find room from customer name.");
                System.out.println("X: Exit Program.");
                System.out.println("*************************************");
                System.out.print("\nPlease select the letter you want to go to:  ");
                String Selection = input.next();
                Selection = Selection.toUpperCase();
                
                switch (Selection) {
                    
                    case "N", "n" -> BookARoom(myHotel, roomNum);
                    case "E", "e" -> CheckIfEmpty(myHotel);
                    case "V", "v" -> ViewAllRooms(myHotel);
                    case "D", "d" -> DeleteCustomerFromRoom(myHotel, roomNum);
                    case "F", "f" -> FindRoomFromCustomerName(myHotel);
                    case "X", "x" -> System.exit(0);
                    default -> System.out.println("Invalid Selection");
                }
                
               
                
            }}
    }

    private static void initialise(Room[] myHotel) {
        for (Room myHotel1 : myHotel) {
            myHotel1.setName("nobody");
        }
    }

    private static void CheckIfEmpty(Room[] myHotel) {
        for (int x = 0; x < myHotel.length; x++) {
            if (myHotel[x].getName().equals("nobody")) {
                System.out.println("room " + (x + 1) + " is empty");
            }
        }
    }

    private static void BookARoom(Room[] myHotel, int roomNum) {
        String roomName;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter room number you want to reserve (1-10):");
        roomNum = input.nextInt() - 1;
        System.out.println("Enter your name for identification of the room " + (roomNum + 1) + " :");
        roomName = input.next();
        myHotel[roomNum].setName(roomName);
        System.out.println("***************************");
        System.out.println("Successfully Reserved Room!");
        System.out.println("***************************");
    }

    private static void ViewAllRooms(Room[] myHotel) {
        for (int x = 0; x < myHotel.length; x++) {
            System.out.println("room " + (x + 1) + " occupied by " + myHotel[x].getName());
        }
    }

    private static void DeleteCustomerFromRoom(Room[] myHotel, int roomNum) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your room number to delete/cancel reservation (1-10):");
        roomNum = input.nextInt() - 1;
        myHotel[roomNum].setName("nobody");
        System.out.println("***************************");
        System.out.println("Successfully Deleted!:)");
        System.out.println("***************************");
    }

    private static void FindRoomFromCustomerName(Room[] myHotel) {
        Scanner input = new Scanner(System.in);
        String roomName;
        System.out.println("Enter name to Search for:");
        roomName = input.next();
        int x;
        boolean Checker = false;
        for (x = 0; x < myHotel.length; x++) {
            if (roomName.equals(myHotel[x].getName())) {
                System.out.println("The Account That Matches That name is Account number " + x);
                Checker = true;
            }
        }
        if (Checker == false) {
            System.out.println("There are no Rooms Booked with that name\n(make sure you've used the correct Construction of the word)");
        }
    }

    public static class Room {

        //protected String mainName;
        private String mainName;

        public Room() {
            mainName = "k";

        }

        public void setName(String aName) {
            //  System.out.println("add name class method ");
            mainName = aName;
        }

        public String getName() {
            return mainName;
        }
    }
}