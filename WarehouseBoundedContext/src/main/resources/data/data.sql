

-- Set the seller_id to a known UUID
SET @seller_id = UNHEX(REPLACE('8d50dbe3-68a4-4afc-a242-13818629ac9d', '-', ''));

-- Insert into WarehouseEventsWindowEntity with unique warehouse_events_window_id
INSERT INTO warehouse_events_window_entity (warehouse_events_window_id, warehouse_id)
VALUES
    (UNHEX(REPLACE('a1b1c9d1-e2f3-11d3-a1f3-1234567890aa', '-', '')), UNHEX(REPLACE('f8a9f9c0-45e2-4f32-809f-2d4a73c4083b', '-', ''))), -- For IRON
    (UNHEX(REPLACE('b2b2d7e2-f3a2-22b3-b2c4-1234567890bb', '-', '')), UNHEX(REPLACE('c0e9a5a3-bbfd-4e73-bef7-9a2a8bce394b', '-', ''))), -- For GYPSUM
    (UNHEX(REPLACE('c3c3e3f3-a4b4-33c4-c3d5-1234567890cc', '-', '')), UNHEX(REPLACE('aedcb3a5-f2a6-4ed8-8090-0d92c3d6955c', '-', ''))), -- For CEMENT
    (UNHEX(REPLACE('d4d4f4a6-b4c7-44d5-d4e6-1234567890dd', '-', '')), UNHEX(REPLACE('acdd75b0-0712-4ef1-870f-67a8d0d7bc18', '-', ''))), -- For PETCOKE
    (UNHEX(REPLACE('e5e5a7b6-c5d6-55e6-e5f7-1234567890ee', '-', '')), UNHEX(REPLACE('b03f68bb-6606-482d-8007-dc7f94acb1e5', '-', ''))); -- For SLAG


INSERT INTO warehouse_event_entity (event_id, event_time, event_type, material_weight, weigh_bridge_ticket_id, warehouse_events_window_id, material_type)
VALUES
    -- Events for IRON warehouse
    (UNHEX(REPLACE('f1a7b8c9-11d2-1112-a7b8-1234567890ff', '-', '')), '2024-10-01 12:00:00', 'DELIVER', 200.00, UNHEX(REPLACE('a1b1c9d1-e2f3-11d3-a1f3-1234567890aa', '-', '')), UNHEX(REPLACE('a1b1c9d1-e2f3-11d3-a1f3-1234567890aa', '-', '')), 'IRON'),

    -- Events for GYPSUM warehouse
    (UNHEX(REPLACE('f3c1d2e3-33d4-3334-c1d2-1234567890bb', '-', '')), '2024-10-01 12:30:00', 'DELIVER', 200.00, UNHEX(REPLACE('b2b2d7e2-f3a2-22b3-b2c4-1234567890bb', '-', '')), UNHEX(REPLACE('b2b2d7e2-f3a2-22b3-b2c4-1234567890bb', '-', '')), 'GYPSUM'),

    -- Events for CEMENT warehouse
    (UNHEX(REPLACE('f5e6f5a7-55d6-5556-e6f5-1234567890dd', '-', '')), '2024-10-04 09:30:00', 'DELIVER', 200.00, UNHEX(REPLACE('c3c3e3f3-a4b4-33c4-c3d5-1234567890cc', '-', '')), UNHEX(REPLACE('c3c3e3f3-a4b4-33c4-c3d5-1234567890cc', '-', '')), 'CEMENT'),

    -- Events for PETCOKE warehouse
    (UNHEX(REPLACE('f6f5a7b6-66d7-6667-f5a7-1234567890ee', '-', '')), '2024-10-05 10:00:00', 'DELIVER', 200.00, UNHEX(REPLACE('d4d4f4a6-b4c7-44d5-d4e6-1234567890dd', '-', '')), UNHEX(REPLACE('d4d4f4a6-b4c7-44d5-d4e6-1234567890dd', '-', '')), 'PETCOKE'),

    -- Events for SLAG warehouse
    (UNHEX(REPLACE('f7b9c8d7-77d8-7778-b9c8-1234567890ff', '-', '')), '2024-10-06 11:00:00', 'DELIVER', 200.00, UNHEX(REPLACE('e5e5a7b6-c5d6-55e6-e5f7-1234567890ee', '-', '')), UNHEX(REPLACE('e5e5a7b6-c5d6-55e6-e5f7-1234567890ee', '-', '')), 'SLAG');


-- Insert into warehouse_entity (each warehouse associated with a seller_id)
INSERT INTO warehouse_entity (warehouse_id, seller_id, material_type, warehouse_events_window_id)
VALUES
    (UNHEX(REPLACE('f8a9f9c0-45e2-4f32-809f-2d4a73c4083b', '-', '')), @seller_id, 'IRON', UNHEX(REPLACE('a1b1c9d1-e2f3-11d3-a1f3-1234567890aa', '-', ''))), -- IRON
    (UNHEX(REPLACE('c0e9a5a3-bbfd-4e73-bef7-9a2a8bce394b', '-', '')), @seller_id, 'GYPSUM', UNHEX(REPLACE('b2b2d7e2-f3a2-22b3-b2c4-1234567890bb', '-', ''))), -- GYPSUM
    (UNHEX(REPLACE('aedcb3a5-f2a6-4ed8-8090-0d92c3d6955c', '-', '')), @seller_id, 'CEMENT', UNHEX(REPLACE('c3c3e3f3-a4b4-33c4-c3d5-1234567890cc', '-', ''))), -- CEMENT
    (UNHEX(REPLACE('acdd75b0-0712-4ef1-870f-67a8d0d7bc18', '-', '')), @seller_id, 'PETCOKE', UNHEX(REPLACE('d4d4f4a6-b4c7-44d5-d4e6-1234567890dd', '-', ''))), -- PETCOKE
    (UNHEX(REPLACE('b03f68bb-6606-482d-8007-dc7f94acb1e5', '-', '')), @seller_id, 'SLAG', UNHEX(REPLACE('e5e5a7b6-c5d6-55e6-e5f7-1234567890ee', '-', ''))); -- SLAG


INSERT into warehouse_db.purchase_order_entity(purchase_order_id, order_date, seller_id, customer_name,buyer_id,status)
VALUES (UNHEX(REPLACE('8d50dbe3-68a4-4afc-a242-13818629ac9f', '-', '')), '2024-10-20', @seller_id, 'Customer XYZ', UNHEX(REPLACE('6aa1209e-573f-4508-a66a-c801b47cfa03', '-', '')),'outstanding');

INSERT INTO purchase_order_line_entity (order_line_id, material_type, quantity, price_per_ton, purchase_order_purchase_order_id)
VALUES
    (UNHEX(REPLACE('55a5d91c-433f-44b6-8510-bf389e693812', '-', '')), 'IRON', 50, 50.5, UNHEX(REPLACE('8d50dbe3-68a4-4afc-a242-13818629ac9f', '-', ''))),
    (UNHEX(REPLACE('7fff25e6-9f5e-4f1d-8b2c-616e39e55ce3', '-', '')), 'PETCOKE', 50, 70.0, UNHEX(REPLACE('8d50dbe3-68a4-4afc-a242-13818629ac9f', '-', ''))),
    (UNHEX(REPLACE('fad0c3e7-bf55-45bc-a086-ad6e14fda8d2', '-', '')), 'CEMENT', 50, 40.75, UNHEX(REPLACE('8d50dbe3-68a4-4afc-a242-13818629ac9f', '-', '')));
#####