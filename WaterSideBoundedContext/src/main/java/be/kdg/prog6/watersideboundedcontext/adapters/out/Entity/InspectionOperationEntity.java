package be.kdg.prog6.watersideboundedcontext.adapters.out.Entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class InspectionOperationEntity {

    private LocalDateTime inspectionDate;
    private String inspectorSignature;

    public InspectionOperationEntity(LocalDateTime inspectionDate, String inspectorSignature) {
        this.inspectionDate = inspectionDate;
        this.inspectorSignature = inspectorSignature;
    }

    public InspectionOperationEntity() {
    }
}
