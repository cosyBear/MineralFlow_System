//package be.kdg.prog6.LandSideBoundedContext.domain;
//
//import be.kdg.prog6.LandSideBoundedContext.port.in.TruckWeighingCommand2;
//
//import java.time.LocalDateTime;
//
//public class Bridge {
//
//    private Integer bridgeNumber;
//
//
//    public Bridge(Integer bridgeNumber) {
//        this.bridgeNumber = bridgeNumber;
//    }
//
//    public Integer getBridgeNumber() {
//        return bridgeNumber;
//    }
//
//    public void setBridgeNumber(Integer bridgeNumber) {
//        this.bridgeNumber = bridgeNumber;
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    what you need to do
////    =========================
////    tomorrow in class
//
//
//
//
//    //TODO so first i would make an api call from the landside this data total Weight of the truck plus the material plus the licensePlate and SellerID
//    // this will call the WareHouse use case and it will publish an event to the with this data this data total Weight of the truck plus the material plus the licensePlate and SellerID to the Warehouse
//
//    // TODO in the warehouse i listen to that event and i would call the core and and run a method that say startLoading this method will also create an event with the type of material, date of delivery, warehouse number,
//    //TODO also the startLoading will create an event of the WarehouseEvent with the amout for the mat is 0 but i would
//
//
//
//    //TODO land side will listen to that and event take the data
//
//    //
//
//
//
//   // #########################################33
//
//    //TODO 1
//    // todo so the first thing you do is make a method called "(getAvailableBridge)" that the method will recive this data total Weight of the truck plus the material plus the licensePlate and SellerID
//    // todo you will tell the getAvailableBridge in the  bridgeUseCase find the empty Bridge or Create one if none is found.
//    //TODO after this you would call the weighTruckIn( Bridge bridge , weighTruckInCommand ) this weighTruckInCommand  will have this  total Weight of the truck plus the material plus the licensePlate and SellerID
//    // you passing the bridge and the data.
//
//    //TODO 2 weighTruckIn
//    // Todo this method will be called from getAvailableBridge and it will call the bridge to set the attrubites in the class like the
////    private Integer bridgeNumber;
////    private double currentTruckWeight;
////    private LicensePlate licensePlate;
////    private MaterialType materialType;
////    private SellerId sellerId;
////    private LocalDateTime timestamp;
//     // after that the method will call RabitMq to publish the event to the warehouse
//
//
//        // todO WAREHOUSE side
//        // toDo so what the WAREHOUSE will do is that listen to that event make a dto and creat a command form that data and send it to the WAREHOUSEUseCase
//        // TODO in the WAREHOUSE you would have a method to say like ("deliverMaterial") this method will create and event but the amount should be 0 return the id of the event so you can change the amount later
//        // TODO in here what we can also do rigth after the ("deliverMaterial") method runs we can create a PDT will Include the Event ID from deliverMaterial and    send the PDT  to the LandSide this will trigger weighTruckOut method this   method  will
//        // Todo  load the PurchaseOrder for the licensePlate why because we know the real amount of material in the PurchaseOrder  now we can subtract the total from teh amount of material in the PurchaseOrder send an event to change the even in the waerhouse put in the real amount
//    /   //TODo also create teh WBT now and we aer done
////
//
//
//
//
//
//
////    what you need to do
////    =========================
////
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    // what shoudl the Bridge
//    // should
//    // this should also create the WBT
//    // i can make a const for the tuck to say the truck weigth 5 tons or something like this.
//    // make a enum for the truck weight aloine without material
//
//
//    //when the truck come in for the first time.
//    // so i guess we take the weigth ofi the prusher line order and the tuck wigth and put them toegthr this is the first time weigthing
//    // we weigh  the truck we assign the warehouse and dump the matirals when you do that the PDT is gernated.
//    // so you create a pdt database
//
//
//
//
//
////    public void weighTruckIn(TruckWeighingCommand command) {
////        this.currentTruckLicensePlate = command.getLicensePlate();
////        this.currentTruckWeight = command.getWeight();
////        this.timeIn = command.getTimestamp();
////
////        // Assign warehouse based on material type (this logic will call output ports)/
////        //TODO=  This could be an internal method that checks which warehouse is available based on the material type
////        this.assignedWarehouse = assignWarehouse(command.getMaterialType());
////
////        // Create and publish TruckWeightedIn event (domain event)
////        TruckWeightedInEvent event = new TruckWeightedInEvent(
////                command.getLicensePlate(),
////                command.getWeight(),
////                command.getTimestamp(),
////                assignedWarehouse
////        );
////        eventPublisher.publishTruckWeightedIn(event);
////    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}