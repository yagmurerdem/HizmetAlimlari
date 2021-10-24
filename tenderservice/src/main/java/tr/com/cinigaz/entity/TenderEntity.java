package tr.com.cinigaz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tenders")
public class TenderEntity extends MainEntity {

    @Id
    @Column(name="tender_id") // ihale id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tender")
    @SequenceGenerator(name = "tender", allocationSize = 1, sequenceName = "seq_tender")
    private short tenderId;

    @Column(name="tender_name", length = 50) //ihale adı
    private String tenderName;

    @Column(name="tender_description", length = 150)//ihale açıklaması
    private String tenderDescription;

//    @Column(name="interlocutor_institution_id")//muhatap kurum
//    private String interlocutorinstituonName;

    //ihale id gelicek
}
