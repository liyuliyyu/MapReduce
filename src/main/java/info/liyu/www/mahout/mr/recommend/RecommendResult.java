package info.xiaohei.www.mahout.mr.recommend;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by xiaohei on 16/2/28.
 */
public class RecommendResult {

    public static void main(String[] args) throws IOException, TasteException {
        String filePath = "/Users/xiaohei/Downloads/datafile/job/pv.csv";
        DataModel dataModel = info.xiaohei.www.mahout.mr.recommend.RecommendFactory.getDataModel(filePath);
        RecommenderBuilder rb1 = info.xiaohei.www.mahout.mr.recommend.RecommendEvaluator.userCityBlock(dataModel);
        RecommenderBuilder rb2 = info.xiaohei.www.mahout.mr.recommend.RecommendEvaluator.itemLoglikelihood(dataModel);
        LongPrimitiveIterator iterator = dataModel.getUserIDs();
        while (iterator.hasNext()) {
            long uid = iterator.nextLong();
            System.out.print("userCityBlock=>");
            result(uid, rb1, dataModel);
            dataFilterResult(uid, rb1, dataModel, filePath);
            System.out.print("itemLoglikelihood=>");
            result(uid, rb2, dataModel);
            dataFilterResult(uid, rb2, dataModel, filePath);
        }
    }

    /**
     * 显示推荐结果
     * */
    public static void result(long uid, RecommenderBuilder rb, DataModel dataModel) throws TasteException {
        List<RecommendedItem> recommendedItemList = rb.buildRecommender(dataModel).recommend(uid, info.xiaohei.www.mahout.mr.recommend.RecommendUtil.RECOMMENDER_NUM);
        info.xiaohei.www.mahout.mr.recommend.RecommendUtil.showRecommendResult(uid, recommendedItemList, false);
    }

    /**
     * 显示过滤时间之后的推荐结果
     * */
    public static void dataFilterResult(long uid, RecommenderBuilder rb, DataModel dataModel, String filePath) throws IOException, TasteException {
        Set<Long> jobIds = info.xiaohei.www.mahout.mr.recommend.RecommendUtil.filteOutDateRecores(filePath);
        IDRescorer rescorer = new info.xiaohei.www.mahout.mr.recommend.JobRecorer(jobIds);
        List<RecommendedItem> recommendedItemList = rb.buildRecommender(dataModel).recommend(uid, info.xiaohei.www.mahout.mr.recommend.RecommendUtil.RECOMMENDER_NUM, rescorer);
        info.xiaohei.www.mahout.mr.recommend.RecommendUtil.showRecommendResult(uid, recommendedItemList, false);
    }
}
