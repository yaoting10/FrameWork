package com.huobangzhu.api.v1.controller;

import com.huobangzhu.api.v1.security.SessionToken;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.huobangzhu.foundation.model.StatusResponse;
import com.huobangzhu.foundation.model.Token;
import com.huobangzhu.foundation.model.TokenFactory;
import com.huobangzhu.foundation.model.VerificationCache;
import com.huobangzhu.util.ModelUtils;
import com.wordnik.swagger.annotations.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sun.plugin.services.PlatformService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.huobangzhu.api.v1.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.huobangzhu.foundation.model.ErrorCode.*;
import static com.huobangzhu.foundation.model.StatusResponse.error;
import static com.huobangzhu.foundation.model.StatusResponse.success;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author: Tim.Yao
 */
@Api(value = "insecure", description = "Insecure APIs", position = 1)
@Slf4j
@RestController
@RequestMapping(consumes = "application/json", produces = "application/json")
@Profile("prod")
public class InsecureController{

    @Autowired
    private TokenFactory<Long> tokenFactory;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private VerificationCache<String, String, String> verifyCache;
    @Autowired
    private PlatformService platformService;

    @GET
    @Path("/signout")
    @ApiOperation(value = "User Sign Out", response = BooleanEntity.class, position = 3)
    @ApiResponses({@ApiResponse(code = TOKEN_REQUIRED, message = "Token Required", response = StatusResponse.class),
            @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class)})
    @RequestMapping(value = "/signout", method = GET)
    public
    @ResponseBody
    StatusResponse signOut(@HeaderParam(TOKEN_HEADER_KEY) @ApiParam(required = true) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String serializedToken){
        SessionToken sessionToken = SessionToken.valueOf(serializedToken);
        Token cacheToken = tokenFactory.get(sessionToken.getKey());
        if(Objects.isNull(cacheToken)){
            return success(new BooleanEntity(false));
        }
        tokenFactory.remove(sessionToken.getKey());
        return success(new BooleanEntity(true));
    }




    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class ResetPasswordRequest{

        @ApiModelProperty(value = "Ticket", required = true, position = 0)
        @JsonProperty("k")
        @NotBlank
        private String ticket;

        @ApiModelProperty(value = "Password", required = true, position = 1)
        @JsonProperty("p")
        @NotBlank
        private String password;

        @ApiModelProperty(value = "Confirmed Password", required = true, position = 2)
        @JsonProperty("c")
        @NotBlank
        private String confirmedPassword;

        @JsonCreator
        public ResetPasswordRequest(@JsonProperty("k") @NotBlank String ticket,
                                    @JsonProperty("p") @NotBlank String password,
                                    @JsonProperty("c") @NotBlank String confirmedPassword){
            this.ticket = ticket;
            this.password = password;
            this.confirmedPassword = confirmedPassword;
        }
    }

    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class VerifyCodeRequest{

        @ApiModelProperty(value = "Cell No", required = true, position = 0)
        @JsonProperty("c")
        @NotBlank
        private String cell;

        @ApiModelProperty(value = "Code Type [0:register, 1:findPwd, 2:modifyCellNo]", required = true, position = 1)
        @JsonProperty("t")
        @NotNull
        private Integer type;

        @ApiModelProperty(value = "Verification Code", position = 2)
        @JsonProperty("v")
        private String code;

        @JsonCreator
        public VerifyCodeRequest(@JsonProperty("c") @NotBlank String cell,
                                 @JsonProperty("t") @NotNull Integer type,
                                 @JsonProperty("v") String code){
            this.cell = cell;
            this.code = code;
            this.type = type;
        }
    }

    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class SigninRequest{

        @ApiModelProperty(value = "Cell No", required = true, position = 0)
        @JsonProperty("c")
        @NotBlank
        private String cellNo;

        @ApiModelProperty(value = "Password", required = true, position = 1)
        @JsonProperty("p")
        @NotBlank
        private String password;

        @JsonCreator
        public SigninRequest(@JsonProperty("c") @NotBlank String cellNo,
                             @JsonProperty("p") @NotBlank String password){
            this.cellNo = cellNo;
            this.password = password;
        }
    }


    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class UserRegistration{

        @ApiModelProperty(value = "Ticket", required = true, position = 0)
        @JsonProperty("k")
        @NotBlank
        private String ticket;

        @ApiModelProperty(value = "Cell No", required = true, position = 1)
        @JsonProperty("c")
        @NotBlank
        private String cell;

        @ApiModelProperty(value = "Password", required = true, position = 2)
        @JsonProperty("p")
        @NotBlank
        private String password;

        @JsonCreator
        public UserRegistration(@JsonProperty("k") @NotBlank String ticket,
                                @JsonProperty("c") @NotBlank String cellNo,
                                @JsonProperty("p") @NotBlank String password){
            this.ticket = ticket;
            this.cell = cellNo;
            this.password = password;
        }
    }

    private Pageable toPageable(Integer page, Integer size){
        if(page == null || size == null)
            return new PageRequest(0, 20);
        if(page < 0 || size < 1)
            return new PageRequest(0, 20);

        return new PageRequest(page, size);
    }
}
