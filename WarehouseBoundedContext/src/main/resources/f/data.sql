
-- Insert into warehouse_entity with corresponding UUIDs
INSERT INTO warehouse_entity (sellerid, events_window_id, material_type, warehouse_number)
VALUES
    ('4ecd40db-49a0-4912-bdc0-0c98a3749c25', '82815650-6231-4c7b-ab03-e798d28e1a8e', 'CEMENT', 'WH001'),
    ('3e9100a3-4098-4fc8-9ace-da4d1142e970', '82815650-6231-4c7b-ab03-e798d28e1a8e', 'GYPSUM', 'WH002'),
    ('3e9100a3-4098-4fc8-9ace-da4d1142e970', '82815650-6231-4c7b-ab03-e798d28e1a8e', 'IRON', 'WH003'),
    ('3e9100a3-4098-4fc8-9ace-da4d1142e970', '82815650-6231-4c7b-ab03-e798d28e1a8e', 'PETCOKE', 'WH004'),
    ('3e9100a3-4098-4fc8-9ace-da4d1142e970', '82815650-6231-4c7b-ab03-e798d28e1a8e', 'SLAG', 'WH005');
-- Insert into warehouse_event_entity with events for the window
INSERT INTO warehouse_event_entity (amount, time, events_window_id, ware_house_event_id, weigh_bridge_ticket_id, type)
VALUES
    (1000, NOW(), '82815650-6231-4c7b-ab03-e798d28e1a8e', 'a85e62b0-dad3-41c8-9c9d-370431948818', 'f40c633f-e2e8-4456-860c-12e29cdc47a7', 'DELIVER'),
    (500, NOW(), '82815650-6231-4c7b-ab03-e798d28e1a8e', '084c4f32-fa43-42e2-bfd9-ea091eb202ef', 'f40c633f-e2e8-4456-860c-12e29cdc47a7', 'SHIP'),
    (2000, NOW(), '82815650-6231-4c7b-ab03-e798d28e1a8e', 'd3b7bcd9-5967-4338-b7ed-56747efedc24', 'a52d1ebd-1e83-4b68-8ae6-74a92dbb90f0', 'DELIVER');


-- Insert into warehouse_events_window_entity
INSERT INTO warehouse_events_window_entity  (warehouse_event_window_id, warehouse_id)
VALUES
    ('82815650-6231-4c7b-ab03-e798d28e1a8e', '7e67c74b-7eed-40c6-b49d-c24c9354d20d');
-- Insert into warehouse_entity with corresponding UUIDs