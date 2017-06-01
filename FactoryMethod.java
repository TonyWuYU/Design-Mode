package designmode;

/**
 * Created by yuwu on 2017/5/31.
 * 类型: 创建型
 * 名称: 工厂模式
 * 分类:
 * 1.简单工厂模式(Simple Factory)
 * 2.工厂方法(Factory Method)
 * 3.抽象工厂(Abstract Factory)
 * 说明:
 * 工厂模式主要是为创建对象提供过渡接口，以便将创建对象的具体过程屏蔽隔离起来，达到提高灵活性的目的。
 * 特征:
 */
public class FactoryMethod {
    //测试抽象工厂类
    public static void main(String[] args) {
        ChangHongFactory changHongFactory = new ChangHongFactory();
        ChangHongTV changHongTV = changHongFactory.create();
        changHongTV.showName();
        TclFactory tclFactory = new TclFactory();
        TclTV tclTV = tclFactory.create();
        tclTV.showName();
    }
}

/**
 * 简单工厂模式:
 */
class TV {
    public void showName() {

    }
}

class ChangHongTV extends TV {
    public void showName() {
        System.out.println("ChangHong");
    }
}

class TclTV extends TV {
    public void showName() {
        System.out.println("TCL");
    }
}

class TvFactorySimple {
    public TV tvFactory(String TvName) {
        switch (TvName) {
            case "ChangHong":
                return new ChangHongTV();
            case "TCL":
                return new TclTV();
            default:
                return null;
        }
    }

    //测试简单工厂类
    public static void main(String[] args) {
        TvFactorySimple tvFactorySimple = new TvFactorySimple();
        TV changHong = tvFactorySimple.tvFactory("ChangHong");
        changHong.showName();
        TV tclTV = tvFactorySimple.tvFactory("TCL");
        tclTV.showName();
    }

}

/**
 * 静态工厂方法
 * 使用了static关键字,使生产方法成为类方法。
 */
class TvFactoryStatic {
    public static TclTV tclTVFactory() {
        return new TclTV();
    }

    public static ChangHongTV changHongTVFactory() {
        return new ChangHongTV();
    }

    //测试静态工厂类
    public static void main(String[] args) {
        TclTV tclTV = TvFactoryStatic.tclTVFactory();
        tclTV.showName();
        ChangHongTV changHongTV = TvFactoryStatic.changHongTVFactory();
        changHongTV.showName();
    }
}

/**
 * 抽象工厂方法
 * 抽象出生产方法接口,每一个工厂类实现该接口,做到了对扩展开放,对修改关闭。
 */
interface TvFactoryAbtract {
    TV create();
}

/**
 * 长虹电视工厂类,实现了抽象工厂接口
 */
class ChangHongFactory implements TvFactoryAbtract {
    public ChangHongTV create() {
        return new ChangHongTV();
    }
}

/**
 * TCL电视工厂类,实现了抽象工厂接口
 */
class TclFactory implements TvFactoryAbtract {
    public TclTV create() {
        return new TclTV();
    }
}

