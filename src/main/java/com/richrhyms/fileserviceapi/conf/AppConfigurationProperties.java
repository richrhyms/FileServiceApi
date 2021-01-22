package com.richrhyms.fileserviceapi.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Otaru Richard
 * email: richotaru@gmail.com
 * Jan, 2021
 **/
@Configuration
@ConfigurationProperties(prefix = "app",ignoreInvalidFields = true)
@Getter
@Setter
public class AppConfigurationProperties {
    private String authBaseUrl;
    private String uploadDir;
    /**
     * Path to the directory where to find email templates
     */
    private String emailTemplatesDirectory;
    /**
     * This is the base url that will be used when contacting file server on the local network
     */
    private String fileServerLocalBaseUrl;
}
