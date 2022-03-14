import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {
    @EmbeddedId
    private KeyLinkedPurchaseList KeyLinkedPurchaseList;
    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;
    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

}
