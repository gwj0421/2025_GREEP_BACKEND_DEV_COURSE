package base.utils;

import base.exception.IncorrectInputException;
import step.twoAndThree.domain.Url;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UrlUtils {
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
            return new Url(detailPath[1], detailPath[2], Collections.emptyMap());
        }

        Map<String, String> parameters = new HashMap<>();
        for (String originParameter : parts[1].split("&")) {
            String[] parameter = originParameter.split("=", 2);
            if (parameter.length == 1) {
                throw new IncorrectInputException();
            }
            parameters.put(parameter[0], parameter[1]);
        }

        return new Url(detailPath[1], detailPath[2], parameters);
    }

}
