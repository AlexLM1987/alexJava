
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "????????????";

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery( "SELECT Courses.id, Students.id " +
                    " FROM PurchaseList " +
                    " JOIN Students ON PurchaseList.student_name = Students.name " +
                    " JOIN Courses ON PurchaseList.course_name = Courses.name " +
                    " ORDER BY Courses.id;");

            while (resultSet.next())
            {
                int studentId = Integer.parseInt(resultSet.getString("Students.id"));
                int courseId = Integer.parseInt(resultSet.getString("Courses.id"));
                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
                KeyLinkedPurchaseList id = new KeyLinkedPurchaseList(studentId, courseId);
                linkedPurchaseList.setKeyLinkedPurchaseList(id);
                session.saveOrUpdate(linkedPurchaseList);
            }
            transaction.commit();
            sessionFactory.close();
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
