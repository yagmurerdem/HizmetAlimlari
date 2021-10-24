package tr.com.cinigaz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="contract_file_types")
public class ContractFileTypeEntity extends MainEntity {

    @Id
    @Column(name="contract_file_type_id") // sözleşme tip id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_file_type")
    @SequenceGenerator(name = "contract_file_type", allocationSize = 1, sequenceName = "seq_contract_file_type")
    private short contractFileTypeId;

    @Column(name="contract_file_type_name", length = 50) //sözleşme tipi adı
    private String contractFileTypeName;

    @Column(name="contract_file_type_description", length = 150)//sözleşme tipi açıklaması
    private String contractFileTypeDescription;
}
