package be.kdg.prog6.LandSideBoundedContext.Core;

import java.time.LocalDate;

//value object.
public record TimeSlot(LocalDate date,
                       Integer earliestArravieTime,
                       Integer latestArravieTime)
{


    LocalDate getDate()
    {
        return date;
    }
    Integer getEarliestArravieTime(){
        return earliestArravieTime;
    }

    Integer getLatestArravieTime(){
        return latestArravieTime;
    }



}
