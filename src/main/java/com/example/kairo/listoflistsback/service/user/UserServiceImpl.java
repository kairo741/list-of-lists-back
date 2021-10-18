package com.example.kairo.listoflistsback.service.user;


import com.example.kairo.listoflistsback.dto.UserDTO;
import com.example.kairo.listoflistsback.entity.Role;
import com.example.kairo.listoflistsback.entity.RoleEnum;
import com.example.kairo.listoflistsback.entity.User;
import com.example.kairo.listoflistsback.filter.UserFilter;
import com.example.kairo.listoflistsback.framework.exception.CustomException;
import com.example.kairo.listoflistsback.framework.repository.JpaCrudRepository;
import com.example.kairo.listoflistsback.framework.service.JpaCrudServiceImpl;
import com.example.kairo.listoflistsback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends JpaCrudServiceImpl<User, Long, UserFilter> implements UserService {

    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenUtil jwtUtils;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder encoder;

//    @Override
//    public JwtResponse authenticateUser(LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        User userDetails = (User) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//        return new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                null,
//                roles);
//    }

    @Override
    public void registerUser(UserDTO userDTO) throws CustomException {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new CustomException("Error: Username is already taken!");
        }

        // Create new user's account
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(
//                        encoder.encode(
                        userDTO.getPassword()
//                        )
                )
                .build();

//        Set<String> strRoles = userDTO.getRole();
        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().name(RoleEnum.ROLE_USER).build());

//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User save(User entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            return update(entity);
        } else {
            return create(entity);
        }
    }


    private User create(User entity) {
        log.debug("CoachService.create");
        var model = save(entity);
        entity.setId(model.getId());
        return entity;
    }

    private User update(User newEntity) {
        log.debug("CoachService.update {}", newEntity.getId());
        var op = findById(newEntity.getId());

        if (op.isPresent()) {
            var dbEntity = op.get();
            dbEntity.setName(newEntity.getName());
            save(dbEntity);
        } else {
            throw new CustomException("Nenhum exemplo encontrado");
        }
        return newEntity;
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return null;
    }

    @Override
    protected JpaCrudRepository<User, UserFilter> getData() {
        return null;
    }
}
