import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class PhoneBook {
    TreeMap<String, String> phoneBook = new TreeMap<>();

    public void addContact(String phone, String name) {
        String regexPhone = "[0-9]{11}";
        String regexName = "[А-Яа-я]+";
        if (phone.matches(regexPhone) && name.matches(regexName)) {
            phoneBook.put(phone, name);
            System.out.println("Контакт сохранен!");
        } else {
            System.out.println("Неверный формат ввода");
        }
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
    }

    public String getNameByPhone(String phone) {
        if (phoneBook.containsKey(phone)) {
            String name = phoneBook.get(phone);
            return name + " - " + phone;
        }
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        if (phoneBook.containsValue(name)) {
            TreeSet<String> namePhone = new TreeSet<>();
            for (Map.Entry temp : phoneBook.entrySet()) {
                if (temp.getValue().equals(name)) {
                    namePhone.add(name + " - " + temp.getKey());
                }
            }
            return namePhone;
        }
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        if (!phoneBook.isEmpty()) {
            TreeSet<String> phoneNames = new TreeSet<>();
            for (Map.Entry e : phoneBook.entrySet()) {
                phoneNames.add(e.getValue() + " - " + e.getKey());
            }
            return phoneNames;
        }
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        return new TreeSet<>();
    }
}