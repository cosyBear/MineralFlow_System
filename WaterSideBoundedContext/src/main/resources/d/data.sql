INSERT INTO shipment_order (purchase_order, arrival_time, departure_time, vessel_id)
VALUES
    (UNHEX(REPLACE('6aa1209e-573f-4508-a66a-c801b47cfa01', '-', '')), '2024-10-20 10:00:00', '2024-10-20 16:00:00', UNHEX(REPLACE('fad0c3e7-bf55-45bc-a086-ad6e14fda8d2', '-', '')));
