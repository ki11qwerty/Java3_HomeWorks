/*
*Java Level 3, Lesson 1
*@author Aleksey Antonov
*@link
*
 */
import java.util.ArrayList;

public class HomeWork {

    public static void main(String[] args) {
        Integer[] nums  = { 1 , 2 , 3 , 4 , 5 };
        String[] str  = { "a" , "b" , "c" , "d" , "e" };
        // (1) Написать метод, который меняет два элемента массива местами
        WorkWithArray<Integer> arr1 = new WorkWithArray<>(nums);
        System.out.print("Массив 1 до смены элементов:- ");
        arr1.justPrintArr();
        arr1.changeElement(0,4);
        System.out.print("Массив 1 после смены эдементов:- ");
        arr1.justPrintArr();
        WorkWithArray<String> arr2 = new WorkWithArray<>(str);
        System.out.print("Массив 2 до смены элементов:- ");
        arr2.justPrintArr();
        arr2.changeElement(1,4);
        System.out.print("Массив 2 после смены эдементов:- ");
        arr2.justPrintArr();
        // (2) Написать метод, который преобразует массив в ArrayList
        ArrayList arrList =  arr1.arrToList();

    }
}
class WorkWithArray<T> {
    T[] arr;

    public WorkWithArray(T[] arr) {
        this.arr = arr;
    }

    public void changeElement(int i, int j){
        T a=this.arr[i];
        this.arr[i]=arr[j];
        this.arr[j]=a;
    }

    public ArrayList arrToList (){
        int leng = this.arr.length;
        ArrayList arr = new ArrayList();

        for (int i=0; i<leng; i++)
        {
            arr.add(this.arr[i]);
        }
        return arr;
    }
    public void justPrintArr(){
        int leng = this.arr.length;
        for(int i=0;i<leng;i++){
            if (i != (leng-1))
                System.out.print(this.arr[i]+",");
            else
                System.out.println(this.arr[i]+".");
        }
    }
}
