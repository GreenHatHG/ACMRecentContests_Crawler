//package team.huoguo.crawler.service;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.geccocrawler.gecco.annotation.PipelineName;
//import com.geccocrawler.gecco.pipeline.JsonPipeline;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import team.huoguo.crawler.entity.Hdu;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Pistachio
// * @date 2019/8/11 下午5:40
// */
//
//@PipelineName("HduService")
//public class HduService extends JsonPipeline {
//    private List<Hdu> list = null;
//
//    private void getEndTime(String url){
//        Document doc = Jsoup.parse(, url)
//    }
//    private void addItem(){
//        for(Hdu item : list){
//            if("Hdu".equals(item.getOj())){
//        }
//    }
//    @Override
//    public void process(JSONObject jsonObject) {
//        list = new ArrayList<Hdu>();
//        try{
//            JSONArray jsonArray = jsonObject.getJSONArray("list");
//            for(Object obj : jsonArray){
//                JSONObject objo = (JSONObject) obj;
//                list.add(objo.toJavaObject(Hdu.class));
//            }
//            addItem();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}
