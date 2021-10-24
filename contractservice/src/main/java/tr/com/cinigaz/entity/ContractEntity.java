package tr.com.cinigaz.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="contracts") //sözleşme tablosu

public class ContractEntity extends MainEntity{

    @Id
    @Column(name="contract_id") //sözleşme id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract")
    @SequenceGenerator(name = "contract", allocationSize = 1, sequenceName = "seq_contract")
    private short contractId;

    @Column(name="contract_name", length = 50)//sözleşme adı
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String contractName;

    @Column(name="contract_description", length = 150)//sözleşme tür açıklama
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String contractDescription;
}


