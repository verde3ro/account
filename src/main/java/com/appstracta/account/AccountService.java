package com.appstracta.account;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

	private final IAccountRepository repository;

	@Override
	public List<Account> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Account> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Account save(Account account) {
		return repository.save(account);
	}

	@Override
	public Account update(Account account, Long id) {
		Account accountFind = repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		BeanUtils.copyProperties(account, accountFind);

		return repository.save(account);
	}

	@Override
	public void delete(Long id) {
		Account accountFind = repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));

		repository.delete(accountFind);
	}

}
