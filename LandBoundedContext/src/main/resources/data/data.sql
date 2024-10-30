INSERT INTO appointments (appointment_id, license_plate, seller_id, material_type_entity, time, status)
VALUES
    (1, 'ABC1001', UUID_TO_BIN('8d50dbe3-68a4-4afc-a242-13818629ac9d'), 'IRON', '2024-10-19 11:00:00', 'ON_SITE'),
    (2, 'ABC1002', UUID_TO_BIN('326f5d06-17d0-42b6-8f68-ae815d9ce5f6'), 'SLAG', '2024-10-19 06:00:00', 'ON_SITE'),
    (3, 'ABC1003', UUID_TO_BIN('ff531b87-ba50-4b54-8666-5d860c4bd919'), 'GYPSUM', '2024-10-19 11:00:00', 'ON_SITE'),
    (4, 'ABC1004', UUID_TO_BIN('edbe8f68-f431-48a7-bbf4-92d59c47ebc1'), 'IRON', '2024-10-19 12:30:00', 'ON_SITE'),
    (5, 'ABC1005', UUID_TO_BIN('9a22f5a3-9f12-48bb-98a1-4f6327ae8b2a'), 'IRON', '2024-10-19 07:00:00', 'ON_SITE'),
    (6, 'ABC1006', UUID_TO_BIN('a06b780d-1c23-42e4-832d-f3cda9375de5'), 'IRON', '2024-10-19 08:45:00', 'ON_SITE'),
    (7, 'ABC1007', UUID_TO_BIN('1f7bd89b-f02b-4df1-86eb-76f441debf12'), 'IRON', '2024-10-19 10:15:00', 'ON_SITE'),
    (8, 'ABC1008', UUID_TO_BIN('bf96e6dc-588a-4f58-8575-3049d293d2a8'), 'IRON', '2024-10-19 09:30:00', 'ON_SITE'),
    (9, 'ABC1009', UUID_TO_BIN('477f03f5-533b-4f73-92cf-6bdb8854e2fc'), 'IRON', '2024-10-19 14:00:00', 'ON_SITE'),
    (10, 'ABC1010', UUID_TO_BIN('c221c7e5-2fe2-4654-9f27-9d217f682db9'), 'IRON', '2024-10-19 15:30:00', 'ON_SITE'),
    (102, 'ABC10102', UUID_TO_BIN('c221c7e5-2fe2-4654-9f27-9d217f68adf2'), 'IRON', '2024-10-19 15:30:00', 'LATE');



-- Set the seller_id to a known UUID
SET @seller_id = UNHEX(REPLACE('8d50dbe3-68a4-4afc-a242-13818629ac9d', '-', ''));

-- Insert 5 warehouses for the seller, each with a unique warehouse_id and corresponding material type (using ordinal values)
INSERT INTO ware_house_entity (warehouse_id, seller_id, material_type, amount_of_material)
VALUES (UNHEX(REPLACE('f8a9f9c0-45e2-4f32-809f-2d4a73c4083b', '-', '')), @seller_id, 'GYPSUM',
        200), -- IRON (MaterialType index 1)

       (UNHEX(REPLACE('c0e9a5a3-bbfd-4e73-bef7-9a2a8bce394b', '-', '')), @seller_id, 'IRON',
        200), -- GYPSUM (MaterialType index 0)
       (UNHEX(REPLACE('aedcb3a5-f2a6-4ed8-8090-0d92c3d6955c', '-', '')), @seller_id, 'CEMENT',
        200), -- CEMENT (MaterialType index 2)
       (UNHEX(REPLACE('acdd75b0-0712-4ef1-870f-67a8d0d7bc18', '-', '')), @seller_id, 'PETCOKE',
        200), -- PETCOKE (MaterialType index 3)
       (UNHEX(REPLACE('b03f68bb-6606-482d-8007-dc7f94acb1e5', '-', '')), @seller_id, 'SLAG', 200); -- SLAG (MaterialType index 4)

-- Set the seller_id to a new UUID for a different seller
