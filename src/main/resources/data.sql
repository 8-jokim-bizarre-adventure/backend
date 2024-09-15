INSERT INTO `sivillage`.`brand`
(`brand_code`,
 `main_name`,
 `sub_name`)
VALUES ("b123",
        "main1",
        "sub1");

INSERT INTO `sivillage`.`product`
(`product_code`,
 `product_name`,
 `is_on_sale`,
 `detail`,
 `standard_price`)
VALUES ("a123",
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

INSERT INTO `sivillage`.`size`
    (`value`)
VALUES ("big");

INSERT INTO `sivillage`.`color`
    (`value`)
VALUES ("red");

INSERT INTO `sivillage`.`etc`
(`value`,
 `name`)
VALUES ("feathers",
        "재질");

INSERT INTO `sivillage`.`product_option`
(`price`,
 `stock`,
 `color_id`,
 `etc_id`,
 `size_id`,
 `product_code`,
 `product_option_code`)
VALUES (3600,
        3,
        1,
        1,
        1,
        "a123",
        "po123");


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


