-- Inserting into qkc_branch
INSERT INTO qkc_branch (branch_name, is_active) VALUES ('Branch A', 'y');
INSERT INTO qkc_branch (branch_name, is_active) VALUES ('Branch B', 'y');
INSERT INTO qkc_branch (branch_name, is_active) VALUES ('Branch C', 'y');
INSERT INTO qkc_branch (branch_name, is_active) VALUES ('Branch D', 'y');
INSERT INTO qkc_branch (branch_name, is_active) VALUES ('Branch E', 'y');

-- Inserting into qkc_user
INSERT INTO qkc_user (username, password, branch_id, is_active) VALUES ('John', '$2a$10$LDRBSxqB.NQ/BtADn7aMUedZk3mZAmvFGR6PD2iRoKbL2Z5ZE2wyu', 1, 'y');
INSERT INTO qkc_user (username, password, branch_id, is_active) VALUES ('Jane', '$2a$10$3iEg4jOBq.WLLFyOzbqWeukr14opCCsXLal76SYF/d8KyFTRqBs6G', 2, 'y');
INSERT INTO qkc_user (username, password, branch_id, is_active) VALUES ('Tingyun', '$2a$10$gRp5T.HpzC9hyu5vxXMZ2.T395YMA15bp8JIJuS6rE7.jqOB2LW9C', 3, 'y');
INSERT INTO qkc_user (username, password, branch_id, is_active) VALUES ('Ruan', '$2a$10$ByD.t9lXJtDJRehLcNlmBOZTjbP6EV.rmUwmtZiY4VYxPe5hE26UW', 4, 'y');
INSERT INTO qkc_user (username, password, branch_id, is_active) VALUES ('Mei', '$2a$10$sIRtAtVM7rkED9kAnn/KiOIWfSYLZ0MLRqwc94ZdMEJe.fFgoGFLS', 5, 'y');

-- Inserting into qkc_sku
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU001', 'Chocolate Cake', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU002', 'Vanilla Cupcake', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU003', 'Red Velvet Cake', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU004', 'Blueberry Muffin', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU005', 'Cheesecake', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU006', 'Brownies', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU007', 'Lemon Bars', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU008', 'Pumpkin Pie', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU009', 'Apple Tart', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU010', 'Carrot Cake', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU011', 'Pecan Pie', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU012', 'Cinnamon Rolls', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU013', 'Banana Bread', 'loaf', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU014', 'Donuts', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU015', 'Scones', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU016', 'Apple Pie', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU017', 'Eclairs', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU018', 'Macarons', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU019', 'Fruit Tart', 'piece', 'y');
INSERT INTO qkc_sku (sku_cd, sku_name, unit_of_measurement, is_active) VALUES ('SKU020', 'Tiramisu', 'piece', 'y');

-- Inserting into qkc_raw_material
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT001', 'Flour', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT002', 'Sugar', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT003', 'Butter', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT004', 'Eggs', 'dozen', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT005', 'Vanilla Extract', 'ml', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT006', 'Baking Powder', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT007', 'Milk', 'L', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT008', 'Salt', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT009', 'Chocolate Chips', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT010', 'Honey', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT011', 'Yeast', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT012', 'Powdered Sugar', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT013', 'Cream Cheese', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT014', 'Peanut Butter', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT015', 'Cocoa Powder', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT016', 'Baking Soda', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT017', 'Coconut', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT018', 'Oats', 'kg', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT019', 'Maple Syrup', 'L', 'y');
INSERT INTO qkc_raw_material (material_cd, material_name, unit_of_measurement, is_active) VALUES ('MAT020', 'Almonds', 'kg', 'y');

-- Inserting into qkc_dispatch_type
INSERT INTO qkc_dispatch_type (dispatch_type_cd, dispatch_type_name, is_active) VALUES ('DIS001', 'Standard', 'y');
INSERT INTO qkc_dispatch_type (dispatch_type_cd, dispatch_type_name, is_active) VALUES ('DIS002', 'Express', 'y');
INSERT INTO qkc_dispatch_type (dispatch_type_cd, dispatch_type_name, is_active) VALUES ('DIS003', 'Overnight', 'y');
INSERT INTO qkc_dispatch_type (dispatch_type_cd, dispatch_type_name, is_active) VALUES ('DIS004', 'International', 'y');
INSERT INTO qkc_dispatch_type (dispatch_type_cd, dispatch_type_name, is_active) VALUES ('DIS005', 'Same Day', 'y');

-- Inserting into qkc_raw_material_list
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT001', 1000, 1, '01/15/2024', 1);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT002', 1000, 2, '01/16/2024', 1);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT003', 1000, 3, '01/17/2024', 1);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT004', 1000, 4, '01/18/2024', 1);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT005', 1000, 5, '01/19/2024', 2);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT006', 1000, 1, '01/20/2024', 2);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT007', 1000, 2, '01/21/2024', 2);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT008', 1000, 3, '01/22/2024', 2);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT009', 1000, 4, '01/23/2024', 3);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT010', 1000, 5, '01/24/2024', 3);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT011', 1000, 1, '01/25/2024', 3);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT012', 1000, 2, '01/26/2024', 3);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT013', 1000, 3, '01/27/2024', 4);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT014', 1000, 4, '01/28/2024', 4);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT015', 1000, 5, '01/29/2024', 4);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT016', 1000, 1, '01/30/2024', 4);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT017', 1000, 2, '02/01/2024', 5);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT018', 1000, 3, '02/02/2024', 5);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT019', 1000, 4, '02/03/2024', 5);
INSERT INTO qkc_raw_material_list (material_cd, quantity, user_id, date_receive, branch_id) VALUES ('MAT020', 1000, 5, '02/04/2024', 5);

-- Inserting into qkc_daily_planned_production
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/15/2024', 1, 'SKU001', 10, 'Planned');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/16/2024', 1, 'SKU002', 10, 'In Progress');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/17/2024', 1, 'SKU003', 10, 'Finished');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/18/2024', 1, 'SKU004', 10, 'Planned');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/19/2024', 2, 'SKU005', 10, 'In Progress');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/20/2024', 2, 'SKU006', 10, 'Finished');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/21/2024', 2, 'SKU007', 10, 'Planned');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/22/2024', 2, 'SKU008', 10, 'In Progress');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/23/2024', 3, 'SKU009', 10, 'Finished');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/24/2024', 3, 'SKU010', 10, 'Planned');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/25/2024', 3, 'SKU011', 10, 'In Progress');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/26/2024', 3, 'SKU012', 10, 'Finished');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/27/2024', 4, 'SKU013', 10, 'Planned');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/28/2024', 4, 'SKU014', 10, 'In Progress');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/29/2024', 4, 'SKU015', 10, 'Finished');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('01/30/2024', 4, 'SKU016', 10, 'Planned');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('02/01/2024', 5, 'SKU017', 10, 'In Progress');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('02/02/2024', 5, 'SKU018', 10, 'Finished');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('02/03/2024', 5, 'SKU019', 10, 'Planned');
INSERT INTO qkc_daily_planned_production (production_date, branch_id, sku_cd, quantity, status) VALUES ('02/04/2024', 5, 'SKU020', 10, 'In Progress');

-- Inserting into qkc_production_materials
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (1, 1, 'MAT001', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (2, 2, 'MAT002', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (3, 3, 'MAT003', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (4, 4, 'MAT004', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (5, 5, 'MAT005', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (6, 6, 'MAT006', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (7, 7, 'MAT007', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (8, 8, 'MAT008', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (9, 9, 'MAT009', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (10, 10, 'MAT010', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (11, 11, 'MAT011', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (12, 12, 'MAT012', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (13, 13, 'MAT013', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (14, 14, 'MAT014', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (15, 15, 'MAT015', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (16, 16, 'MAT016', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (17, 17, 'MAT017', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (18, 18, 'MAT018', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (19, 19, 'MAT019', 100);
INSERT INTO qkc_production_materials (dpp_id, material_list_id, material_cd, quantity_to_use) VALUES (20, 20, 'MAT020', 100);

-- Inserting into qkc_finished_product_list
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (1, 'SKU001', 100, 1, '01/15/2024');
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (2, 'SKU002', 150, 1, '01/16/2024');
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (3, 'SKU003', 200, 1, '01/17/2024');
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (4, 'SKU004', 250, 1, '01/18/2024');
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (5, 'SKU005', 300, 2, '01/19/2024');
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (6, 'SKU006', 350, 2, '01/20/2024');
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (7, 'SKU007', 400, 2, '01/21/2024');
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (8, 'SKU008', 450, 2, '01/22/2024');
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (9, 'SKU009', 500, 3, '01/23/2024');
INSERT INTO qkc_finished_product_list (dpp_id, sku_cd, quantity, branch_id, date_finished)
VALUES (10, 'SKU010', 550, 3, '01/24/2024');

-- Inserting into qkc_dispatch_tracking
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS001', 1, 100, 1, 'Manila', '01/15/2024');
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS002', 2, 150, 1, 'Manila', '01/16/2024');
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS003', 3, 200, 1, 'Manila', '01/17/2024');
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS004', 4, 250, 1, 'Manila', '01/18/2024');
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS005', 5, 300, 2, 'Makati', '01/19/2024');
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS001', 6, 350, 2, 'Makati', '01/20/2024');
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS002', 7, 400, 2, 'Makati', '01/21/2024');
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS003', 8, 450, 2, 'Makati', '01/22/2024');
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS004', 9, 500, 3, 'Cebu City', '01/23/2024');
INSERT INTO qkc_dispatch_tracking (dispatch_type_cd, fpl_id, quantity, branch_id, destination, dispatch_date) VALUES ('DIS005', 10, 550, 3, 'Cebu City', '01/24/2024');
