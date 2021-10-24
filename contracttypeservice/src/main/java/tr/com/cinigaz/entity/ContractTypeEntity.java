package tr.com.cinigaz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="contract_types")//sözleşme tip tablosu

public class ContractTypeEntity extends MainEntity{

    @Id
    @Column(name="contract_type_id") // sözleşme tip id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_type")
    @SequenceGenerator(name = "contract_type", allocationSize = 1, sequenceName = "seq_contract_type")
    private short contractTypeId;

    @Column(name="contract_type_name", length = 50) //sözleşme tipi adı
    @Email
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String contractTypeName;

    @Column(name="contract_type_description", length = 150)//sözleşme tipi açıklaması
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String contractTypeDescription;

}
