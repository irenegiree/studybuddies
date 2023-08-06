package com.example.studybuddies.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.example.studybuddies.dto.UserRegistrationDto;
import com.example.studybuddies.model.User;
import com.example.studybuddies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getStudentId(),
                registrationDto.getTutorId(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getRole());

        return userRepository.save(user);
    }

    @Override
    public void createAdminUser() {
        User user = new User(0, 0, "admin@gmail.com", passwordEncoder.encode("admin123"), "ROLE_ADMIN");
        System.out.println("**********************************************************Admin is not in the database: "+userRepository.findByEmail(user.getEmail()));
        if(userRepository.findByEmail(user.getEmail())==null) {
            System.out.println("**********************************************************Admin is not in the database: ");
            userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role){
        Collection<SimpleGrantedAuthority> col = new Collection<SimpleGrantedAuthority>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<SimpleGrantedAuthority> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(SimpleGrantedAuthority simpleGrantedAuthority) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends SimpleGrantedAuthority> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        col.add(new SimpleGrantedAuthority(role));
        return col;
    }

}