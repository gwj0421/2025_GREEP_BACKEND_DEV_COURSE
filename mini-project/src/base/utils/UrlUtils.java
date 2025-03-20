package base.utils;

import base.exception.IncorrectInputException;
import step.two_three.domain.Category;
import step.two_three.domain.Function;
import step.two_three.domain.Url;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UrlUtils {
    public static final String BOARD_ID_KEY = "boardId";
    public static final String POST_ID_KEY = "postId";
    public static final String ACCOUNT_ID_KEY = "accountId";

    private UrlUtils() {
    }


    // 예) /구분/기능?파라미터...
    public static Url makeUrl(String originUrl) {
        if (!originUrl.startsWith("/")) {
            throw new IncorrectInputException();
        }

        String[] parts = originUrl.split("\\?", 2);
        String path = parts[0];
        String[] detailPath = path.split("/");
        if (detailPath.length != 3) {
            throw new IncorrectInputException();
        }
        if (parts.length == 1) {
            return new Url(Category.getCategory(detailPath[1]), Function.getFunction(detailPath[2]), Collections.emptyMap());
        }

        Map<String, String> parameters = new HashMap<>();
        for (String originParameter : parts[1].split("&")) {
            String[] parameter = originParameter.split("=", 2);
            if (parameter.length == 1) {
                throw new IncorrectInputException();
            }
            parameters.put(parameter[0], parameter[1]);
        }

        return new Url(Category.getCategory(detailPath[1]), Function.getFunction(detailPath[2]), parameters);
    }

}
