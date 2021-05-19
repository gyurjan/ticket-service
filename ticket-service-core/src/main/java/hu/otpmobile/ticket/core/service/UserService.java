package hu.otpmobile.ticket.core.service;

import hu.otpmobil.common.dto.UserValidationResponseDto;
import hu.otpmobil.common.error.Errors;
import hu.otpmobile.ticket.core.model.UserToken;
import hu.otpmobile.ticket.core.model.Users;
import hu.otpmobile.ticket.core.repository.UserTokenRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserTokenRepository userTokenRepository;

    public UserValidationResponseDto validateUser(String userTokenStr) {
        logger.info("User validation request received: {}", userTokenStr);
        if (StringUtils.isEmpty(userTokenStr)) {
            return new UserValidationResponseDto(null, false, Errors.TOKEN_NOT_FOUND.getEvent(),
                    Errors.TOKEN_NOT_FOUND.getErrorCode(),
                    Errors.TOKEN_NOT_FOUND.getErrorMessage());
        }
        Optional<UserToken> tokenOpt = userTokenRepository.findByToken(userTokenStr);

        Users user = null;
        if (!tokenOpt.isPresent()) {
            return new UserValidationResponseDto(null, false, Errors.TOKEN_EXPIRED.getEvent(),
                    Errors.TOKEN_EXPIRED.getErrorCode(),
                    Errors.TOKEN_EXPIRED.getErrorMessage());
        } else {
            UserToken userToken = tokenOpt.get();
            user = userToken.getUser();
        }

        return new UserValidationResponseDto(user.getUserId(), true, null, null, null);
    }
}
