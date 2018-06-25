package isd.internship.ala.services;

import isd.internship.ala.models.CustomUserDetails;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class CustomUserDetailsService {//implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser= userRepository.findByEmail(username);
//
//        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Email not found!"));
//
//        return optionalUser.map(CustomUserDetails::new).get();
//    }
}
