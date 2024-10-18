# INSERT INTO appointments (id, license_plate, seller_id, material_type_entity, time, status) VALUES
#                                                                                                  (1, 'ABC1001', UUID_TO_BIN('8d50dbe3-68a4-4afc-a242-13818629ac9d'), 'IRON', '2024-09-23 11:00:00', 'ON_SITE' ),
#                                                                                                  (2, 'ABC1002', UUID_TO_BIN('326f5d06-17d0-42b6-8f68-ae815d9ce5f6'), 'SLAG', '2024-09-23 11:00:00', 'AWAITING_ARRIVAL' ),
#                                                                                                  (3, 'ABC1003', UUID_TO_BIN('ff531b87-ba50-4b54-8666-5d860c4bd919'), 'GYPSUM', '2024-09-23 11:00:00',  'ON_SITE' );
#
#
# -- Set the seller_id to a known UUID
SET @seller_id = UNHEX(REPLACE('8d50dbe3-68a4-4afc-a242-13818629ac9d', '-', ''));

-- Insert 5 warehouses for the seller, each with a unique warehouse_id and corresponding material type (using ordinal values)
INSERT INTO ware_house_entity (warehouse_id, seller_id, material_type, amount_of_material)
VALUES (UNHEX(REPLACE('f8a9f9c0-45e2-4f32-809f-2d4a73c4083b', '-', '')), @seller_id, 'GYPSUM',
        0.0), -- IRON (MaterialType index 1)

       (UNHEX(REPLACE('c0e9a5a3-bbfd-4e73-bef7-9a2a8bce394b', '-', '')), @seller_id, 'IRON',
        0.0), -- GYPSUM (MaterialType index 0)
       (UNHEX(REPLACE('aedcb3a5-f2a6-4ed8-8090-0d92c3d6955c', '-', '')), @seller_id, 'CEMENT',
        0.0), -- CEMENT (MaterialType index 2)
       (UNHEX(REPLACE('acdd75b0-0712-4ef1-870f-67a8d0d7bc18', '-', '')), @seller_id, 'PETCOKE',
        0.0), -- PETCOKE (MaterialType index 3)
       (UNHEX(REPLACE('b03f68bb-6606-482d-8007-dc7f94acb1e5', '-', '')), @seller_id, 'SLAG', 0.0); -- SLAG (MaterialType index 4)

