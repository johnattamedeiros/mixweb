package com.mixapp.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.mixapp.app.MixwebApp;
import com.mixapp.app.config.Constants;
import com.mixapp.app.domain.PersistentToken;
import com.mixapp.app.domain.User;
import com.mixapp.app.repository.PersistentTokenRepository;
import com.mixapp.app.repository.UserRepository;
import com.mixapp.app.service.dto.UserDTO;

import io.github.jhipster.security.RandomUtil;

/**
 * Integration tests for {@link UserService}.
 */
@SpringBootTest(classes = MixwebApp.class)
@Transactional
public class UserServiceIT {

    private static final String DEFAULT_LOGIN = "johndoe";

    private static final String DEFAULT_EMAIL = "johndoe@localhost";

    private static final String DEFAULT_NAME = "john";

    private static final String DEFAULT_IMAGEURL = "http://placehold.it/50x50";

    private static final String DEFAULT_LANGKEY = "dummy";

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuditingHandler auditingHandler;

    @Mock
    private DateTimeProvider dateTimeProvider;

    private User user;

    @BeforeEach
    public void init() {
        persistentTokenRepository.deleteAll();
        user = new User();
        user.setLogin(DEFAULT_LOGIN);
        user.setPassword(RandomStringUtils.random(60));
        user.setActivated(true);
        user.setEmail(DEFAULT_EMAIL);
        user.setName(DEFAULT_NAME);
        user.setImageUrl(DEFAULT_IMAGEURL);
        user.setLangKey(DEFAULT_LANGKEY);

        when(dateTimeProvider.getNow()).thenReturn(Optional.of(LocalDateTime.now()));
        auditingHandler.setDateTimeProvider(dateTimeProvider);
    }

    @Test
    @Transactional
    public void assertThatUserMustExistToResetPassword() {
        userRepository.saveAndFlush(user);
        Optional<User> maybeUser = userService.requestPasswordReset("invalid.login@localhost");
        assertThat(maybeUser).isNotPresent();

        maybeUser = userService.requestPasswordReset(user.getEmail());
        assertThat(maybeUser).isPresent();
        assertThat(maybeUser.orElse(null).getEmail()).isEqualTo(user.getEmail());
        assertThat(maybeUser.orElse(null).getResetDate()).isNotNull();
        assertThat(maybeUser.orElse(null).getResetKey()).isNotNull();
    }

    @Test
    @Transactional
    public void assertThatOnlyActivatedUserCanRequestPasswordReset() {
        user.setActivated(false);
        userRepository.saveAndFlush(user);

        Optional<User> maybeUser = userService.requestPasswordReset(user.getLogin());
        assertThat(maybeUser).isNotPresent();
        userRepository.delete(user);
    }

    @Test
    @Transactional
    public void assertThatResetKeyMustNotBeOlderThan24Hours() {
        Instant daysAgo = Instant.now().minus(25, ChronoUnit.HOURS);
        String resetKey = RandomUtil.generateResetKey();
        user.setActivated(true);
        user.setResetDate(daysAgo);
        user.setResetKey(resetKey);
        userRepository.saveAndFlush(user);

        Optional<User> maybeUser = userService.completePasswordReset("johndoe2", user.getResetKey());
        assertThat(maybeUser).isNotPresent();
        userRepository.delete(user);
    }

    @Test
    @Transactional
    public void assertThatResetKeyMustBeValid() {
        Instant daysAgo = Instant.now().minus(25, ChronoUnit.HOURS);
        user.setActivated(true);
        user.setResetDate(daysAgo);
        user.setResetKey("1234");
        userRepository.saveAndFlush(user);

        Optional<User> maybeUser = userService.completePasswordReset("johndoe2", user.getResetKey());
        assertThat(maybeUser).isNotPresent();
        userRepository.delete(user);
    }

    @Test
    @Transactional
    public void assertThatUserCanResetPassword() {
        String oldPassword = user.getPassword();
        Instant daysAgo = Instant.now().minus(2, ChronoUnit.HOURS);
        String resetKey = RandomUtil.generateResetKey();
        user.setActivated(true);
        user.setResetDate(daysAgo);
        user.setResetKey(resetKey);
        userRepository.saveAndFlush(user);

        Optional<User> maybeUser = userService.completePasswordReset("johndoe2", user.getResetKey());
        assertThat(maybeUser).isPresent();
        assertThat(maybeUser.orElse(null).getResetDate()).isNull();
        assertThat(maybeUser.orElse(null).getResetKey()).isNull();
        assertThat(maybeUser.orElse(null).getPassword()).isNotEqualTo(oldPassword);

        userRepository.delete(user);
    }

    private void generateUserToken(User user, String tokenSeries, LocalDate localDate) {
        PersistentToken token = new PersistentToken();
        token.setSeries(tokenSeries);
        token.setUser(user);
        token.setTokenValue(tokenSeries + "-data");
        token.setTokenDate(localDate);
        token.setIpAddress("127.0.0.1");
        token.setUserAgent("Test agent");
        persistentTokenRepository.saveAndFlush(token);
    }

    @Test
    @Transactional
    public void assertThatAnonymousUserIsNotGet() {
        user.setLogin(Constants.ANONYMOUS_USER);
        if (!userRepository.findOneByLogin(Constants.ANONYMOUS_USER).isPresent()) {
            userRepository.saveAndFlush(user);
        }
        final PageRequest pageable = PageRequest.of(0, (int) userRepository.count());
        final Page<UserDTO> allManagedUsers = userService.getAllManagedUsers(pageable);
        assertThat(allManagedUsers.getContent().stream()
            .noneMatch(user -> Constants.ANONYMOUS_USER.equals(user.getLogin())))
            .isTrue();
    }

}
