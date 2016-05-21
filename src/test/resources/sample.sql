delete from cart where id = '12345';
delete from  cart_detail where id in ('34567','56789');

INSERT INTO cart VALUES('12345','E63C65C649F9167C3AD8421F88051EF6',null,null,null,null,150000.00,150000.00,1,'2016-05-21 19:50:38.724','2016-05-21 19:50:46.6');
INSERT INTO cart_detail VALUES ('34567', '12345', 'c53262fc-f5b4-d7de-c794-3f2187afd0f9', 50000.00, 2, '2016-05-21 19:50:38.742');
INSERT INTO cart_detail VALUES ('56789', '12345', 'c53262fc-f5b4-d7de-c794-3f2187afd0f9', 50000.00, 1, '2016-05-21 19:50:46.602');