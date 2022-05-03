package com.tvd12.example.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopupBuilder {

    private String url;
    private String token;
    private Activity activity;
    private int layoutId = 12345;

    public PopupBuilder url(String url) {
        this.url = url;
        return this;
    }

    public PopupBuilder token(String token) {
        this.token = token;
        return this;
    }

    public PopupBuilder activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public PopupBuilder layoutId(int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public Popup build() throws Exception {
        if (url == null) {
            throw new NullPointerException("url is null");
        }
        if (token == null) {
            throw new NullPointerException("token is null");
        }
        if (activity == null) {
            throw new IllegalStateException("must specific activity");
        }
        if (layoutId == 12345) {
            System.out.println("you are using default layout");
        }
        OptionsService service = new OptionsService(url, token);
        List<String> options = service.getOptions();
        Popup popup = new Popup(activity, layoutId, options);
        return popup;
    }

    public static class OptionsService {

        private final String url;
        private final String token;

        public OptionsService(String url, String token) {
            this.url = url;
            this.token = token;
        }

        public List<String> getOptions() {
            if (url != null && token != null) {
                return Arrays.asList("1", "2", "3");
            }
            return new ArrayList<>();
        }

    }

    public static class Popup {

        private final Activity activity;
        private final List<String> options;
        private final int layoutId;

        public Popup(Activity activity, int layoutId, List<String> options) {
            this.activity = activity;
            this.options = options;
            this.layoutId = layoutId;
        }

        public void open() {
            System.out.println("open on activity: " + activity + ", with layout = " + layoutId + " options = " + options);
        }

        public void close() {
            System.out.println("close popup");
        }

        public void destroy() {
            this.options.clear();
            System.out.println("destroy popup");
        }

    }

}
