package be.kdg.prog6.watersideboundedcontext.adapters.out.Entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class BunkeringOperationEntity {


    LocalDateTime bunkeringTime;


    public BunkeringOperationEntity() {
    }

    public BunkeringOperationEntity(LocalDateTime bunkeringTime) {
        this.bunkeringTime = bunkeringTime;
    }


}
