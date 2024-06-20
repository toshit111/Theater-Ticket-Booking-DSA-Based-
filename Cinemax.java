import java.util.Scanner;

class Node {
    int col;
    int status;
    Node next, prev;

    Node(int col) {
        this.col = col;
        this.status = 0;
        this.next = null;
        this.prev = null;
    }
}

public class Cinemax {
    Node[] head = new Node[11]; // Array of head pointers for 10 rows

    Cinemax() {
        for (int i = 1; i <= 10; i++) {
            head[i] = null;
            Node newNode;
            for (int j = 7; j >= 1; j--) { // Reverse loop to maintain order similar to C++ version
                newNode = create(j);

                if (head[i] == null) {
                    head[i] = newNode;
                    head[i].next = head[i].prev = newNode;
                } else {
                    newNode.next = head[i];
                    newNode.prev = head[i].prev;
                    head[i].prev.next = newNode;
                    head[i].prev = newNode;
                }
            }
        }
    }

    private Node create(int j) {
        return new Node(j);
    }

    void book() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row number: ");
        int x = scanner.nextInt();
        System.out.print("Enter column number: ");
        int y = scanner.nextInt();

        if (x < 1 || x > 10) {
            System.out.println("Enter correct row number.");
            return;
        }
        if (y < 1 || y > 7) {
            System.out.println("Enter correct column number.");
            return;
        }

        Node temp = head[x];
        for (int i = 1; i <= 7; i++) {
            if (temp.col == y) {
                if (temp.status == 0) {
                    temp.status = 1;
                    System.out.println("\nSeat Booked Successfully...");
                    return;
                } else {
                    System.out.println("\nSorry!! Already Booked!!");
                    return;
                }
            }
            temp = temp.next;
        }
    }

    void cancel() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row number: ");
        int x = scanner.nextInt();
        System.out.print("Enter column number: ");
        int y = scanner.nextInt();

        if (x < 1 || x > 10) {
            System.out.println("Enter correct row number.");
            return;
        }
        if (y < 1 || y > 7) {
            System.out.println("Enter correct column number.");
            return;
        }

        Node temp = head[x];
        for (int i = 1; i <= 7; i++) {
            if (temp.col == y) {
                if (temp.status == 1) {
                    temp.status = 0;
                    System.out.println("Booking Cancelled Successfully...");
                    return;
                } else {
                    System.out.println("\nSorry!! Already Unbooked!!");
                    return;
                }
            }
            temp = temp.next;
        }
    }

    void display() {
        System.out.println("\n\t\t_____________________________");
        System.out.println("\t\t\tSCREEN THIS WAY");
        System.out.println("\t\t_____________________________\n");

        for (int i = 1; i <= 10; i++) {
            Node temp = head[i];
            for (int j = 1; j <= 7; j++) {
                if (temp.status == 0)
                    System.out.print(temp.col + ".|___| ");
                else
                    System.out.print(temp.col + ".|_B_| ");
                temp = temp.next;
            }
            System.out.println("\n\n");
        }
    }

    void avail() {
        System.out.println("\n\t\t_____________________________");
        System.out.println("\t\t\tSCREEN THIS WAY");
        System.out.println("\t\t_____________________________\n");

        for (int i = 1; i <= 10; i++) {
            Node temp = head[i];
            for (int j = 1; j <= 7; j++) {
                if (temp.status == 0)
                    System.out.print(temp.col + ".|___| ");
                else
                    System.out.print(temp.col + ". ");
                temp = temp.next;
            }
            System.out.println("\n\n");
        }
    }

    public static void main(String[] args) {
        Cinemax c = new Cinemax();
        Scanner scanner = new Scanner(System.in);
        int ch;

        c.display();

        while (true) {
            System.out.println("\n**************************************************************");
            System.out.println("\t\t CINEMAX MOVIE THEATRE");
            System.out.println("**************************************************************");
            System.out.println("Press <1> Current Seat Status");
            System.out.println("Press <2> Book Seat");
            System.out.println("Press <3> Cancel Booking");
            System.out.println("Press <4> Available Seat");
            System.out.println("Press <5> Exit");
            System.out.print("Enter your choice: ");
            ch = scanner.nextInt();

            switch (ch) {
                case 1:
                    c.display();
                    break;
                case 2:
                    c.book();
                    break;
                case 3:
                    c.cancel();
                    break;
                case 4:
                    c.avail();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

