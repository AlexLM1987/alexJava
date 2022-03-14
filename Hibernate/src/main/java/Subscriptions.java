import javax.persistence.*;
import java.util.Date;

@Entity
public class Subscriptions {

    @EmbeddedId
    private SubscriptionsKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int student;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public SubscriptionsKey getId() {
        return id;
    }

    public void setId(SubscriptionsKey id) {
        this.id = id;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
