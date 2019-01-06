package com.nilin.repositories.profilerepository;

import com.nilin.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<Test, Long> {
}
