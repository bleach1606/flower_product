package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.entity.CartDetail;
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
    public CartDetail create(CartDetail entity) throws BTLException {
        return cartRepository.save(entity);
    }

    @Override
    public Optional<CartDetail> findById(int id) throws BTLException {
        return cartRepository.findById(id);
    }

    @Override
    public CartDetail update(CartDetail entity) throws BTLException {
        return cartRepository.save(entity);
    }

    @Override
    public List<CartDetail> findALl() throws BTLException {
        return cartRepository.findAll();
    }

    @Override
    public List<CartDetail> findByOrderId(int id) throws BTLException {
//        return cartRepository.findByOrderId(id);
        return null;
    }

    @Override
    public void delete(int id) throws BTLException {
        cartRepository.deleteById(id);
    }
}
