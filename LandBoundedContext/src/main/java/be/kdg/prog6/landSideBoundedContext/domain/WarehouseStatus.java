package be.kdg.prog6.landSideBoundedContext.domain;

public enum WarehouseStatus {
    WAREHOUSE_NOT_FOUND,             // The seller can create a new warehouse
    ALREADY_EXISTS_NOT_FULL,// The seller has a warehouse for the material type, and it's not full (below 80%)
    FULL,                   // The warehouse is full (80% - 100%)
    OVERFLOW,               // The warehouse is at overflow capacity (100% - 110%)
    FULL_OVERFLOW,          // The warehouse has exceeded overflow capacity (above 110%)
    MAX_REACHED     ,        // The seller has reached the maximum number of warehouses (5 warehouses)
    DISREGARD
}
