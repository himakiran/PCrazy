package biz.chundi.pcrazy;

/**
 * Created by userhk on 11/02/17.
 */

/*
        We are using the below warpper class so that doInBackground can send multiple arrays to onPostExecute
        http://stackoverflow.com/questions/11833978/asynctask-pass-two-or-more-values-from-doinbackground-to-onpostexecute
         */
public class Wrapper {
    private int[] w_imageIDArray;
    private String[] w_imageUrlArray;
    private String[] w_thumbUrlArray;
    public int[]getWrapperImgId(){
        return w_imageIDArray;
    }
    public String[] getWrapperImgUrl(){
        return w_imageUrlArray;
    }
    public String[] getWrapperThumbUrl() { return w_thumbUrlArray;}
    public void setWrapperImgId(int[] i){
        this.w_imageIDArray = i;
    }
    public void setWrapperImgUrl(String[] s){
        this.w_imageUrlArray = s;
    }
    public void setWrapperThumbUrl(String[] s){
        this.w_thumbUrlArray = s;
    }
}