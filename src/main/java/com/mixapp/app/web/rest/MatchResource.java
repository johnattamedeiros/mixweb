package com.mixapp.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mixapp.app.domain.Match;
import com.mixapp.app.repository.UserRepository;
import com.mixapp.app.security.AuthoritiesConstants;
import com.mixapp.app.service.MailService;
import com.mixapp.app.service.MatchService;
import com.mixapp.app.service.UserService;

@RestController
@RequestMapping("/api")
public class MatchResource {

    private final Logger log = LoggerFactory.getLogger(MatchResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    
    private final MatchService matchService;


    public MatchResource(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/match")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Match> createMatch(@Valid @RequestBody Match match) throws URISyntaxException {

            Match newMatch = matchService.createMatch(match);
            return ResponseEntity.created(new URI("/api/matchs/" + match.getId()))
                .body(newMatch);
    }
//
//    /**
//     * {@code PUT /users} : Updates an existing User.
//     *
//     * @param userDTO the user to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user.
//     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already in use.
//     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already in use.
//     */
//    @PutMapping("/users")
//    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
//    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
//        log.debug("REST request to update User : {}", userDTO);
//        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
//        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
//            throw new EmailAlreadyUsedException();
//        }
//        existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
//        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
//            throw new LoginAlreadyUsedException();
//        }
//        Optional<UserDTO> updatedUser = userService.updateUser(userDTO);
//
//        return ResponseUtil.wrapOrNotFound(updatedUser,
//            HeaderUtil.createAlert(applicationName, "A user is updated with identifier " + userDTO.getLogin(), userDTO.getLogin()));
//    }
//
//    /**
//     * {@code GET /users} : get all users.
//     *
//     * @param pageable the pagination information.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
//     */
//    @GetMapping("/users")
//    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {
//        final Page<UserDTO> page = userService.getAllManagedUsers(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }
//
//    /**
//     * Gets a list of all roles.
//     * @return a string list of all roles.
//     */
//    @GetMapping("/users/authorities")
//    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
//    public List<String> getAuthorities() {
//        return userService.getAuthorities();
//    }
//
//    /**
//     * {@code GET /users/:login} : get the "login" user.
//     *
//     * @param login the login of the user to find.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "login" user, or with status {@code 404 (Not Found)}.
//     */
//    @GetMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
//        log.debug("REST request to get User : {}", login);
//        return ResponseUtil.wrapOrNotFound(
//            userService.getUserWithAuthoritiesByLogin(login)
//                .map(UserDTO::new));
//    }
//
//    /**
//     * {@code DELETE /users/:login} : delete the "login" User.
//     *
//     * @param login the login of the user to delete.
//     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
//     */
//    @DeleteMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
//    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
//    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
//        log.debug("REST request to delete User: {}", login);
//        userService.deleteUser(login);
//        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName,  "A user is deleted with identifier " + login, login)).build();
//    }
}
