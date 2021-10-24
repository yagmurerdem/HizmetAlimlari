package tr.com.cinigaz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class PaymentPlanDto {

    private String paymentPlanName;
    private String paymentPlanDescription;
}
