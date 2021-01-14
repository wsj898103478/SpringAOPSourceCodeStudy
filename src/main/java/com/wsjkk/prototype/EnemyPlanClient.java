package com.wsjkk.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyPlanClient {
    public static void main(String[] args) {
        List<EnemyPlan> enemyPlanList = new ArrayList<>();
        for(int i = 0;i<50;i++){
            //此处随机位置产生敌机
            EnemyPlan ep = new EnemyPlan(new Random().nextInt(200));
            enemyPlanList.add(ep);
        }
    }
}
