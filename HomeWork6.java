package homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public class HomeWork6 {
    /*
    Напиши реализацию интерфейса Playable:
        - Метод List<String> play()
        - Метод List<String> playWith(Playable playable)
    Напиши реализацию абстрактного класса Instrument:
        - Класс должен релизовать интерфейс Playable
        - Поля класса - sound:String, times:Integer (колличество посторейний звука) (+ конструктор/геттеры)
        - Реализация List<String> play() - метод возвращает список из одной
            строки сформированной по правилу (sound + " ") * times
        - Реализация List<String> playWith(Playable playable) - метод должен вернуть массив
            результатов игры интсруметов в порядке вызова - сначала класс у которого вызвали метод,
            затем класс уоторый передали в качестве аргумента
    Напиши реализацию классов Guitar и Drum:
        - Классы наследуют класс Instrument
        - Конструкторы классов не должны принимать никакие агрументы
        - sound для Guitar "Трунь" и times 2
        - sound для Drum "Бац" и times 3
    Напиши реализацию класса Orchestra
        - Поля - instruments:List<Instrument> (+геттер)
        - Конструктор класса должен принимать любое число агрументов типа Instrument
            Подстказка: загугли что такое фунпкции с переменным числом агрументов и сделай так же
        - Класс должен реализовать интерфейс Playable
        - Реализация List<String> play() - вызываются методы play всех инструментов оркестра,
            результаты вызовов собираются в спиок и возвращаются
        - Реализация List<String> playWith(Playable playable) - метод должен вернуть массив
            результатов в порядке вызова - сначала класс у которого вызвали метод,
            затем класс уоторый передали в качестве аргумента
 */

    public interface Playable {
        ArrayList<String> play();
        ArrayList<String> playWith(Playable playable);
    }
    /*
    Напиши реализацию интерфейса Playable:
        - Метод List<String> play()
        - Метод List<String> playWith(Playable playable)*/
    public static abstract class Instrument implements Playable{
        protected String sound;
        protected int times;

        public Instrument(String sound, int times) {
            this.sound = sound;
            this.times = times;
        }

        @Override
        public ArrayList<String> play() {
            ArrayList<String> res=new ArrayList<>();
            String str="";
            for(int i=0;i<times;i++){
                if(i!=times-1){
                    str+=sound+" ";
                }
                else{
                    str+=sound;
                }
            }
            res.add(str);
            return res;
        }

        @Override
        public ArrayList<String> playWith(Playable playable) {
            ArrayList<String> res=new ArrayList<>();
            res.addAll(play());
            res.addAll(playable.play());
            return res;
        }
    }
    /*Напиши реализацию абстрактного класса Instrument:
            - Класс должен релизовать интерфейс Playable
        - Поля класса - sound:String, times:Integer (колличество посторейний звука) (+ конструктор/геттеры)
            - Реализация List<String> play() - метод возвращает список из одной
    строки сформированной по правилу (sound + " ") * times
        - Реализация List<String> playWith(Playable playable) - метод должен вернуть массив
    результатов игры интсруметов в порядке вызова - сначала класс у которого вызвали метод,
    затем класс уоторый передали в качестве аргумента*/
    public static class Guitar extends Instrument{
        Guitar() {

            super();
            this.sound= "трунь" ;
            this.times=2 ;

        }
    }
    /*Напиши реализацию классов Guitar и Drum:
            - Классы наследуют класс Instrument
        - Конструкторы классов не должны принимать никакие агрументы
        - sound для Guitar "Трунь" и times 2
            - sound для Drum "Бац" и times 3*/
    public static class Drum extends Instrument{
        Drum(){

             super();
            this.sound= "бац" ;
            this.times=3 ;
        }
    }

    public static class Orchestra implements Playable{
        public ArrayList<Instrument> getInstruments() {
            return instruments;
        }
        ArrayList<Instrument> instruments=new ArrayList<>();
        Orchestra(Instrument... instrument){
            instruments.addAll(Arrays.asList(instrument));
        }

        @Override
        public ArrayList<String> play() {
            ArrayList<String> res=new ArrayList<>();
            for(int i=0;i<instruments.size();i++){
                res.addAll(instruments.get(i).play());
            }
            return res;
        }

        @Override
        public ArrayList<String> playWith(Playable playable) {
            ArrayList<String> res=new ArrayList<>();
            res.addAll(play());
            res.addAll(playable.play());
            return res;
        }
    }
    /*Напиши реализацию класса Orchestra
        - Поля - instruments:List<Instrument> (+геттер)
            - Конструктор класса должен принимать любое число агрументов типа Instrument
    Подстказка: загугли что такое функции с переменным числом агрументов и сделай так же
        - Класс должен реализовать интерфейс Playable
        - Реализация List<String> play() - вызываются методы play всех инструментов оркестра,
    результаты вызовов собираются в спиок и возвращаются
        - Реализация List<String> playWith(Playable playable) - метод должен вернуть массив
    результатов в порядке вызова - сначала класс у которого вызвали метод,
    затем класс уоторый передали в качестве аргумента*/
    public static void main(String[] args) {
        var guitar = new Guitar();
        var drum = new Drum();
        print("Guitar: гитару создали", true);
        print("Drum:   барабан создали", true);
        print("Guitar: play Guitar должно быть " + GUITAR_R, Objects.equals(guitar.play().get(0), GUITAR_R));
        print("Drum:   play Drum должно быть " + DRUM_R, Objects.equals(drum.play().get(0), DRUM_R));
        ArrayList<String> guitarWithDrum = guitar.playWith(drum);
        print("Guitar: playWith Drum первая гитара", Objects.equals(guitarWithDrum.get(0), GUITAR_R));
        print("Guitar: playWith Drum последний барабан", Objects.equals(guitarWithDrum.get(1), DRUM_R));
        ArrayList<String> drumWithGuitar = drum.playWith(guitar);
        print("Drum:   playWith Guitar первый барабан", Objects.equals(drumWithGuitar.get(0), DRUM_R));
        print("Drum:   playWith Guitar последняя гитара", Objects.equals(drumWithGuitar.get(1), GUITAR_R));

        var emptyOrchestra = new Orchestra();
        var orchestra = new Orchestra(new Guitar(), new Drum(), new Guitar(), new Drum());
        print("EmptyOrchestra: Пустой оркестр создался", true);
        print("EmptyOrchestra: Инструменты должны быть пустым списком", emptyOrchestra.getInstruments() != null);
        print("Orchestra: Оркестр создался", true);
        print("Orchestra: В оркестре должно быть 4 инструмента", orchestra.getInstruments().size() == 4);
        ArrayList<String> orchestraPlay = orchestra.play();
        print("Orchestra: Должны сыграть 4 инструмента", orchestraPlay.size() == 4);
        print("Orchestra: Гитара играет первая", Objects.equals(orchestraPlay.get(0), GUITAR_R));
        print("Orchestra: Барабан играет второй", Objects.equals(orchestraPlay.get(1), DRUM_R));
        print("Orchestra: Гитара играет третья", Objects.equals(orchestraPlay.get(2), GUITAR_R));
        print("Orchestra: Барабан играет четвертый", Objects.equals(orchestraPlay.get(3), DRUM_R));
        ArrayList<String> orchestraPlayWithGuitar = orchestra.playWith(new Guitar());
        print("Orchestra: Должны сыграть 5 инструментов", orchestraPlayWithGuitar.size() == 5);
        print("Orchestra: Гитара играет последняя", Objects.equals(orchestraPlayWithGuitar.get(4), GUITAR_R));
    }

    /* Техническая секция - сюда писать ничего не надо */

    private static void print(String condition, Boolean act) {
        Function<String, String> yellow = str -> "\u001B[33m" + str + "\u001B[0m";
        System.out.print( "TEST CASE " + yellow.apply(constLen(condition, 55)));
        if (act) System.out.print("✅"); else System.out.print("❌");
        System.out.println();
    }

    private static String constLen(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        while (len-- - str.length() > 0) sb.append(" ");
        return sb.toString();
    }

    private static final String GUITAR_R = "Трунь Трунь";
    private static final String DRUM_R = "Бац Бац Бац";
}