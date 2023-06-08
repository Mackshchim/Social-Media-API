package tatar.mackshchim.sm.controllers.configurations;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tatar.mackshchim.sm.security.responses.SecurityExceptionDTO;

import static tatar.mackshchim.sm.security.filters.JWTAuthenticationFilter.AUTHENTICATION_URL;
import static tatar.mackshchim.sm.security.filters.JWTAuthenticationFilter.USERNAME_PARAMETER;
import static tatar.mackshchim.sm.security.utils.impl.JWTUtilAuth0Impl.ACCESS_TOKEN;
import static tatar.mackshchim.sm.security.utils.impl.JWTUtilAuth0Impl.REFRESH_TOKEN;

@Configuration
public class OpenApiConfiguration {

    public static final String BEARER_AUTH = "bearerAuth";
    public static final String TOKENS = "Tokens";
    public static final String EMAIL_AND_PASSWORD = "EmailAndPassword";




    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(buildSecurity())
                .paths(buildAuthenticationPaths())
                .components(buildComponents())
                .info(buildInfo());
    }

    private Paths buildAuthenticationPaths() {
        return new Paths()
                .addPathItem(AUTHENTICATION_URL,buildAuthenticationPathItem());
    }

    private PathItem buildAuthenticationPathItem() {
        return new PathItem()
                .post(new Operation()
                        .addTagsItem("Authentication")
                        .requestBody(buildAuthenticationRequestBody())
                        .responses(buildAuthenticationResponses()));
    }

    private RequestBody buildAuthenticationRequestBody() {
        return new RequestBody()
                .content(new Content()
                        .addMediaType("application/x-www-form-urlencoded",
                                new MediaType()
                                        .schema(new Schema<>()
                                                .$ref(EMAIL_AND_PASSWORD))));
    }

    private ApiResponses buildAuthenticationResponses() {
        return new ApiResponses()
                .addApiResponse("200",
                        new ApiResponse()
                                .content(new Content()
                                                .addMediaType("application/json",
                                                        new MediaType()
                                                                .schema(new Schema<>()
                                                                        .$ref(TOKENS)))))
                .addApiResponse("403",
                        new ApiResponse()
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType().schema(new Schema<SecurityExceptionDTO>())
                                                ))
                                .description("Exception info"));
    }

    private SecurityRequirement buildSecurity() {
        return new SecurityRequirement().addList(BEARER_AUTH);
    }

    private Info buildInfo() {
        return new Info().title("Social Media API").version("0.1");
    }

    private Components buildComponents() {
        Schema<?> emailAndPassword = new Schema<>()
                .type("object")
                .description("Users email and password")
                .addProperty(USERNAME_PARAMETER, new Schema<>().type("string"))
                .addProperty("password", new Schema<>().type("string"));

        Schema<?> tokens = new Schema<>()
                .type("object")
                .description("Access and Refresh tokens")
                .addProperty(ACCESS_TOKEN, new Schema<>().type("string"))
                .addProperty(REFRESH_TOKEN, new Schema<>().type("string"));

        SecurityScheme securityScheme = new SecurityScheme()
                .name(BEARER_AUTH)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new Components()
                .addSchemas(EMAIL_AND_PASSWORD, emailAndPassword)
                .addSchemas(TOKENS, tokens)
                .addSecuritySchemes(BEARER_AUTH, securityScheme);
    }

}
