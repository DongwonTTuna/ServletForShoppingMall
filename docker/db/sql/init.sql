DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  userEmail VARCHAR(255) NOT NULL UNIQUE,
  userPassword VARCHAR(255) NOT NULL,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


CREATE TABLE product (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  productName VARCHAR(255) NOT NULL,
  productPrice BIGINT NOT NULL,
  productDescription VARCHAR(255) NOT NULL,
  productStock BIGINT NOT NULL,
  productStatus BIGINT NOT NULL,
  ownerID BIGINT NOT NULL,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (ownerID) REFERENCES customer(id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


CREATE TABLE cart (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  userID BIGINT NOT NULL,
  productID BIGINT NOT NULL,
  quantity BIGINT NOT NULL,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (userID) REFERENCES customer(id) ON DELETE CASCADE,
  FOREIGN KEY (productID) REFERENCES product(id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


CREATE TABLE orders (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  userID BIGINT NOT NULL,
  productID BIGINT NOT NULL,
  quantity BIGINT NOT NULL,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (userID) REFERENCES customer(id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


INSERT INTO customer (userEmail, userPassword)
VALUES ('dongwon@gmail.com', 'qwer1234'),
       ('dongwon@yahoo.com', 'asdf1234'),
       ('dongwon@bing.com', 'password');

INSERT INTO product (productName, productPrice, productDescription, productStock, productStatus, ownerID)
VALUES ('りんご', 500, '長野県の高原地帯で収穫されたりんごです。', 100, 0, 1),
       ('バゲットパン', 800, 'フランスか直輸入した熱々ふわふわなパンです。', 50, 0, 1),
       ('うに', 4000, '北海道の海で獲った地域特産品です。', 30, 0, 2),
       ('さくらんぼ', 1200, '山梨県の甘くてジューシーなさくらんぼです。', 80, 0, 1),
       ('煎茶', 1000, '京都で育てられたおいしい煎茶です。', 60, 0, 3),
       ('寿司', 1500, '新鮮なネタが自慢の本格的な寿司です。', 40, 0, 2),
       ('とんかつ', 1300, 'ジューシーでサクサクなとんかつです。', 70, 0, 3),
       ('そば', 900, '長野県産の風味豊かなそばです。', 90, 0, 1),
       ('たこ焼き', 700, '大阪の名物たこ焼きです。', 120, 0, 3),
       ('富士山ビール', 600, '富士山の清らかな水で醸造されたビールです。', 200, 0, 1),
       ('和牛ステーキ', 3000, '上質な国産和牛のステーキです。', 25, 0, 2),
       ('かき氷', 400, '夏の風物詩、かき氷です。', 100, 0, 1),
       ('たい焼き', 300, 'アツアツのたい焼きです。', 80, 0, 3),
       ('納豆', 200, '日本の伝統食品、納豆です。', 150, 0, 2),
       ('ユニコーンプリン', 1000, '幻想的なユニコーンプリンです。', 10, 0, 1),
       ('さば寿司', 850, '新鮮なサバを使ったサバ寿司です。', 60, 0, 3),
       ('わらび餅', 500, '滑らかな食感のわらび餅です。', 70, 0, 2),
       ('ふわふわオムレツ', 600, 'ふわふわでとろけるようなオムレツです。', 40, 0, 1),
       ('梅干し', 300, '自家製の梅干しです。', 200, 0, 2),
       ('クリームパン', 500, 'バタークリームがたっぷり入ったパンです。', 50, 1, 1);
