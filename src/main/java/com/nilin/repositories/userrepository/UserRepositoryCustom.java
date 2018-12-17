package com.nilin.repositories.userrepository;

import com.nilin.model.User;

public interface UserRepositoryCustom {

    User findByUsername(String username);
}
