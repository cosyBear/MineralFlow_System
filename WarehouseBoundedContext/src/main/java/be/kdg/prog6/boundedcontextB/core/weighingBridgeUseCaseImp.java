package be.kdg.prog6.boundedcontextB.core;

import be.kdg.prog6.boundedcontextB.domain.Bridge;
import be.kdg.prog6.boundedcontextB.domain.PDT;
import be.kdg.prog6.boundedcontextB.domain.Truck;
import be.kdg.prog6.boundedcontextB.domain.Warehouse;
import be.kdg.prog6.boundedcontextB.port.in.weighingBridgeUseCase;
import org.springframework.stereotype.Service;

@Service
public class weighingBridgeUseCaseImp implements weighingBridgeUseCase {

    //later add a repo and so on
    // for now use a object you make you can do this just relax.

    private  Bridge bridge = new Bridge();
    private  Warehouse warehouse = new Warehouse();

    @Override
    public void processTruck(Truck  truck) {



            bridge.assignWarehouseNumber(truck);
            PDT pdtTicket = warehouse.addMaterial(truck);
            bridge.TruckLeaving(truck , pdtTicket);
            //after this the truck leaves

    }
}
