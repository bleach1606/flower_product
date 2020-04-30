package ptit.edu.btl.service;

import ptit.edu.btl.entity.Users;
import ptit.edu.btl.exception.BTLException;

public interface UsersService {
    Users create(Users entity) throws BTLException;

    Users save(Users entity) throws BTLException;

    Users findById(int fiIdHS) throws BTLException;

    Users update(Users entity) throws BTLException;

    Users findByUsername(String username) throws BTLException;

    void delete(int id) throws BTLException;
}
