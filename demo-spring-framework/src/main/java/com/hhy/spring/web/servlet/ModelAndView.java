package com.hhy.spring.web.servlet;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class ModelAndView {
    private String view;
    private Map<String, String> parameters = new HashMap<>();

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameter(String key, String val) {
        parameters.put(key, val);
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
