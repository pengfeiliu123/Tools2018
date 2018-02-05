package com.lpf.tools.entity;

import java.io.Serializable;

/**
 * Created by liupengfei on 2018/2/5 16:28.
 */

public class FlowTag implements Serializable{

    public String tagName;
    public Class tagClass;

    public FlowTag(String tagName, Class tagClass) {
        this.tagName = tagName;
        this.tagClass = tagClass;
    }
}
