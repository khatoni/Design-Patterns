package observer;

import hash.ChecksumCalculator;
import hash.MD5Sum;
import org.junit.jupiter.api.Test;
import structure.AbstractFile;
import structure.File;
import visitor.HashStreamWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObserverTest {

    @Test
    void testSuccessfulSubscription() {
        AbstractFile abstractFile = mock();
        ChecksumCalculator calculator = new MD5Sum();
        Observer obs = new ProgressReporter(1000);

        try {
            Observable observable = new HashStreamWriter(calculator, abstractFile, null);
            observable.addSubscriber(obs);
            assertTrue(observable.containsParticularSubscriber(obs), "The subscriber is expected to be subscribed but it is not");

        } catch (FileNotFoundException e) {
            fail();
        }
    }

    @Test
    void testSuccessfulRemoveSubscription () {
        AbstractFile abstractFile = mock();
        ChecksumCalculator calculator = new MD5Sum();
        Observer obs = new ProgressReporter(1000);

        try {
            Observable observable = new HashStreamWriter(calculator, abstractFile, null);
            observable.addSubscriber(obs);
            assertTrue(observable.containsParticularSubscriber(obs), "The subscriber is expected to be subscribed but it is not");

            observable.removeSubscriber(obs);
            assertFalse(observable.containsParticularSubscriber(obs), "The subscriber is expected to be removed");
        } catch (FileNotFoundException e) {
            fail();
        }
    }
}
