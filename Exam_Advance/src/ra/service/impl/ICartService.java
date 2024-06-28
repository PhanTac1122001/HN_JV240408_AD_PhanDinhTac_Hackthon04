package ra.service.impl;

import ra.model.CartItem;
import ra.service.CartService;

import java.util.ArrayList;
import java.util.List;

public class ICartService implements CartService {
    public static List<CartItem> cartItemList=new ArrayList<>();
    @Override
    public List<CartItem> getAll() {
        return cartItemList;
    }

    @Override
    public void save(CartItem cartItem) {
        if (findById(cartItem.getCartItemId()) == null) {
            cartItemList.add(cartItem);
        } else {
            cartItemList.set(cartItemList.indexOf(findById(cartItem.getCartItemId())), cartItem);
        }
    }

    @Override
    public CartItem findById(Integer integer) {
        for (CartItem c : cartItemList) {
            if (c.getCartItemId()==integer) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer integer) {
        if (findById(integer) != null) {
            cartItemList.remove(findById(integer));
        } else {
            System.err.println("Không có sản phẩm này");
        }
    }
    @Override
    public void deleteAllCart(){
        cartItemList.clear();
    }
}
