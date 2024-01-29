ALTER TABLE budget
    ADD CONSTRAINT fk_budget_customer FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE budget_materials
    ADD CONSTRAINT fk_budget_materials_budget FOREIGN KEY (budget_id) REFERENCES budget (id);

ALTER TABLE budget_materials
    ADD CONSTRAINT fk_budget_materials_material FOREIGN KEY (material_id) REFERENCES material (id);

ALTER TABLE budget_services
    ADD CONSTRAINT fk_budget_services_budget FOREIGN KEY (budget_id) REFERENCES budget (id);

ALTER TABLE budget_services
    ADD CONSTRAINT fk_budget_services_service FOREIGN KEY (service_id) REFERENCES service (id);

ALTER TABLE work_order
    ADD CONSTRAINT fk_work_order_customer FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE work_order_materials
    ADD CONSTRAINT fk_work_order_materials_budget FOREIGN KEY (budget_id) REFERENCES budget (id);

ALTER TABLE work_order_materials
    ADD CONSTRAINT fk_work_order_materials_material FOREIGN KEY (material_id) REFERENCES material (id);

ALTER TABLE work_order_services
    ADD CONSTRAINT fk_work_order_services_budget FOREIGN KEY (budget_id) REFERENCES budget (id);

ALTER TABLE work_order_services
    ADD CONSTRAINT fk_work_order_services_service FOREIGN KEY (service_id) REFERENCES service (id);

ALTER TABLE done
    ADD CONSTRAINT fk_done_customer FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE done_materials
    ADD CONSTRAINT fk_done_materials_budget FOREIGN KEY (budget_id) REFERENCES budget (id);

ALTER TABLE done_materials
    ADD CONSTRAINT fk_done_materials_material FOREIGN KEY (material_id) REFERENCES material (id);

ALTER TABLE done_services
    ADD CONSTRAINT fk_done_services_budget FOREIGN KEY (budget_id) REFERENCES budget (id);

ALTER TABLE done_services
    ADD CONSTRAINT fk_done_services_service FOREIGN KEY (service_id) REFERENCES service (id);

ALTER TABLE user_group
    ADD CONSTRAINT fk_user_group_user FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_group
    ADD CONSTRAINT fk_user_group_group FOREIGN KEY (group_id) REFERENCES `group` (id);

ALTER TABLE group_permission
    ADD CONSTRAINT fk_group_permission_group FOREIGN KEY (group_id) REFERENCES `group` (id);

ALTER TABLE user
    ADD CONSTRAINT fk_user_group FOREIGN KEY (main_group_id) REFERENCES `group` (id);