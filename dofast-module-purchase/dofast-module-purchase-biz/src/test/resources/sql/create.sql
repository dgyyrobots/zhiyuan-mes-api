-- 将该建表 SQL 语句，添加到 dofast-module-purchase-biz 模块的 test/resources/sql/create_tables.sql 文件里
CREATE TABLE IF NOT EXISTS "purchase_goods" (
    "id" int NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "purchase_id" int,
    "goods_number" varchar,
    "goods_name" varchar,
    "goods_specs" varchar,
    "company" int,
    "monovalent" varchar,
    "quantity" int,
    "taxes" varchar,
    "total" varchar,
    "category_name" varchar,
    "brand_name" varchar,
    "creator" bigint DEFAULT '',
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updater" bigint DEFAULT '',
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "deleted" bit NOT NULL DEFAULT FALSE,
    "tenant_id" bigint NOT NULL,
    PRIMARY KEY ("id")
) COMMENT '采购商品明细';

-- 将该建表 SQL 语句，添加到 dofast-module-purchase-biz 模块的 test/resources/sql/create_tables.sql 文件里
CREATE TABLE IF NOT EXISTS "purchase_invoice" (
    "id" int NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "purchase_order_num" varchar,
    "shipment_num" varchar,
    "purchase_id" int,
    "supplier_id" int,
    "payment_type" int,
    "is_return" int,
    "supplieruser" varchar,
    "supplierphone" varchar,
    "total_price" varchar,
    "is_warehousing" int,
    "creator" bigint DEFAULT '',
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updater" bigint DEFAULT '',
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "deleted" bit NOT NULL DEFAULT FALSE,
    "tenant_id" bigint NOT NULL,
    PRIMARY KEY ("id")
) COMMENT '采购入库单';

-- 将该建表 SQL 语句，添加到 dofast-module-purchase-biz 模块的 test/resources/sql/create_tables.sql 文件里
CREATE TABLE IF NOT EXISTS "purchase_order" (
    "supplier_id" int,
    "supplier_contact" varchar,
    "supplier_phone" varchar,
    "purchase_amount" varchar,
    "id" int NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "po_no" varchar,
    "payment_type" int,
    "warehousing_type" int,
    "return_goods" int,
    "process_type" int,
    "remarks" varchar,
    "creator" bigint DEFAULT '',
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updater" bigint DEFAULT '',
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "deleted" bit NOT NULL DEFAULT FALSE,
    "tenant_id" bigint NOT NULL,
    PRIMARY KEY ("id")
) COMMENT '采购订单';

-- 将该建表 SQL 语句，添加到 dofast-module-purchase-biz 模块的 test/resources/sql/create_tables.sql 文件里
CREATE TABLE IF NOT EXISTS "purchase_return" (
    "id" int NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "purchase_id" int,
    "purchase_order_num" varchar,
    "shipment_num" varchar,
    "return_bonus" varchar,
    "is_return" int,
    "supplier_id" int,
    "payment_type" int,
    "supplieruser" varchar,
    "supplierphone" varchar,
    "creator" bigint DEFAULT '',
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updater" bigint DEFAULT '',
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "deleted" bit NOT NULL DEFAULT FALSE,
    "tenant_id" bigint NOT NULL,
    PRIMARY KEY ("id")
) COMMENT '采购退货表';