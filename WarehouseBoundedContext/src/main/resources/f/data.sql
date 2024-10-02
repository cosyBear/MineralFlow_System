INSERT INTO warehouses (warehouseID, material_type, sellerId, events_window_id)
VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 'IRON', '12345', '550e8400-e29b-41d4-a716-446655440001'),
    ('550e8400-e29b-41d4-a716-446655440002', 'IRON', '12346', '550e8400-e29b-41d4-a716-446655440003');
INSERT INTO warehouse_events_window_entity (warehouse_event_window_id, warehouse_id)
VALUES
    ('550e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440000'),
    ('550e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440002');
INSERT INTO warehouse_event_entity (ware_house_event_id, time_of_event, type_of_event, amount_of_material, weigh_bridge_ticket_id, warehouse_events_window_entity_id)
VALUES
    ('550e8400-e29b-41d4-a716-446655440004', '2024-10-01 12:00:00', 'DELIVER', 100.5, '550e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440001'),
    ('550e8400-e29b-41d4-a716-446655440006', '2024-10-01 12:30:00', 'SHIP', 200.0, '550e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440001'),
    ('550e8400-e29b-41d4-a716-446655440008', '2024-10-01 13:00:00', 'DELIVER', 150.0, '550e8400-e29b-41d4-a716-446655440009', '550e8400-e29b-41d4-a716-446655440003');
