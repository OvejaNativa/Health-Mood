package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.CartItem;
import cl.healthmood.Health.Mood.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public Optional<CartItem> getCartItemById(Integer id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        if (cartItem.getQuantity() == null || cartItem.getQuantity() < 1) {
            cartItem.setQuantity(1);
        }
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Integer id) {
        cartItemRepository.deleteById(id);
    }
}