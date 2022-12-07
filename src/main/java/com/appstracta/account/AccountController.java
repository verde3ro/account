package com.appstracta.account;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AccountController {

	private final IAccountService service;

	@GetMapping
	public ResponseEntity<List<Account>> findAll() {
		List<Account> accounts = service.findAll();

		if (accounts.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(accounts);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Account>> findById(@PathVariable Long id) {
		Optional<Account> account = service.findById(id);

		return ResponseEntity.ok(account);
	}

	@PostMapping
	public ResponseEntity<Account> save(@RequestBody Account account, HttpServletRequest httpServletRequest) {
		Account accountSave = service.save(account);
		URI location = ServletUriComponentsBuilder.fromServletMapping(httpServletRequest).path("/{id}").build().expand(accountSave.getId()).toUri();

		return ResponseEntity.created(location).body(accountSave);
	}

	@PutMapping
	public Account update(@RequestBody Account account, @PathVariable Long id) {
		return service.update(account, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
