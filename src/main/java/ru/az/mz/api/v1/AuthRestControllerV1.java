package ru.az.mz.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.az.mz.dto.v1.AuthDtoV1;
import ru.az.mz.dto.v1.LoginPasswordDtoV1;
import ru.az.mz.dto.v1.UserDtoV1;
import ru.az.mz.model.User;
import ru.az.mz.security.JwtAuthenticationException;
import ru.az.mz.security.JwtUtil;
import ru.az.mz.services.MyException;
import ru.az.mz.services.UserServiceV1V1;

import java.util.HashSet;

@RestController
@RequestMapping("api/v1/auth")
public class AuthRestControllerV1 {

	private final UserServiceV1V1 userServiceV1;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public AuthRestControllerV1(UserServiceV1V1 userServiceV1, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userServiceV1 = userServiceV1;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("login")
	public ResponseEntity<AuthDtoV1> login(@RequestBody LoginPasswordDtoV1 loginPasswordDtoV1) throws MyException {
		if (loginPasswordDtoV1 == null || loginPasswordDtoV1.getUsername() == null) {
			throw new MyException(HttpStatus.BAD_REQUEST);
		}
		User userFromDb = userServiceV1.findByUsername(loginPasswordDtoV1.getUsername());
		if (loginPasswordDtoV1.getPassword() == null
				|| !passwordEncoder.matches(loginPasswordDtoV1.getPassword(), userFromDb.getPassword()))
			throw new MyException("Error Password", HttpStatus.FORBIDDEN);
		AuthDtoV1 authDtoV1 = new AuthDtoV1(
				jwtUtil.createToken(loginPasswordDtoV1.getUsername(), new HashSet<>(userFromDb.getRoles())),
				UserDtoV1.createWithRole(userFromDb));
		return ResponseEntity.ok(authDtoV1);
	}
	
	@PostMapping("validate")
	public ResponseEntity<UserDtoV1> validateToken(
			@RequestParam(required = true) String token	
	) throws JwtAuthenticationException, MyException{
		String localToken = token.replaceAll("Bearer ", "");
		User userFromDb = null;
		if(jwtUtil.validateToken(localToken)) {
			userFromDb = userServiceV1.findByUsername(jwtUtil.getUsername(localToken));
			return ResponseEntity.ok(UserDtoV1.createWithRole(userFromDb));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@ExceptionHandler(MyException.class)
	public ResponseEntity<?> errAuth(MyException e){
		return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
	}

}
