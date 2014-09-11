package edu.hm.cs.fs.scriptinat0r7.testfixtures;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * {@code EnumVerifier} can be used in unit tests to check the ordering and naming of your {@code Enum} elements.
 *
 * @author Maximilian Götz
 *
 * @param <T>
 *            the enum to be tested.
 */
public final class EnumVerifier<T extends Enum<T>> {

    private final List<T> enumElements;
    private final Class<T> type;

    private EnumVerifier(final List<T> enumElements, final Class<T> type) {
        this.enumElements = enumElements;
        this.type = type;
    }

    /**
     * Factory method to create an instance of {@code EnumVerifier} with the specified {@code Enum} type.
     *
     * @param type
     *            the type of the {@code Enum}.
     * @return a new instance of {@code EnumVerifier}.
     * @param <T>
     *            the type of the {@code Enum}.
     */
    public static <T extends Enum<T>> EnumVerifier<T> forClass(final Class<T> type) {
        List<T> enumElements = new LinkedList<T>();
        return new EnumVerifier<T>(enumElements, type);
    }

    /**
     * Adds the specified {@code Enum} element to be tested. It is specified as a {@code String} to ensure that the
     * spelling of the {@code Enum} element is correct and that the {@code valueOf} method works properly.
     *
     * @param enumString
     *            the name of the {@code Enum} element to be tested.
     * @return the actual instance of {@code EnumVerifier}.
     */
    public EnumVerifier<T> withEnumElement(final String enumString) {
        T element = getValueOf(enumString);
        this.enumElements.add(element);
        return this;
    }

    @SuppressWarnings("unchecked")
    private T getValueOf(final String enumString) {
        try {
            return (T) type.getMethod("valueOf", String.class).invoke(null, enumString);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |
                NoSuchMethodException | SecurityException e) {
            throw new AssertionError("No such Enum element with name " + enumString + " in Enum type "
                    + type.getSimpleName() + ".", e);
        }
    }

    /**
     * Verifies the length and ordering of the specified {@code Enum} elements.
     */
    public void verify() {
        verifyLengthOfEnumElementList();
        verifyCorrectElementOrdering();
    }

    private void verifyLengthOfEnumElementList() throws AssertionError {
        int realEnumLength = type.getEnumConstants().length;
        int givenEnumsLength = enumElements.size();

        if (realEnumLength > givenEnumsLength) {
            throw new AssertionError("More Enum constants exist than specified 'withEnumElement(enum)'.");
        }

        if (realEnumLength < givenEnumsLength) {
            throw new AssertionError("There are more Enum constants specified than exist.");
        }
    }

    private void verifyCorrectElementOrdering() throws AssertionError {
        for (int i = 0; i < enumElements.size(); i++) {
            T actual = enumElements.get(i);
            int ordinal = actual.ordinal();
            if (ordinal != i) {
                throw new AssertionError("The Enum element " + type.getSimpleName() + "." + actual.toString()
                        + " is at the wrong position; expected at <" + ordinal + ">, but was <" + i + ">.");
            }
        }
    }
}
