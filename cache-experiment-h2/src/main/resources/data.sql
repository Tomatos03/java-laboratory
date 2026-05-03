-- 插入用户数据（10000条）
INSERT INTO t_user (username, email, phone, address)
SELECT
    'user_' || i,
    'user_' || i || '@example.com',
    '138' || LPAD(i, 8, '0'),
    'Address ' || i || ', Street ' || (i % 100)
FROM SYSTEM_RANGE(1, 10000) AS s(i);

-- 为每个用户插入3-5条订单
INSERT INTO t_order (user_id, product_name, amount, create_time)
SELECT
    u.id,
    CASE MOD(u.id + o.i, 5)
        WHEN 0 THEN 'Laptop'
        WHEN 1 THEN 'Phone'
        WHEN 2 THEN 'Tablet'
        WHEN 3 THEN 'Headphones'
        WHEN 4 THEN 'Keyboard'
    END,
    ROUND(RAND() * 1000 + 50, 2),
    TIMESTAMPADD('DAY', -MOD(u.id + o.i, 365), CURRENT_TIMESTAMP)
FROM t_user u
CROSS JOIN SYSTEM_RANGE(1, 3) AS o(i)
WHERE MOD(u.id, 10) != 0  -- 90%的用户有订单
UNION ALL
SELECT
    u.id,
    CASE MOD(u.id, 5)
        WHEN 0 THEN 'Monitor'
        WHEN 1 THEN 'Mouse'
        WHEN 2 THEN 'Webcam'
        WHEN 3 THEN 'Speaker'
        WHEN 4 THEN 'Charger'
    END,
    ROUND(RAND() * 500 + 20, 2),
    TIMESTAMPADD('DAY', -MOD(u.id, 365), CURRENT_TIMESTAMP)
FROM t_user u
WHERE MOD(u.id, 5) = 0;  -- 20%的用户有额外订单
