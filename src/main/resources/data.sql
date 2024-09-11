INSERT INTO `sivillage`.`brand`
(`brand_code`,
 `main_name`,
 `sub_name`)
VALUES ("b123",
        "main1",
        "sub1");

INSERT INTO `sivillage`.`product`
(`product_code`,
 `brand_code`,
 `product_name`,
 `is_on_sale`,
 `detail`,
 `standard_price`)
VALUES ("a123",
        "b123",
        "쉐보레",
        true,
        "좋은상품입니다.",
        36000);

INSERT INTO `sivillage`.`hashtag`
    (`value`)
VALUES ("가을에 어울려요");

INSERT INTO `sivillage`.`hashtag`
    (`value`)
VALUES ("겨울에 어울려요");

INSERT INTO `sivillage`.`product_hashtag`
(`hashtag_id`,
 `product_code`)
VALUES ("1",
        "a123");

--
--
--
-- INSERT INTO `sivillage`.`color`
--     (`value`)
-- VALUES ("실버");
--
-- INSERT INTO `sivillage`.`size`
--     (`value`)
-- VALUES ("FREE");
--
-- INSERT INTO `sivillage`.`etc`
-- (`name`,
--  `value`)
-- VALUES ("소재",
--         "소가죽");


