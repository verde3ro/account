package com.appstracta.account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {

	List<Account> findAll();
	Optional<Account> findById(Long id);
	Account save(Account account);

	Account update(Account account, Long id);

	void delete(Long id);

}
