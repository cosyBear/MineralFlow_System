package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseEventQuery;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseManagerPort;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseQuery;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseLoadPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WarehouseManagerUseCaseImp implements WarehouseManagerPort {


    private final WarehouseLoadPort warehouseLoadPort;

    public WarehouseManagerUseCaseImp(WarehouseLoadPort warehouseLoadPort) {
        this.warehouseLoadPort = warehouseLoadPort;
    }


    @Override
    public List<WarehouseQuery> warehouseQueryList() {
        return mapToQuery(warehouseLoadPort.loadAllWarehouses());
    }

    public List<WarehouseQuery> mapToQuery(List<Warehouse> warehouseList) {
        List<WarehouseQuery> queryList = new ArrayList<>();
        for (Warehouse warehouse : warehouseList) {
            List<WarehouseEventQuery> eventList = new ArrayList<>();
            for (WarehouseEvent warehouseEvent : warehouse.getEventsWindow().getWarehouseEventList()) {
                eventList.add(new WarehouseEventQuery(warehouseEvent.getMaterialType().toString(), warehouseEvent.getTime(), warehouseEvent.getType().toString()));
            }

            queryList.add(new WarehouseQuery(warehouse.getWarehouseNumber().getId(), warehouse.getSellerId().getSellerID(), warehouse.getMaterialType().toString(), eventList));

        }

        return queryList;
    }


}
