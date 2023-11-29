-- Place your queries here. Docs available https://www.hugsql.org/


-- :name create-product! :! :n
-- :doc creates a new product record
insert into products(name,price) values(:name,:price);


-- :name list-all-products :? :*
select * from products;


-- :name product-by-id :? :1
select * from products where id=:id

-- :name add-product! :! :n
insert into products (name,price,category,count) values (:name,:price,:category,:count);

-- :name delete-product! :? :1
delete from products where id=:id;

 -- :name update-product! :! :n
 update products set name=:name, price=:price,category=:category,count=:count where id=:id;


