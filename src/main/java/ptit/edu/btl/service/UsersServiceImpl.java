package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.PeopleRepository;
import ptit.edu.btl.repository.UsersRepository;


@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PeopleRepository peopleRepository;

    public UsersServiceImpl(UsersRepository usersRepository, PeopleRepository peopleRepository) {
        this.usersRepository = usersRepository;
        this.peopleRepository = peopleRepository;
    }

    @Override
    public Users create(Users entity) throws BTLException {

        if (entity.getRole() == null) {
            entity.setRole(Constant.Role.CUSTOMER.getRole());
        }
        entity.setFiActive(true);
        //todo check tài khoản hợp lý password hợp lệ
        Users temp = findByUsername(entity.getUsername());
        if ( temp != null) {
            throw new BTLException("Tài khoản đã tồn tại !!!");
        }
        entity.setPeople(peopleRepository.save(entity.getPeople()));
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
        entity.setPeople(peopleRepository.save(entity.getPeople()));
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
