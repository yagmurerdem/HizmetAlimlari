package tr.com.cinigaz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="service_types") //hizmet tür tablosu

public class ServiceTypeEntity extends MainEntity {

    @Id
    @Column(name="service_type_id") //hizmet tür id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_type")
    @SequenceGenerator(name = "service_type", allocationSize = 1, sequenceName = "seq_service_type")
    private short serviceTypeId;

    @Column(name="service_type_name", length = 50)//hizmet tür adı
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String serviceTypeName;

    @Column(name="service_type_description", length = 150)//hizmet tür açıklama
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String serviceTypeDescription;
}
