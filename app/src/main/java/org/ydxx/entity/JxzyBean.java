package org.ydxx.entity;

import java.util.List;

public class JxzyBean {


    /**
     * status : 1
     * data : [{"lsid":"高数 No.2","kcmc":"高数 No.2","ext1":"https://www.imooc.com/video/16896","ext3":"https://www.imooc.com/video/16896","id":2,"lsxm":"明明 No.2","ext2":"高数 No.2"},{"lsid":"高数 No.2","kcmc":"高数 No.2","ext1":"https://www.imooc.com/video/16896","ext3":"https://www.imooc.com/video/16896","id":3,"lsxm":"明明 No.2","ext2":"高数 No.2"},{"lsid":"高数 No.2","kcmc":"高数 No.2","ext1":"https://www.imooc.com/video/16896","ext3":"https://www.imooc.com/video/16896","id":4,"lsxm":"明明 No.2","ext2":"高数 No.2"},{"lsid":"高数 No.2","kcmc":"高数 No.2","ext1":"https://www.imooc.com/video/16896","ext3":"https://www.imooc.com/video/16896","id":5,"lsxm":"明明 No.2","ext2":"高数 No.2"},{"lsid":"高数 No.2","kcmc":"高数 No.2","ext1":"https://www.imooc.com/video/16896","ext3":"https://www.imooc.com/video/16896","id":6,"lsxm":"明明 No.2","ext2":"高数 No.2"},{"lsid":"高数 No.2","kcmc":"高数 No.2","ext1":"https://www.imooc.com/video/16896","ext3":"https://www.imooc.com/video/16896","id":7,"lsxm":"明明 No.2","ext2":"高数 No.2"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lsid : 高数 No.2
         * kcmc : 高数 No.2
         * ext1 : https://www.imooc.com/video/16896
         * ext3 : https://www.imooc.com/video/16896
         * id : 2
         * lsxm : 明明 No.2
         * ext2 : 高数 No.2
         */

        private String lsid;
        private String kcmc;
        private String ext1;
        private String ext3;
        private int id;
        private String lsxm;
        private String ext2;

        public String getLsid() {
            return lsid;
        }

        public void setLsid(String lsid) {
            this.lsid = lsid;
        }

        public String getKcmc() {
            return kcmc;
        }

        public void setKcmc(String kcmc) {
            this.kcmc = kcmc;
        }

        public String getExt1() {
            return ext1;
        }

        public void setExt1(String ext1) {
            this.ext1 = ext1;
        }

        public String getExt3() {
            return ext3;
        }

        public void setExt3(String ext3) {
            this.ext3 = ext3;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLsxm() {
            return lsxm;
        }

        public void setLsxm(String lsxm) {
            this.lsxm = lsxm;
        }

        public String getExt2() {
            return ext2;
        }

        public void setExt2(String ext2) {
            this.ext2 = ext2;
        }
    }
}
