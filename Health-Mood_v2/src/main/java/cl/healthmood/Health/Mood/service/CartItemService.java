package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.CartItem;
import java.util.List;
import java.util.Optional;

public interface CartItemService {

    List<CartItem> getAllCartItems();

    Optional<CartItem> getCartItemById(Integer id);

    CartItem saveCartItem(CartItem cartItem);

    void deleteCartItem(Integer id);

}