package DAO;

public class DBInfo {
    public static final String DB_URL = "java:/comp/env/jdbc/market";
    public static final String DB_USER = "sa";
    public static final String DB_PASSWORD = "";
    public static final String INITIAL_SQL = "DROP TABLE IF EXISTS cart;\n" +
            "DROP TABLE IF EXISTS orders;\n" +
            "DROP TABLE IF EXISTS product;\n" +
            "DROP TABLE IF EXISTS customer;\n" +

            "CREATE TABLE customer (id BIGINT PRIMARY KEY AUTO_INCREMENT, \n" +
            "                       userEmail VARCHAR(255) NOT NULL UNIQUE,\n" +
            "                       userPassword VARCHAR(255) NOT NULL,\n" +
            "                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
            "                       updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);\n" +

            "/* product status is 0 or 1. 0 = active, 1 = inactive*/\n" +
            "CREATE TABLE PRODUCT (id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
            "                     productName VARCHAR(255) NOT NULL,\n" +
            "                     productPrice BIGINT NOT NULL,\n" +
            "                     productDescription VARCHAR(255) NOT NULL,\n" +
            "                     productStock BIGINT NOT NULL,\n" +
            "                     productStatus BIGINT NOT NULL,\n" +
            "                     ownerID BIGINT NOT NULL,\n" +
            "                     createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
            "                     updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n" +
            "                     FOREIGN KEY (ownerID) REFERENCES customer(id) ON DELETE CASCADE);\n" +

            "CREATE TABLE cart (id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
            "                  userID BIGINT NOT NULL,\n" +
            "                  productID BIGINT NOT NULL,\n" +
            "                  quantity BIGINT NOT NULL,\n" +
            "                  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
            "                  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n" +
            "                  FOREIGN KEY (userID) REFERENCES customer(id) ON DELETE CASCADE,\n" +
            "                  FOREIGN KEY (productID) REFERENCES PRODUCT(id) ON DELETE CASCADE);\n" +

            "CREATE TABLE orders (id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
            "                    userID BIGINT NOT NULL,\n" +
            "                    productID BIGINT NOT NULL,\n" +
            "                    quantity BIGINT NOT NULL,\n" +
            "                    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
            "                    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n" +
            "                    FOREIGN KEY (userID) REFERENCES customer(id) ON DELETE CASCADE);\n" +

            "INSERT INTO customer (userEmail, userPassword)\n" +
            "VALUES ('dongwon@gmail.com', 'qwer1234'),\n" +
            "       ('dongwon@yahoo.com', 'asdf1234'),\n" +
            "       ('dongwon@bing.com', 'password');\n" +

            "INSERT INTO PRODUCT (productName, productPrice, productDescription, productStock, productStatus, ownerID)\n"
            +
            "VALUES ('りんご', 500, '長野県の高原地帯で収穫されたりんごです。', 100, 0, 1),\n" +
            "       ('バゲットパン', 800, 'フランスか直輸入した熱々ふわふわなパンです。', 50, 0, 1),\n" +
            "       ('うに', 4000, '北海道の海で獲った地域特産品です。', 30, 0, 2),\n" +
            "       ('さくらんぼ', 1200, '山梨県の甘くてジューシーなさくらんぼです。', 80, 0, 1),\n" +
            "       ('煎茶', 1000, '京都で育てられたおいしい煎茶です。', 60, 0, 3),\n" +
            "       ('寿司', 1500, '新鮮なネタが自慢の本格的な寿司です。', 40, 0, 2),\n" +
            "       ('とんかつ', 1300, 'ジューシーでサクサクなとんかつです。', 70, 0, 3),\n" +
            "       ('そば', 900, '長野県産の風味豊かなそばです。', 90, 0, 1),\n" +
            "       ('たこ焼き', 700, '大阪の名物たこ焼きです。', 120, 0, 3),\n" +
            "       ('富士山ビール', 600, '富士山の清らかな水で醸造されたビールです。', 200, 0, 1),\n" +
            "       ('和牛ステーキ', 3000, '上質な国産和牛のステーキです。', 25, 0, 2),\n" +
            "       ('かき氷', 400, '夏の風物詩、かき氷です。', 100, 0, 1),\n" +
            "       ('たい焼き', 300, 'アツアツのたい焼きです。', 80, 0, 3),\n" +
            "       ('納豆', 200, '日本の伝統食品、納豆です。', 150, 0, 2),\n" +
            "       ('ユニコーンプリン', 1000, '幻想的なユニコーンプリンです。', 10, 0, 1),\n" +
            "       ('さば寿司', 850, '新鮮なサバを使ったサバ寿司です。', 60, 0, 3),\n" +
            "       ('わらび餅', 500, '滑らかな食感のわらび餅です。', 70, 0, 2),\n" +
            "       ('ふわふわオムレツ', 600, 'ふわふわでとろけるようなオムレツです。', 40, 0, 1),\n" +
            "       ('梅干し', 300, '自家製の梅干しです。', 200, 0, 2),\n" +
            "       ('クリームパン', 500, 'バタークリームがたっぷり入ったパンです。', 50, 1, 1);";
}
