package com.chat.service;

import com.chat.dao.entity.AccountEntity;
import com.chat.dto.request.CreateAccountRequest;

public interface AccountService {
    void createAccount(CreateAccountRequest accountRequest);
}
