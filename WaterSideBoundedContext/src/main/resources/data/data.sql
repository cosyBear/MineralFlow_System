INSERT INTO shipment_order (
    purchase_order,
    expected_arrival_time,
    expected_departure_time,
    vessel_id,
    shipment_order_id,
    status
)
VALUES (
           UNHEX(REPLACE('8d50dbe3-68a4-4afc-a242-13818629ac9f', '-', '')),
           '2024-10-20 10:00:00',
           '2024-10-20 16:00:00',
           UNHEX(REPLACE('8d50dbe3-68a4-4afc-a242-13818629ac9f', '-', '')),
           UNHEX(REPLACE('b73a7a63-07f6-4a50-9bd8-718d51e7c415', '-', '')),
           'AWAITING_ARRIVAL'
       );
