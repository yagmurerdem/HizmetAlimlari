package tr.com.cinigaz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recources")
public class RecourceEntity extends MainEntity {

    @Id
    @Column(name="recource_id") // başvuru id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recource_type")
    @SequenceGenerator(name = "recource_type", allocationSize = 1, sequenceName = "seq_recource_type")
    private short recourceId;

    @Column(name="recource_name", length = 50) //başvuru adı
    private String recourceName;

    @Column(name="recource_description", length = 150)//başvuru açıklaması
    private String recourceDescription;

    @Column(name="interlocutor_institution_id")//muhatap kurum
    private String interlocutorinstituonName;

    @Column(name="tender_id")// ihale id
    private String tenderName;

}
