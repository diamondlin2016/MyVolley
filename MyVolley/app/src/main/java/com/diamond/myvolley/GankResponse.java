package com.diamond.myvolley;

import java.util.Date;
import java.util.List;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/28 下午3:15
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */


public class GankResponse {

    private boolean error;
    private List<Bean> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean getError() {
        return error;
    }

    public void setResults(List<Bean> results) {
        this.results = results;
    }

    public List<Bean> getResults() {
        return results;
    }


    public class Bean {
        private String _id;
        private Date createdAt;
        private String desc;
        private Date publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public void set_id(String _id) {
            this._id = _id;
        }

        public String get_id() {
            return _id;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setPublishedAt(Date publishedAt) {
            this.publishedAt = publishedAt;
        }

        public Date getPublishedAt() {
            return publishedAt;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSource() {
            return source;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public boolean getUsed() {
            return used;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getWho() {
            return who;
        }
    }


}