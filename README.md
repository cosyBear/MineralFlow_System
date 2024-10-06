# WeighBridge and Warehouse System



### 1. **LandsideBoundedContext**

This context handles the weighbridge operations and the management of trucks entering and exiting the facility.

- **WeighBridgeUseCaseImp **:
    - `weighTruckIn()`: This method handles the logic when a truck enters the weighbridge. It creates a `WeighbridgeTicket` and publishes a `weighInEvent` event. If the truck is on time, it is allowed to enter, otherwise, an error is thrown.
    - `weighTruckOut()`: This method handles the logic when a truck exits the weighbridge. It updates the `WeighbridgeTicket` and publishes a `WeighOutEvent` event.
    - `assignAWarehouseTruck()`: This method checks if a warehouse is available for the seller's material type. It either assigns an existing warehouse or indicates whether a new one can be created based on warehouse availability.

- **WeighBridgeController (REST Controller)**:
    - `POST /trucks/weighIn`: Endpoint for handling truck weigh-in. Accepts truck details such as license plate, material type, and weight.....
    - `POST /trucks/weighOut`: Endpoint for handling truck weigh-out. Accepts truck details including final weight and warehouse status.....

- **WarehouseListener** 
  - this is a event listener used by the landSide to listen to the update the warehouse Send.
  - what seller, what material, the amount in the warehouse.
### 2. **WarehouseBoundedContext**

This context manages the warehouse operations and material handling processes.

- **WarehouseUseCaseImp **:
    - `assignWarehouseToSeller()`: Handles the truck entering a warehouse by assigning a warehouse to store the truck's material
    - `truckIn()`:  uses the assignWarehouseToSeller() to get the warehouse for that seller and start the unloading process .
    - `truckOut()`: this method is used to update the True Material weight in the warehouse. and publish event of the warehouseMaterialEvent 
    - to  update the LandSide

- **WeighBridgeListener (Event Listener)**:
    - Listens to the `truckWeightedInQueue` 
    - Listens to the `truckWeightedOutQueue`