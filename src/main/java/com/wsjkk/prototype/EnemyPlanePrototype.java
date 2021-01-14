package com.wsjkk.prototype;

public class EnemyPlanePrototype implements Cloneable{//从此实现克隆接口
    private Bullet bullet = new Bullet();

    private int x;//敌机横坐标
    private int y = 0; //敌机纵坐标

    public EnemyPlanePrototype(int x) { //构造器
        this.x = x;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }
    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public void fly() { //让敌机飞
        y++; //每调用一次，敌机飞行时纵坐标+1
    }

    //此处开放setX，为了让克隆后的实例重新修改x坐标
    public void setX(int x) {
        this.x = x;
    }

    //为了保证飞机飞行的连贯性
    //这里我们关闭setY方法，不支持随意更改Y纵坐标
    //    public void setY(int y) {
    //        this.y = y;
    //    }


    //重写克隆方法
    //还要强调一点就是浅拷贝和深拷贝的问题。假如我们的敌机类里有一颗子弹bullet可以射击我们的主角
    //我们都知道Java中的变量分为原始类型和引用类型，
    // 所谓浅拷贝只是拷贝原始类型的指，比如坐标x, y的指会被拷贝到克隆对象中，
    // 对于对象bullet也会被拷贝，但是请注意拷贝的只是地址而已，那么多个地址其实真正指向的对象还是同一个bullet。
    //由于我们调用父类Object的clone方法进行的是浅拷贝，所以此处的bullet并没有被克隆成功，
    // 比如我们每架敌机必须携带的子弹是不同的实例，那么我们就必须进行深拷贝，于是我们的代码就得做这样的改动。
    @Override
    protected EnemyPlanePrototype clone() throws CloneNotSupportedException {
        EnemyPlanePrototype enemyPlanePrototype = (EnemyPlanePrototype)super.clone();//先克隆出飞机
        enemyPlanePrototype.setBullet(this.bullet.clone());//再克隆出子弹
        return enemyPlanePrototype;
    }
}
