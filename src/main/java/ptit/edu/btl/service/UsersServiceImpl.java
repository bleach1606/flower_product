package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.UsersRepository;

import java.util.Objects;


@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users create(Users entity) throws BTLException {
        if (entity.getRole().isEmpty() || entity.getRole().equals(null)) {
            entity.setRole(Constant.Role.CUSTOMER.getRole());
        }
        //todo check tài khoản hợp lý password hợp lệ
        if (Objects.nonNull(findByUsername(entity.getUsername()))) {
            throw new BTLException("Tài khoản đã tồn tại !!!");
        }
        return usersRepository.save(entity);
    }

    @Override
    public Users save(Users entity) {
        return usersRepository.save(entity);
    }

    @Override
    public Users findById(int fiIdHS) {
        return usersRepository.findById(fiIdHS);
    }

    @Override
    public Users update(Users entity) {
        //todo kiểm tra sửa đổi okk thì mới được update
        return usersRepository.save(entity);
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public void delete(int id) {
        usersRepository.delete(findById(id));
    }
}
