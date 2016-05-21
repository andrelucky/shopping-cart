# shopping-cart

Develop with :
<ul>
<li>Spring Boot</li>
<li>Spring MVC</li>
<li>Spring Data JPA</li>
<li>PostgreSql</li>
<li>FlywayDB</li>
</ul>

Deployment :
http://shopping-cart-andrelucky.herokuapp.com
<pre>
get     /api/product        -> list product
get     /api/discount       -> list discount
get     /api/cart           -> list cart
post    /api/product        -> add product to cart      {"productId":"c53262fc-f5b4-d7de-c794-3f2187afd0f9","qty":1}
delete  /api/cart/{id}      -> delete product in cart with cart detail id
post    /api/cart/checkout  -> checkout                 {"name":"andre","email":"andreluckyanto@test.com","address":"depok","discountCode":"DISKON200"}
</pre>
