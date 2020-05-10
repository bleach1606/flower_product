package ptit.edu.btl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.People;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.PeopleRepository;
import ptit.edu.btl.repository.UsersRepository;
import ptit.edu.btl.session.CustomUserDetails;


@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PeopleRepository peopleRepository) {
        this.usersRepository = usersRepository;
        this.peopleRepository = peopleRepository;
    }

    @Override
    public Users create(Users entity) throws BTLException {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
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
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return usersRepository.save(entity);
    }

    @Override
    public Users findById(int fiIdHS) {
        Users users = usersRepository.findById(fiIdHS);
        if (peopleRepository.findById(users.getId()) != null) {
            People people = peopleRepository.findById(users.getId());
            users.setPeople(people);
        }
        return users;
    }

    @Override
    public Users update(Users entity) {
        //todo kiểm tra sửa đổi okk thì mới được update
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
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

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        Users user = usersRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new CustomUserDetails(user);
    }
}
