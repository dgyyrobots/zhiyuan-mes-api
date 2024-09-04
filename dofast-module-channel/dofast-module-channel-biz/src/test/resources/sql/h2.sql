-- 将该建表 SQL 语句，添加到 dofast-module-channel-biz 模块的 test/resources/sql/create_tables.sql 文件里
CREATE TABLE IF NOT EXISTS "channel_shop" (
    "id" varchar NOT NULL,
    "display_name" varchar NOT NULL,
    "name" varchar NOT NULL,
    "shop_nick" varchar NOT NULL,
    "shop_url" varchar NOT NULL,
    "shop_id" varchar NOT NULL,
    "shop_code" varchar NOT NULL,
    "channel" varchar NOT NULL,
    "sort_code" int NOT NULL,
    "deleted" bit NOT NULL DEFAULT FALSE,
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "creator" varchar DEFAULT '',
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "updater" varchar DEFAULT '',
    "last_time" varchar NOT NULL,
    "remark" varchar NOT NULL,
    "tenant_id" bigint NOT NULL,
    PRIMARY KEY ("id")
) COMMENT '渠道店铺';

-- 将该删表 SQL 语句，添加到 dofast-module-channel-biz 模块的 test/resources/sql/clean.sql 文件里
DELETE FROM "channel_shop";
