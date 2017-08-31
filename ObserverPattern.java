package designmode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuwu on 2017/8/31.
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Student tony = new Student();//声明学生对象tony
        Teacher teacher = new Teacher(tony);//创建观察者对象【老师】,观察对象【tony】
        Parent parent = new Parent(tony);//创建观察者对象【父母】,观察对象【tony】
        tony.setScore(80, 80);//tony的成绩改动
        tony.setScore(123, 123);//tony的成绩改动
    }
}

/**
 * 【发布】接口 或者称为 【被观察者接口】
 */
interface Subject {
    /**
     * 注册观察者
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObserver();
}

/**
 * 【订阅】接口,或者称为【观察者】接口
 */
interface Observer {
    /**
     * 观察者操作
     */
    void update();
}

/**
 * 学生类,作为被观察者
 */
class Student implements Subject {

    private int mathScore;
    private int englishScore;

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(int englishScore) {
        this.englishScore = englishScore;
    }

    private List<Observer> observerList;

    public Student() {
        observerList = new ArrayList<>();
        mathScore = 100;
        englishScore = 100;
    }

    public void setScore(int mathScore, int englishScore) {
        this.mathScore = mathScore;
        this.englishScore = englishScore;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        observerList.forEach((observer1) -> observer1.update());
    }


}

/**
 * 【观察者】父母类
 */
class Parent implements Observer {

    private Student student;

    public Parent(Student student) {
        this.student = student;
        student.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Tell Parent: Your Child Get :" + "English: " +
                student.getEnglishScore() + " 、" + "Math: " + student.getMathScore());
    }
}

/**
 * 【观察者】老师类
 */
class Teacher implements Observer {

    private Student student;

    public Teacher(Student student) {
        this.student = student;
        student.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Tell Teacher: Your Child Get :" + "English: " +
                student.getEnglishScore() + " 、" + "Math: " + student.getMathScore());
    }
}