package tr.com.cinigaz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employers") //işveren tablosu

public class EmployerEntity extends MainEntity {
    @Id
    @Column(name="employer_id") //işveren id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employer")
    @SequenceGenerator(name = "employer", allocationSize = 1, sequenceName = "seq_employer")
    private short employerId;

    @Column(name="employer_name", length = 50)//işveren adı
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String employerName;

    @Column(name="employer_description", length = 150)//işveren açıklama
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String employerDescription;

}
