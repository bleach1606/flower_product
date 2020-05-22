package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.entity.Cart;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart create(Cart entity) throws BTLException {
        return cartRepository.save(entity);
    }

    @Override
    public Optional<Cart> findById(int id) throws BTLException {
        return cartRepository.findById(id);
    }

    @Override
    public Cart update(Cart entity) throws BTLException {
        return cartRepository.save(entity);
    }

    @Override
    public List<Cart> findALl() throws BTLException {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> findByOrderId(int id) throws BTLException {
        return cartRepository.findByOrderId(id);
    }

    @Override
    public void delete(int id) throws BTLException {
        cartRepository.deleteById(id);
    }
}
