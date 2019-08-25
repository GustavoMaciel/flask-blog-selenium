package tk.gustavo.utils;
import static tk.gustavo.config.Configuration.getBaseUrl;

public class URL {
    public static final String LOGIN_URL = getBaseUrl() + "login";
    public static final String REGISTER_URL = getBaseUrl() + "register";
    public static final String BASE_URL = getBaseUrl();
}
