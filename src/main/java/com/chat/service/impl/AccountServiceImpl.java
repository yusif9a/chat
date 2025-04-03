package com.chat.service.impl;

import com.chat.dao.entity.AccountEntity;
import com.chat.dao.entity.UserEntity;
import com.chat.dao.repository.AccountRepository;
import com.chat.dao.repository.UserRepository;
import com.chat.dto.request.CreateAccountRequest;
import com.chat.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public void createAccount(CreateAccountRequest accountRequest) {

        var user = userRepository.findByEmail(accountRequest.getEmail()).orElseThrow();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(accountRequest.getFirstname());
        accountEntity.setLastname(accountRequest.getLastname());
        accountEntity.setGender(accountRequest.getGender());
        accountEntity.setAge(accountRequest.getAge());
        accountEntity.setUser(user);

        accountRepository.save(accountEntity);
    }
}
