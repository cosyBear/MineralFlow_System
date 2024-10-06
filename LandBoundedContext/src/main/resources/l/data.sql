INSERT INTO appointments (id, license_plate, seller_id, material_type_entity, time, payload) VALUES
                                                                                                 (1, 'ABC1001', UUID_TO_BIN('8d50dbe3-68a4-4afc-a242-13818629ac9d'), 'IRON', '2024-09-23 11:00:00', 0),
                                                                                                 (2, 'ABC1002', UUID_TO_BIN('326f5d06-17d0-42b6-8f68-ae815d9ce5f6'), 'IRON', '2024-09-23 11:00:00', 0),
                                                                                                 (3, 'ABC1003', UUID_TO_BIN('ff531b87-ba50-4b54-8666-5d860c4bd919'), 'IRON', '2024-09-23 11:00:00', 0);


INSERT INTO ware_house_entity(warehouse_id, seller_id, material_type, amount_of_material)
VALUES
    (UNHEX(REPLACE('123e4567-e89b-12d3-a456-426614174000', '-', '')), -- Convert Warehouse UUID to binary(16)
     UNHEX(REPLACE('123e4567-e89b-12d3-a456-426614174001', '-', '')), -- Convert Seller UUID to binary(16)
     1,  -- Assuming IRON material type is represented by 1
     10.0); -- Amount of material
