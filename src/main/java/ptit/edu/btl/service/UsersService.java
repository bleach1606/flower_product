package ptit.edu.btl.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.exception.BTLException;

public interface UsersService {
    Users create(Users entity) throws BTLException;

    Users save(Users entity) throws BTLException;

    Users findById(int fiIdHS) throws BTLException;

    Users update(Users entity) throws BTLException;

    Users findByUsername(String username) throws BTLException;

    void delete(int id) throws BTLException;

    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
