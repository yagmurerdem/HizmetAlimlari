package tr.com.cinigaz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name="payment_plans")//ödeme planı tablosu
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPlanEntity extends MainEntity {

    @Id
    @Column(name="payment_plan_id") //ödeme planı id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_plan")
    @SequenceGenerator(name = "payment_plan", allocationSize = 1, sequenceName = "seq_payment_plan")
    private short paymentPlanId;

    @Column(name="payment_plan", length = 50)//ödeme planı adı
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String paymentPlanName;

    @Column(name="payment_plan_description", length = 150)//ödeme planı açıklama
    @NotBlank //boş geçilmesin //"" bu demek ve bunun içi de boş olmasın
    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
    private String paymentPlanDescription;

    @Column(name="contract_id", length = 150)
    private short contractId;
}
