import event.Event;
import things.classes.*;
import things.enums.Condition;
import things.interfaces.Lyable;
import things.interfaces.Place;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void prepare(Person rodion, Clothes pants, Clothes hat, Illness cold, Person drunk, Sound quiet, Furniture sofa, Place room, BodyPart head, BodyPart legs) {
        rodion.addForgotten(Condition.COLD);
        rodion.addForgotten(Condition.WORRIED);
        try {
            rodion.setLocation(room);
        } catch (Exception e) {
            room = new LargeItem("room");
            try {
                rodion.setLocation(room);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        pants.broke();
        pants.makeDirty(new SmallItem("запёкшаяся кровь"));

        try {
            rodion.putOnClothes(hat, head);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rodion.putOnClothes(pants, legs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cold.setEffects(Condition.COLD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cold.setStrength(100);
        drunk.addCondition(Condition.DRUNK);
        try {
            drunk.setLocation(new LargeItem("распивочные"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        quiet.setVolume(0);
        sofa.setDefaultPlacesForLie(2);
        sofa.setDefaultPlacesForSit(5);
        try {
            rodion.lie(sofa);
        } catch (Exception ex) {
            rodion.wakeUp();
            try {
                rodion.lie(sofa);
            } catch (Exception ignored) {
            }
        }
        rodion.sleep();
    }

    public static List<Event> thought1(Time current, Person drunk) {
        List<Event> thought1 = new ArrayList<>();
        thought1.add(new Event(drunk, "goOut", current, new Class[]{}));
        return thought1;
    }

    public static List<Event> thought2(Time current, Person rodion) {
        ArrayList<Event> thought2 = new ArrayList<>();
        thought2.add(new Event(rodion, "addCondition", current, new Class[]{Condition.class}, Condition.MAD));
        return thought2;
    }

    public static List<Event> thought3(Time yesterday, Person rodion, Clothes hat, Furniture sofa, OpenableItem door, LargeItem room) {
        ArrayList<Event> thought3 = new ArrayList<>();

        thought3.add(new Event(rodion, "setLocation", yesterday, new Class[]{Place.class}, room));
        thought3.add(new Event(rodion, "forgetToLock", yesterday, new Class[]{OpenableItem.class}, door));
        thought3.add(new Event(rodion, "lie", yesterday, new Class[]{Lyable.class}, sofa));
        thought3.add(new Event(hat, "fall", yesterday));
        thought3.add(new Event(new SmallItem("подушка"), "setLocation", yesterday, new Class[]{Place.class}, new LargeItem("пол")));
        return thought3;
    }

    public static List<Event> thought41(Time yesterday) {
        ArrayList<Event> thought41 = new ArrayList<>();
        Person newRodion = new Person("Я");
        thought41.add(new Event(newRodion, "addCondition", yesterday, new Class[]{Condition.class}, Condition.DRUNK));
        return thought41;
    }

    public static List<Event> thought4(Time yesterday, LargeItem room, Person rodion, Time current) {
        ArrayList<Event> thought4 = new ArrayList<>();
        Person somebody = new Person("кто-то");
        thought4.add(new Event(somebody, "setLocation", yesterday, new Class[]{Place.class}, room));
        thought4.add(new Event(rodion, "think", current, new Class[]{List.class}, thought41(yesterday)));
        return thought4;
    }

    public static void applyTime(Time time, PhysicalItem... args) {
        for (PhysicalItem item : args) {
            if (time.ifDark()) item.addCondition(Condition.DARK);
            else item.addCondition(Condition.LIGHT);
        }
    }

    public static void main(String[] args) throws Exception {
        BodyPart teeth = new BodyPart("зубы");
        BodyPart legs = new BodyPart("ноги");//
        BodyPart head = new BodyPart("голова");
        Person rodion = new Person("Он", teeth, legs, head);
        Clothes hat = new Clothes("шляпа");
        Clothes pants = new Clothes("панталоны");
        Time current = new Time("третий час ночи");
        Furniture sofa = new Furniture("диван");
        Illness cold = new Illness("лихорадка");
        Person drunk = new Person("пьяные");

        LargeItem room = new LargeItem("комната");
        LargeItem corridor = new LargeItem("коридор");
        OpenableItem door = new OpenableItem("дверь", room, corridor);//
        Sound quiet = new Sound("тишина");
        Time yesterday = new Time("вчера");


        prepare(rodion, pants, hat, cold, drunk, quiet, sofa, room, head, legs);
        applyTime(current, rodion, hat, pants, sofa, drunk, teeth, door, room, corridor);

        rodion.addCondition(Condition.STUNNED);
        rodion.hear(new Sound("срашные, отчаянные вопли"));

        rodion.think(thought1(current, drunk));//
        rodion.standUp();//
        rodion.remember();
        rodion.think(thought2(current, rodion));//
        rodion.startIllness(cold);
        teeth.shake();
        rodion.shakeAll();
        rodion.open(door);//
        rodion.hear(quiet);
        rodion.lookAtSelf();//
        rodion.lookAt(new LargeItem("всё кругом"));//
        rodion.think(thought3(yesterday, rodion, hat, sofa, door, room));//

        rodion.think(thought4(yesterday, room, rodion, current));//
        rodion.setLocation(new LargeItem("у окона"));//
        rodion.addCondition(Condition.LIGHT);
        rodion.lookAtSelf();//
        List<PhysicalItem> clothes = new ArrayList<>(rodion.takeOfClothes());//
        rodion.lookAt(clothes);
    }
}
