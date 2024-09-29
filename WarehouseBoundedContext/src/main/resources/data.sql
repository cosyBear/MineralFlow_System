-- Insert dummy data for available warehouses (capacity not reached, no material)
INSERT INTO warehouse_entity (material_type, sellerId, warehouse_number, capacity_reached, amount_of_material)
VALUES
    (NULL, 'seller-01', 1, false, 0),
    (NULL, 'seller-02', 2, false, 0),
    (NULL, 'seller-03', 3, false, 0),
    (NULL, 'seller-04', 4, false, 0),
    (NULL, 'seller-05', 5, false, 0);

-- Insert dummy data for full warehouses (capacity reached, some material present)
INSERT INTO warehouse_entity (material_type, sellerId, warehouse_number, capacity_reached, amount_of_material)
VALUES
    ('IRON', 'seller-06', 6, true, 5000),
    ('CEMENT', 'seller-07', 7, true, 7000),
    ('PETCOKE', 'seller-08', 8, true, 3000),
    ('SLAG', 'seller-09', 9, true, 2000),
    ('SLAG', 'seller-10', 10, true, 8000);
