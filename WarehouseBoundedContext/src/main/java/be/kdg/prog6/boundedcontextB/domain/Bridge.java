package be.kdg.prog6.boundedcontextB.domain;

import be.kdg.prog6.boundedcontextB.util.Error.NoAvailableWarehouse;

import java.time.LocalDateTime;
import java.util.List;

public class Bridge {

    private double initLoad; // value when the truck first arrive at the Bridge
    private double finalLoad; // when the truck pass the Bridge second time for the final weight

    List<Warehouse> availableWarehouses ;
    // so the availableWarehouses would have a list of all the warehouses and then when a truck arriave we give a warehouse for the client







    public void assignWarehouseNumber(Truck truck) throws NoAvailableWarehouse
    {

        for(Warehouse warehouse : availableWarehouses)
        {
            if(warehouse.isWarehouseAvailable()){
                truck.setWareHouseNumber(warehouse.getWarehouseNumber());
                this.initLoad = truck.getPayload();
            }else{
                throw new NoAvailableWarehouse("there is no available warehouse for " + truck.getLicensePlate() );
            }
        }

    }


//
//    TODO1 so this mehtod should get the initload of hte truck and deduct the weight we have now. to get the true weight of the truck payload
//    TODO2 also this method should do the following: the truck driver will get a (Weigh Bridge Transaction) WBT document. meaing this method would return a WBT ticked
//     TODo3 not sure if you have to make this here ? After the truck passes the weighing bridge, we enrich the payload delivery ticket with the
//    weight from the WBT document.
//    also this maybe here ?
//    The truck passes the exit, where its license plate is scanned again and we consolidate the
//    appointment made by the seller.


    public WBT TruckLeaving(Truck truck , PDT pdtTicket) {

        this.finalLoad = initLoad - 0; // for now just 0 zero because i dont know how to solve this  without Changing  the domain

        pdtTicket.setPayload(finalLoad);

        return new WBT(truck.getLicensePlate(), LocalDateTime.now(), this.finalLoad);


    }













}