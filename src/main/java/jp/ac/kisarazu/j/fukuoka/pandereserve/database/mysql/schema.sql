-- 正規化されたテーブルの作成

CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(32) NOT NULL,
                                     password TEXT NOT NULL,
                                     salt TEXT NOT NULL,
                                     created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                     updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS bakery_items (
                                            bakery_item_id INT AUTO_INCREMENT PRIMARY KEY,
                                            bakery_item_name VARCHAR(255) NOT NULL,
                                            bakery_item_price INT NOT NULL,
                                            bakery_item_description VARCHAR(255) NOT NULL,
                                            bakery_item_image LONGTEXT NOT NULL,
                                            status ENUM('AVAILABLE', 'UNAVAILABLE') NOT NULL DEFAULT 'AVAILABLE',
                                            created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                            updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS reservations (
                                            reservation_id VARCHAR(36) NOT NULL PRIMARY KEY,
                                            customer_name VARCHAR(64) NOT NULL,
                                            customer_phone_number VARCHAR(11) NOT NULL,
                                            receive_time DATETIME,
                                            status ENUM('RECEIVED', 'UNRECEIVED') NOT NULL DEFAULT 'UNRECEIVED',
                                            created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                            updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS reservation_details (
                                                   reservation_detail_id INT AUTO_INCREMENT PRIMARY KEY,
                                                   reservation_id VARCHAR(36) NOT NULL,
                                                   bakery_item_id INT,
                                                   quantity INT NOT NULL,
                                                   created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                                   updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 外部キーの設定
ALTER TABLE reservation_details
    ADD CONSTRAINT fk_reservation_id FOREIGN KEY (reservation_id)
        REFERENCES reservations(reservation_id) ON DELETE CASCADE;

ALTER TABLE reservation_details
    ADD CONSTRAINT fk_bakery_item_id FOREIGN KEY (bakery_item_id)
        REFERENCES bakery_items(bakery_item_id) ON DELETE SET NULL;