package org.jtheque.primary.od.able;

import org.jtheque.primary.od.impl.temp.PersonTemporaryContext;

/**
 * A person specification.
 *
 * @author Baptiste Wicht
 */
public interface Person extends Data, Notable {
    /**
     * Sets the name of the person.
     *
     * @param name The name of the person
     */
    void setName(String name);

    /**
     * Sets the first name of the person.
     *
     * @param firstName The first name of the person
     */
    void setFirstName(String firstName);

    /**
     * Set the country of the object.
     *
     * @param country The new country of the data
     */
    void setTheCountry(SimpleData country);

    /**
     * Return the country of the person.
     *
     * @return The country
     */
    SimpleData getTheCountry();

    /**
     * Return the name of the person.
     *
     * @return The name of the person.
     */
    String getName();

    /**
     * Return the first name of the person.
     *
     * @return The first name of the person.
     */
    String getFirstName();

    /**
     * Set the email of the person.
     *
     * @param email The email of the person.
     */
    void setEmail(String email);

    /**
     * Return the email of the person.
     *
     * @return The email of the person.
     */
    String getEmail();

    /**
     * Set the type of the person. For example, it could be Author of Realizer.
     *
     * @param type The type of the person.
     */
    void setType(String type);

    /**
     * Return the type of the person.
     *
     * @return The type of the person.
     */
    String getType();

    @Override
    PersonTemporaryContext getTemporaryContext();

    /**
     * Indicate if the person has a country or not.
     *
     * @return <code>true</code> if the person has a country else <code>false</code>.
     */
    boolean hasCountry();
}
