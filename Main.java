import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Living rodion = new Living("Он");
        rodion.addForgotten(Condition.COLD);
        rodion.addForgotten(Condition.WORRIED);
        Clothes hat = new Clothes("шляпа");
        Clothes pants = new Clothes("панталоны");
        pants.broke();
        pants.makeDirty(new SmallItem("запёкшаяся кровь"));

        rodion.putOnClothes(hat, new BodyPart("голова", rodion));
        rodion.putOnClothes(pants, new BodyPart("ноги", rodion));
        Time current = new Time("третий час ночи");
        ArrayList<Event> text = new ArrayList<>();
        Furniture sofa = new Furniture("диван");

        text.add(new Event(rodion,  "lie",current, new Class[]{Lyable.class}, (Lyable)sofa));
        text.add(new Event(rodion,"addCondition", current,new Class[]{Condition.class}, Condition.STUNNED));
        text.add(new Event(rodion, "hear", current, new Class[]{Sound.class},new Sound("срашные, отчаянные вопли") ));
        ArrayList<Event> thought1 = new ArrayList<>();
        Living drunk = new Living("пьяные");
        drunk.addCondition(Condition.DRUNK);
        drunk.setLocation(new LargeItem("распивочные"));

        thought1.add(new Event(drunk, "goOut", current, new Class[]{}));
        text.add(new Event(rodion, "think",current, new Class[]{ArrayList.class}, thought1 ));

        text.add(new Event(rodion, "standUp", current));
        text.add(new Event(rodion, "remember", current));

        ArrayList<Event> thought2 = new ArrayList<>();
        thought2.add(new Event(rodion,  "addCondition", current,new Class[]{Condition.class}, Condition.MAD));
        text.add(new Event(rodion, "think",current, new Class[]{ArrayList.class}, thought2 ));

        Illness l = new Illness("лихорадка");
        l.setEffects(Condition.COLD);
        text.add(new Event(rodion, "startIllness", new Time ("давно"),new Class[]{Illness.class},l ));

        BodyPart teeth = new BodyPart("зубы", rodion);
        BodyPart everything = new BodyPart("всё", rodion);
        text.add(new Event(teeth, "shake", current));
        text.add(new Event(everything, "shake", current)); // ???
        OpenableItem door = new OpenableItem("дверь");
        text.add(new Event(rodion, "open", current,new Class[]{OpenableItem.class},door ));

        Sound quiet = new Sound("тишина");
        quiet.setVolume(0);
        text.add(new Event(rodion, "hear", current, new Class[]{Sound.class},quiet ));

        text.add(new Event(rodion, "lookAtSelf", current));
        text.add(new Event(rodion,"lookAt", current, new Class[]{PhysicalItem.class},new LargeItem("всё кругом") ));

        ArrayList<Event> thought3 = new ArrayList<>();
        LargeItem room = new LargeItem("комната");
        Time yesterday = new Time("вчера");
        thought3.add(new Event(rodion, "setLocation" ,yesterday, new Class[]{Place.class}, room));
        thought3.add(new Event(rodion, "forgetToLock", yesterday, new Class[]{OpenableItem.class}, door));
        thought3.add(new Event(rodion, "lie",yesterday, new Class[]{Lyable.class}, (Sitable)sofa));
        thought3.add(new Event(hat, "fall", yesterday));
        thought3.add(new Event(new SmallItem("подушка"), "setLocation", yesterday, new Class[]{Place.class}, new LargeItem("пол")));
        text.add(new Event(rodion, "think",current, new Class[]{ArrayList.class}, thought3 ));

        ArrayList<Event> thought4 = new ArrayList<>();
        Living somebody = new Living("кто-то");
        thought4.add(new Event(somebody,"setLocation" ,yesterday,new Class[]{ Place.class}, room));

        ArrayList<Event> thought41 = new ArrayList<>();
        Living newRodion = new Living("Я");
        thought41.add(new Event(newRodion, "addCondition", yesterday, new Class[]{Condition.class}, Condition.DRUNK ));
        thought4.add(new Event(rodion, "think",current, new Class[]{ArrayList.class}, thought41 ));;
        text.add(new Event(rodion, "think",current, new Class[]{ArrayList.class}, thought4 ));


        text.add(new Event(rodion, "setLocation", current,new Class[]{Place.class}, new LargeItem("у окона")));
        text.add(new Event(rodion, "addCondition", current,new Class[]{Condition.class}, Condition.LIGHT ));
        text.add(new Event(rodion, "lookAtSelf", current));
        Event e = new Event(rodion, "takeOfClothes", current);
        e.setReturnVal(true);
        text.add(e);
        Event event = new Event(rodion,"lookAt", current, new Class[]{ArrayList.class} );
        event.setTimes(3);
        text.add(event);

        Object cur = null;
        for (Event x: text){
            cur = x.exec(cur);
        }
    }
}
