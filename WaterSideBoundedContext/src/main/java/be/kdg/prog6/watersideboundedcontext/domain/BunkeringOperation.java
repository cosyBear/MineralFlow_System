package be.kdg.prog6.watersideboundedcontext.domain;

import java.time.LocalDateTime;

public record BunkeringOperation(LocalDateTime bunkeringTime) {


    public boolean isCompleted(){
        return this.bunkeringTime != null;
    }



}

