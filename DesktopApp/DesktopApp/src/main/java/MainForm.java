import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm
{
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JButton collapse;
    private JButton expand;
    private JPanel buttonPanel;
    private JTextField surnameField;
    private JTextField nameField;
    private JTextField patronymicField;
    private JTextField fullNameField;
    private JLabel surname;
    private JLabel name;
    private JLabel patronymic;
    private JLabel fullName;


    public MainForm()
    {
        expand.setVisible(false);
        fullName.setVisible(false);
        fullNameField.setVisible(false);

        collapse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (surnameField.getText().equals("") || nameField.getText().equals("")) {
                    JOptionPane.showMessageDialog(
                            mainPanel,
                            "Введите ФИО",
                            "Ошибка",
                            JOptionPane.PLAIN_MESSAGE
                    );
                } else {
                    surnameField.setVisible(false);
                    surname.setVisible(false);
                    name.setVisible(false);
                    nameField.setVisible(false);
                    patronymic.setVisible(false);
                    patronymicField.setVisible(false);

                    fullName.setVisible(true);
                    fullNameField.setVisible(true);

                    fullNameField.setText(surnameField.getText() + " " +
                            nameField.getText() + " " + patronymicField.getText());
                    fullNameField.getText();
                    collapse.setVisible(false);
                    expand.setVisible(true);
                }
            }
        });

        expand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] temp = fullNameField.getText().split(" ");

                if (temp.length >= 2){
                    collapse.setVisible(true);
                    expand.setVisible(false);

                    fullName.setVisible(false);
                    fullNameField.setVisible(false);

                    surnameField.setVisible(true);
                    surname.setVisible(true);
                    name.setVisible(true);
                    nameField.setVisible(true);
                    patronymic.setVisible(true);
                    patronymicField.setVisible(true);
                }
            }
        });

    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }
}
