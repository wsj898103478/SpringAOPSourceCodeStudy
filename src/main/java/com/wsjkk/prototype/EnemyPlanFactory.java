package com.wsjkk.prototype;

/**
 * 此处我们省去抓异常，随后的事情就非常简单了，我们只需要很简单地调用EnemyPlaneFactory.getInstance(int x)
 * 并声明x坐标位置，一架敌机很快地就做好了，
 * 并且我们保证是在敌机出现的时候再去克隆，确保不要一开局就全部克隆出来，如此一来，
 * 既保证了实时性节省了内存空间，又保证了敌机实例化的速度，游戏绝不会卡帧！
 * 至于此处代码中的懒汉原型还可以怎样优化那就要根据具体场景了，交给大家自由发挥吧，这里只说明主要问题。
 */
public class EnemyPlanFactory {

    private static EnemyPlanePrototype enemyPlanePrototype = new EnemyPlanePrototype(200);
    //获取敌机克隆实例
    public static EnemyPlanePrototype getInstance(int x) throws CloneNotSupportedException {
        EnemyPlanePrototype clone = enemyPlanePrototype.clone();
        clone.setX(x);
        return clone;
    }
}
