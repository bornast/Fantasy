package hr.bornast.fantasy.application.service.impl;

import java.util.HashSet;
import java.util.Set;

import hr.bornast.fantasy.application.command.auth.LoginCommand;
import hr.bornast.fantasy.application.dto.auth.TokenDto;
import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.application.service.AuthService;
import hr.bornast.fantasy.common.util.JwtUtil;
import hr.bornast.fantasy.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenDto login(LoginCommand command) {
        var username = command.getUsername();
        var password = command.getPassword();
        authenticate(username, password);

        var userDetails = loadUserByUsername(username);
        var user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        var generatedToken = jwtUtil.generateToken(userDetails, user.getId());

//        var user = userRepository.findById(username).get();

        return new TokenDto(generatedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: custom exception
        var user = userRepository.findByUsername(username)
            .orElseThrow(RuntimeException::new);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user));
        } else {
            throw new UsernameNotFoundException("Username is not valid");
        }
    }

    private Set getAuthorities(User user) {
        Set authorities = new HashSet();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });

        return authorities;
    }

    private void authenticate(String userName, String userPassword) {
        // TODO: use custom exceptions
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new RuntimeException("User is disabled");
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Bad credentials from user");
        }
    }

}
