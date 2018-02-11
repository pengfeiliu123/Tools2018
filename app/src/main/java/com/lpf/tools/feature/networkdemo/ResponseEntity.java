package com.lpf.tools.feature.networkdemo;

import java.util.List;

/**
 * Created by liupengfei on 2018/2/11 11:41.
 */

public class ResponseEntity {


    /**
     * type : resource_by_tag
     * id : resource_by_tag
     * name : resource_by_tag
     * nextToken : 2
     * resources : [{"v_mv_youtube":{"videoId":"2V56f0xZNqw","srcURL":"https://www.youtube.com/watch?v=2V56f0xZNqw","shareURL":"https://www.youtube.com/watch?v=2V56f0xZNqw"},"singer":{"type":"mv_singer_lite","id":"6ad37227f75dc298044fec6023cb986c","name":"Various Artists","icon":"https://d39g27wwsckj7u.cloudfront.net/pic/music/singer_avatar/Avatar-VariousArtist.png","selfPic":["https://d39g27wwsckj7u.cloudfront.net/pic/music/singer_avatar/Avatar-VariousArtist.png"]},"albumId":"7550486bdff66e8afb1e552149dc270b","langs":[{"type":"tag_lang","id":"99e5578aa53018e1be1b5d65d84c2ff4","name":"Hindi"}],"genres":[{"type":"tag_genre","id":"Bollywood","name":"Bollywood"}],"type":"v_mv_youtube","id":"17e7c7e9ec8ef2022cb134d91b5460b8","name":"Didi Tera Devar Deewana - Hum Aapke Hain Koun - Salman Khan, Madhuri Dixit - Best Bollywood Song","attachment":"","s_highlight":"","desc":"","watchAt":0,"picList":["https://i.ytimg.com/vi/2V56f0xZNqw/hqdefault.jpg"],"duration":458,"publishTime":"2017-12-18T08:28:57+00:00","playCount":53832333},{"v_mv_youtube":{"videoId":"KdtlfPMbKGs","srcURL":"https://www.youtube.com/watch?v=KdtlfPMbKGs","shareURL":"https://www.youtube.com/watch?v=KdtlfPMbKGs"},"singer":{"type":"mv_singer_lite","id":"6ad37227f75dc298044fec6023cb986c","name":"Various Artists","icon":"https://d39g27wwsckj7u.cloudfront.net/pic/music/singer_avatar/Avatar-VariousArtist.png","selfPic":["https://d39g27wwsckj7u.cloudfront.net/pic/music/singer_avatar/Avatar-VariousArtist.png"]},"albumId":"585d66c32d69aa2a9b51d272f965bdb9","langs":[{"type":"tag_lang","id":"99e5578aa53018e1be1b5d65d84c2ff4","name":"Hindi"}],"genres":[{"type":"tag_genre","id":"Bollywood","name":"Bollywood"}],"type":"v_mv_youtube","id":"b1493b6b3f2df8bdcd920e046888d21f","name":"Bahut Pyar Karte Hain (Video Song) - Saajan","attachment":"","s_highlight":"","desc":"","watchAt":0,"picList":["https://i.ytimg.com/vi/KdtlfPMbKGs/hqdefault.jpg"],"duration":241,"publishTime":"2017-12-18T08:28:57+00:00","playCount":36435023}]
     */

    private String type;
    private String id;
    private String name;
    private String nextToken;
    private List<ResourcesBean> resources;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextToken() {
        return nextToken;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    public List<ResourcesBean> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesBean> resources) {
        this.resources = resources;
    }

    public static class ResourcesBean {
        /**
         * v_mv_youtube : {"videoId":"2V56f0xZNqw","srcURL":"https://www.youtube.com/watch?v=2V56f0xZNqw","shareURL":"https://www.youtube.com/watch?v=2V56f0xZNqw"}
         * singer : {"type":"mv_singer_lite","id":"6ad37227f75dc298044fec6023cb986c","name":"Various Artists","icon":"https://d39g27wwsckj7u.cloudfront.net/pic/music/singer_avatar/Avatar-VariousArtist.png","selfPic":["https://d39g27wwsckj7u.cloudfront.net/pic/music/singer_avatar/Avatar-VariousArtist.png"]}
         * albumId : 7550486bdff66e8afb1e552149dc270b
         * langs : [{"type":"tag_lang","id":"99e5578aa53018e1be1b5d65d84c2ff4","name":"Hindi"}]
         * genres : [{"type":"tag_genre","id":"Bollywood","name":"Bollywood"}]
         * type : v_mv_youtube
         * id : 17e7c7e9ec8ef2022cb134d91b5460b8
         * name : Didi Tera Devar Deewana - Hum Aapke Hain Koun - Salman Khan, Madhuri Dixit - Best Bollywood Song
         * attachment :
         * s_highlight :
         * desc :
         * watchAt : 0
         * picList : ["https://i.ytimg.com/vi/2V56f0xZNqw/hqdefault.jpg"]
         * duration : 458
         * publishTime : 2017-12-18T08:28:57+00:00
         * playCount : 53832333
         */

        private VMvYoutubeBean v_mv_youtube;
        private SingerBean singer;
        private String albumId;
        private String type;
        private String id;
        private String name;
        private String attachment;
        private String s_highlight;
        private String desc;
        private int watchAt;
        private int duration;
        private String publishTime;
        private int playCount;
        private List<LangsBean> langs;
        private List<GenresBean> genres;
        private List<String> picList;

        public VMvYoutubeBean getV_mv_youtube() {
            return v_mv_youtube;
        }

        public void setV_mv_youtube(VMvYoutubeBean v_mv_youtube) {
            this.v_mv_youtube = v_mv_youtube;
        }

        public SingerBean getSinger() {
            return singer;
        }

        public void setSinger(SingerBean singer) {
            this.singer = singer;
        }

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }

        public String getS_highlight() {
            return s_highlight;
        }

        public void setS_highlight(String s_highlight) {
            this.s_highlight = s_highlight;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getWatchAt() {
            return watchAt;
        }

        public void setWatchAt(int watchAt) {
            this.watchAt = watchAt;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public List<LangsBean> getLangs() {
            return langs;
        }

        public void setLangs(List<LangsBean> langs) {
            this.langs = langs;
        }

        public List<GenresBean> getGenres() {
            return genres;
        }

        public void setGenres(List<GenresBean> genres) {
            this.genres = genres;
        }

        public List<String> getPicList() {
            return picList;
        }

        public void setPicList(List<String> picList) {
            this.picList = picList;
        }

        public static class VMvYoutubeBean {
            /**
             * videoId : 2V56f0xZNqw
             * srcURL : https://www.youtube.com/watch?v=2V56f0xZNqw
             * shareURL : https://www.youtube.com/watch?v=2V56f0xZNqw
             */

            private String videoId;
            private String srcURL;
            private String shareURL;

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }

            public String getSrcURL() {
                return srcURL;
            }

            public void setSrcURL(String srcURL) {
                this.srcURL = srcURL;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }
        }

        public static class SingerBean {
            /**
             * type : mv_singer_lite
             * id : 6ad37227f75dc298044fec6023cb986c
             * name : Various Artists
             * icon : https://d39g27wwsckj7u.cloudfront.net/pic/music/singer_avatar/Avatar-VariousArtist.png
             * selfPic : ["https://d39g27wwsckj7u.cloudfront.net/pic/music/singer_avatar/Avatar-VariousArtist.png"]
             */

            private String type;
            private String id;
            private String name;
            private String icon;
            private List<String> selfPic;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public List<String> getSelfPic() {
                return selfPic;
            }

            public void setSelfPic(List<String> selfPic) {
                this.selfPic = selfPic;
            }
        }

        public static class LangsBean {
            /**
             * type : tag_lang
             * id : 99e5578aa53018e1be1b5d65d84c2ff4
             * name : Hindi
             */

            private String type;
            private String id;
            private String name;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class GenresBean {
            /**
             * type : tag_genre
             * id : Bollywood
             * name : Bollywood
             */

            private String type;
            private String id;
            private String name;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
