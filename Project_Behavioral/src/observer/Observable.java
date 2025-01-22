package observer;

import java.util.HashSet;
import java.util.Set;

public abstract class Observable {
    protected Set<Observer> subscribers = new HashSet<>();

    public void addSubscriber(Observer observer) {
        subscribers.add(observer);
    }

    public void removeSubscriber(Observer observer) {
        subscribers.remove(observer);
    }

    public int countOfObservers() {
        return subscribers.size();
    }

    public boolean containsParticularSubscriber(Observer observer) {
        return subscribers.contains(observer);
    }

    public void notifyAllSubscribers(Observable sender, Object message) {
        for (Observer observer : subscribers) {
            observer.update(sender, message);
        }
    }
}
