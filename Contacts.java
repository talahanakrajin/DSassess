import java.util.Scanner;


public class Contacts{
    private String name;
    private String phoneNum;
    private String email;
    ContactBook head;
    ContactBook tail;

    static class ContactBook{
        Contacts contact;
        ContactBook next;
        ContactBook(Contacts contact){
            this.contact = contact;
            this.next = null;
        }
    }
    
    public Contacts(){
        this.name = "";
        this.phoneNum = "";
        this.email = "";
    }

    void addContact(String name,String phoneNum, String email){
        Contacts contact = new Contacts();
        contact.name = name;
        contact.phoneNum = phoneNum;
        contact.email = email;
        
        ContactBook newNode = new ContactBook(contact);
        if(head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
       
    }

    void removeContact(String contactName) {
        ContactBook temp = head;
        ContactBook prev = null;
        if (temp != null && temp.contact.name.equals(contactName)) {
            head = temp.next;
            if (head == null) {
                tail = null; // Update tail if the list becomes empty
            }
            return;
        }
        while (temp != null && !temp.contact.name.equals(contactName)) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            return;
        }
        prev.next = temp.next;
        if (prev.next == null) {
            tail = prev; // Update tail if the last node is removed
        }
    }

    public String searchEmail(String name){
        ContactBook temp = head;
        while(temp != null){
            if(temp.contact.name.equals(name)){
                return temp.contact.email;
            }
            temp = temp.next;
        }
        return "Contact not found";

    }

    public String printList(){
        ContactBook temp = head;
        String result = "";
        if (temp == null){
            return "List is empty";
        }
        while(temp != null){
            result += "[Name: " + temp.contact.name + " Phone Number: " + temp.contact.phoneNum + " Email: " + temp.contact.email + "]\n";
            temp = temp.next;
        }
        return result;
    }

    public String searchCont(String contactName){
        ContactBook temp = head;
        while(temp != null){
            if(temp.contact.name.equals(contactName)){
                return "[Name: " + temp.contact.name + " Phone Number: " + temp.contact.phoneNum + " Email: " + temp.contact.email + "]\n";
            }
            temp = temp.next;
        }
        return "Contact not found";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Contacts contacts = new Contacts();

        while(true){
            System.out.println("***************************");
            System.out.println("(A)dd");
            System.out.println("(D)elete");
            System.out.println("(E)mail Search");
            System.out.println("(P)rint List");
            System.out.println("(S)earch");
            System.out.println("(Q)uit");
            System.out.println("***************************");
            System.out.println("Please Enter a command: ");
            String command = sc.nextLine();
           
            
            switch (command) {
                case "A":
                    Scanner sc1 = new Scanner(System.in);

                    System.out.println("Enter the name of the contact: ");
                    String name = sc1.nextLine();
                    while (name == null || name.equals("")) {
                        System.out.println("Invalid name");
                        name = sc1.nextLine();
                    }

                    System.out.println("Enter the phone number of the contact: ");
                    String phoneNum = sc1.nextLine();
                    while (phoneNum.length() != 10) {
                        System.out.println("Invalid phone number");
                        phoneNum = sc1.nextLine();
                    }

                    System.out.println("Enter the email of the contact: ");
                    String email = sc1.nextLine();
                    while (!email.contains("@")) {
                        System.out.println("Invalid email");
                        email = sc1.nextLine();
                    }

                    contacts.addContact(name, phoneNum, email);
                    break;
                case "D":
                    System.out.println("Enter the name of the contact you want to delete: ");
                    String contactName = sc.nextLine();
                    contacts.removeContact(contactName);
                    break;
                case "E":
                    System.out.println("Enter the name of the email you want to search: ");
                    String emailName = sc.nextLine();
                    System.out.println(contacts.searchEmail(emailName));
                    break;

                case "P":
                    System.out.println(contacts.printList());
                    break;
                
                case "S":
                    System.out.println("Enter the name of the contact you want to search: ");
                    String searchNum = sc.nextLine();
                    System.out.println(contacts.searchCont(searchNum));
                    break;
                
                case "Q":
                    sc.close();
                    System.exit(0);
                    break;
                    
            
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
        
    }
}