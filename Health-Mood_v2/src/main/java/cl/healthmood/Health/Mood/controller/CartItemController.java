package cl.healthmood.Health.Mood.controller;

import cl.healthmood.Health.Mood.model.CartItem;
import cl.healthmood.Health.Mood.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Integer id) {
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        if (cartItem.isPresent()) {
            return ResponseEntity.ok(cartItem.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.saveCartItem(cartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Integer id, @RequestBody CartItem cartItem) {
        Optional<CartItem> existingCartItem = cartItemService.getCartItemById(id);
        if (existingCartItem.isPresent()) {
            cartItem.setCartItemId(id);
            CartItem updatedCartItem = cartItemService.saveCartItem(cartItem);
            return ResponseEntity.ok(updatedCartItem);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Integer id) {
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        if (cartItem.isPresent()) {
            cartItemService.deleteCartItem(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}