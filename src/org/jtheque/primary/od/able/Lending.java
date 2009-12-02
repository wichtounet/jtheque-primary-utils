package org.jtheque.primary.od.able;

import org.jtheque.primary.od.impl.temp.LendingTemporaryContext;
import org.jtheque.utils.bean.IntDate;

/**
 * @author Baptiste Wicht
 */
public interface Lending extends PrimaryData {
    /**
     * Set the date of the lending.
     *
     * @param date The date of the lending.
     */
    void setDate(IntDate date);

    /**
     * Return the date of the lending.
     *
     * @return The date.
     */
    IntDate getDate();

    /**
     * Set the person.
     *
     * @param thePerson The person.
     */
    void setThePerson(Person thePerson);

    /**
     * Return the person of the lending.
     *
     * @return The person of the lending.
     */
    Person getThePerson();

    /**
     * Set the other of the lending.
     *
     * @param other The other of the lending.
     */
    void setTheOther(int other);

    /**
     * Return the other.
     *
     * @return The other.
     */
    int getTheOther();

    @Override
    LendingTemporaryContext getTemporaryContext();
}
