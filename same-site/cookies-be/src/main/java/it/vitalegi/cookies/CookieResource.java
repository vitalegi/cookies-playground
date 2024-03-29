package it.vitalegi.cookies;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

@CrossOrigin(origins = {"https://fe.home.company.eu", "https://home.company.eu", "https://home.company2.eu"}, allowCredentials = "true")
@RestController
@RequestMapping("/cookies")
public class CookieResource {

    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/{id}")
    public Cookies getOrInitCookies(@PathVariable("id") String id, @CookieValue(name = "cookie1", required = false) String cookie, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}. received cookie: {}. Origin={}", id, cookie, request.getHeader("Origin"));
        if (cookie != null && !cookie.isBlank()) {
            return cookies(cookie);
        }
        var newValue = UUID.randomUUID().toString();
        log.info("{} Set cookie {}={}", id, "cookie1", newValue);
        var newCookie = "cookie1=" + newValue + "; Domain=.company.eu; SameSite=None; Secure; HttpOnly; Path=/; Max-Age=1600";
        response.addHeader(HttpHeaders.SET_COOKIE, newCookie);
        return cookies(newValue);
    }

    protected Cookies cookies(String cookie) {
        var cookies = new Cookies();
        cookies.setCookies(Arrays.asList(cookie));
        return cookies;
    }
}
