INSERT INTO trucks (id, license_number, payload) VALUES (1, 'ABC123', 5000.00);
INSERT INTO trucks (id, license_number, payload) VALUES (2, 'DEF456', 4500.00);
INSERT INTO trucks (id, license_number, payload) VALUES (3, 'GHI789', 5500.00);
INSERT INTO trucks (id, license_number, payload) VALUES (4, 'JKL012', 4800.00);
INSERT INTO trucks (id, license_number, payload) VALUES (5, 'MNO345', 6000.00);
INSERT INTO trucks (id, license_number, payload) VALUES (6, 'PQR678', 5200.00);
INSERT INTO trucks (id, license_number, payload) VALUES (7, 'STU901', 4300.00);
INSERT INTO trucks (id, license_number, payload) VALUES (8, 'VWX234', 5400.00);
INSERT INTO trucks (id, license_number, payload) VALUES (9, 'YZA567', 4700.00);
INSERT INTO trucks (id, license_number, payload) VALUES (10, 'BCD890', 5800.00);

INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (1, '2024-09-19', 900, 1000);
INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (2, '2024-09-19', 1000, 1100);
INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (3, '2024-09-19', 1100, 1200);
INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (4, '2024-09-20', 900, 1000);
INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (5, '2024-09-20', 1000, 1100);
INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (6, '2024-09-20', 1100, 1200);
INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (7, '2024-09-21', 900, 1000);
INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (8, '2024-09-21', 1000, 1100);
INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (9, '2024-09-21', 1100, 1200);
INSERT INTO time_slots (id, date, earliest_arrival_time, latest_arrival_time) VALUES (10, '2024-09-22', 900, 1000);

INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (1, 'IRON', 1, 1);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (2, 'CEMENT', 2, 2);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (3, 'GYPSUM', 3, 3);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (4, 'Petcoke', 4, 4);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (5, 'SLAG', 5, 5);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (6, 'IRON', 6, 6);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (7, 'CEMENT', 7, 7);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (8, 'GYPSUM', 8, 8);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (9, 'Petcoke', 9, 9);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (10, 'SLAG', 10, 10);


INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (11, 'IRON', 1, 5);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (12, 'CEMENT', 2, 6);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (13, 'GYPSUM', 3, 7);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (14, 'Petcoke', 4, 8);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (15, 'SLAG', 5, 9);
INSERT INTO appointments (id, material_type, time_slot_id, truck_id) VALUES (16, 'IRON', 6, 10);
