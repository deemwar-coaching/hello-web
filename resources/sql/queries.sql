-- Place your queries here. Docs available https://www.hugsql.org/


-- :name create-product! :! :n
-- :doc creates a new product record
insert into products(name,price) values(:name,:price);


-- :name list-all-products :? :*
select * from products;


-- :name product-by-id :? :1
select * from products where id=:id
