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

 -- :name update-product-price! :! :n
 update products set  price = :price  where id = :id;

  -- :name update-product-count! :! :n
 update products set  count = :count  where id = :id;

 -- :name list-users :? :*
 select * from users order by id;

 -- :name add-new-user! :! :n
 insert into users (user_name,password,role) values (:user_name,:password,:role);

-- :name delete-user! :? :1
delete from users where id=:id;

-- :name update-user-password! :! :1
update users set  password = :password  where id = :id;

-- :name update-user-role! :! :1
update users set  role = :role  where id = :id;

--:name encrypt-password! :1 :1
update users set password= :password where id= :id;

--:name find-user! :? :1
select * from users where user_name= :user_name;

--:name logged-in-user! :? :1
select user_name,role from users where user_name =:user_name;

