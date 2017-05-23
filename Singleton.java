package designmode;

/**
 * Created by yuwu on 2017/5/21.
 * 类型: 创建型
 * 名称: 单例设计模式
 * 说明:Singleton是一种创建型模式,当某个类采用创建型模式后,只可能产生一个实例供外部访问。
 * 特征: 1. 将类的构造方法实例方法私有化,即改为private类型。
 * 2. 在类的内部产生创建类的实例化对象,并封装成private staticl类型。
 * 3. 定义一个静态方法返回类的实例。
 * 分类: 由于不同环境下的需求不同,一般而言,单例模式有以下三种
 * 1. 饿汉式, 特点:线程安全,但是效率低下。
 * 2.
 */

/**
 * 方法一
 * 饿汉式
 * 优点: 写起来简单,不存在线程同步问题,避免了synchronized所造成的性能问题。
 * 缺点: 在加载阶段已经生成,对于资源会有损耗。
 */
public class Singleton {
    //定义一个私有的构造方法。
    private Singleton() {
    }

    //实例化对象,并且加上static和final修饰符。
    private static final Singleton singleton = new Singleton();

    //static方法,通过方法可以取到单例对象。
    public static Singleton getInstance() {
        return singleton;
    }
}

/**
 * 方法二
 * 饱汉式
 * 优点:编写方便,使用时才加载,不会在类初始化时生成,当getInstance方法第一次被调用时,才会初始化单例对象,分配内存,一定程度下节省了空间。
 * 缺点:并发条件下很可能出现多个SingletonFull实例,不能解决并发问题。
 */
class SingletonFull {
    //定义私有的构造方法
    private SingletonFull() {
    }

    //声明单例对象引用
    private static SingletonFull instance;

    //如果未被实例化,则静态方法返回实例
    public static SingletonFull getInstance() {
        if (instance == null) {
            instance = new SingletonFull();
        }
        return instance;
    }
}

/**
 * 方法三
 * 饱汉式(中等安全版)
 * 是饱汉式的简单优化
 * 优点: 加入了synchronized关键字,避免多线程访问时同时出现多个实例。
 * 缺点: 同步方法频繁调用时，效率略低。
 */
class SingletonFullSyn {
    //定义私有的构造方法
    private SingletonFullSyn() {
    }

    //声明单例对象引用
    private static SingletonFullSyn instance;

    //如果未被实例化,则静态方法返回实例
    public static synchronized SingletonFullSyn getInstance() {
        if (instance == null) {
            instance = new SingletonFullSyn();
        }
        return instance;
    }
}

/**
 * 方法四
 * 饱汉式安全最高级(double-checked)
 * 单例模式的最优实现,省内存,线程安全。
 * 使用了volatile关键字,禁止指令重排(编译器优化),同时具有方法三的优点,多线程访问安全,在需要时再加载等。
 */
class SingletonSecure {
    //定义私有构造方法
    private SingletonSecure() {
    }

    //定义静态私有变量,用volatile禁止重排
    private static volatile SingletonSecure instance;

    //获取单例实例,这里使用了double-check方法,注意:在里面还要嵌套一层if(instance == null)判断
    public static SingletonSecure getInstance() {
        if (instance == null) {
            synchronized (SingletonSecure.class) {
                if (instance == null) {
                    instance = new SingletonSecure();
                }
            }
        }
        return instance;
    }
}