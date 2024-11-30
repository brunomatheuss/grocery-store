# grocery-store

# Instructions

### Lombok plugin is required

### The sent wiremock must be running

### The file *grocery-store.postman_collection.json* contais the postman collection.

### Endpoints

#### Products
Used to retrieve product information.

* **[GET] v1/products**: retrieve all products available on wiremock;
* **[GET] v1/products/{productID}**: retrieve information about the product for which the id was entered;

#### Cart
Used to manipulate cart information.

* **[POST] v1/carts**: opens the cart and make it available to add products; 
* **[GET] v1/carts**: retrieve all carts information;
* **[GET] v1/carts/{cartId}**: retrieve information about the product for which the id was entered; <br/>
*Path example*: /v1/carts/1
* **[POST] v1/carts/addProduct**: add the product which the id was entered to the cart for which the id was entered; <br/>
*Body example*: 
```
    {
        "cartId": 1,
        "productId": "4MB7UfpTQs",
        "quantity": 2
    }
```
* **[POST] v1/carts/closeCart**: close the cart which the id was entered; <br/>
*Body example*:
```
    {
        "cartId": 1
    }
```