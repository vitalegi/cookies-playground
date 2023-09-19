package it.vitalegi.cookies;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cookies")
public class CookieResource {

    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/test1")
    public String getOrInitCookies(@CookieValue(name = "cookie1", required = false) String cookie, HttpServletResponse response) {
        log.info("Received cookie: {}", cookie);
        if (cookie != null && !cookie.isBlank()) {
            return cookie;
        }
        var newValue = UUID.randomUUID().toString();
        log.info("Set cookie {}={}", "cookie1", newValue);
        var newCookie = "cookie1=" + newValue + "; Domain=domain1.internal; SameSite=None; Secure; Path=/; Maax-Age: " + (60 * 5);
        response.addHeader(HttpHeaders.SET_COOKIE, newCookie);
        return newValue;
    }
}
