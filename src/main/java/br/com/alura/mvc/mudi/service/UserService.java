package br.com.alura.mvc.mudi.service;

import br.com.alura.mvc.mudi.model.User;

@FunctionalInterface
public interface UserService {

    User findByUsername(String username);
}
