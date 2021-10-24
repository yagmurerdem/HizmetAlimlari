package tr.com.cinigaz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractFileTypeDto {
    private String contractFileTypeName;
    private String contractFileTypeDescription;
}
