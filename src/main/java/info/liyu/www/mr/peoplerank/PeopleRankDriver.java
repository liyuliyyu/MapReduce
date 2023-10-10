package info.xiaohei.www.mr.peoplerank;

import java.io.IOException;

/**
 * Created by xiaohei on 16/3/9.
 */
public class PeopleRankDriver {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        info.xiaohei.www.mr.peoplerank.AdjacencyMatrix.run();
        for (int i = 0; i < 10; i++) {
            info.xiaohei.www.mr.peoplerank.CalcPeopleRank.run();
        }
        info.xiaohei.www.mr.peoplerank.FinallyResult.run();
    }
}
