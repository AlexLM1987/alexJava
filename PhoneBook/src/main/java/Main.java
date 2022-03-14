import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();

            if (input.equals("LIST")) {
                for (String temp : phoneBook.getAllContacts()) {
                    System.out.println(temp);
                }
                break;
            }

            String regexPhone = "[0-9]{11}";
            if (!input.matches(regexPhone)) {
                if (phoneBook.getPhonesByName(input).isEmpty()) {
                    System.out.println("Такого имени в телефонной книге нет.");
                    System.out.println("Введите номер телефона для абонента " + input + ":");
                    String input2 = scanner.nextLine();
                    phoneBook.addContact(input2, input);
                } else {
                   System.out.println(phoneBook.getPhonesByName(input));
                }
                continue;
            }

            if (phoneBook.getNameByPhone(input).equals("")) {
                System.out.println("Такого номера нет в телефонной книге.");
                System.out.println("Введите имя абонента для номера " + input + ":");
                String input2 = scanner.nextLine();
                phoneBook.addContact(input, input2);
            } else {
                System.out.println(phoneBook.getNameByPhone(input));
            }
        }
    }
}
