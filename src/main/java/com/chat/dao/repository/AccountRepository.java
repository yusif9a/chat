package com.chat.dao.repository;

import com.chat.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<AccountEntity,Long> {
}
