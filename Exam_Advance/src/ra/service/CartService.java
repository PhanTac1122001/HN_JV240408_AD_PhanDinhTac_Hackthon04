package ra.service;

import ra.model.CartItem;

public interface CartService extends IGenericService<CartItem,Integer>{
    void deleteAllCart();
}
