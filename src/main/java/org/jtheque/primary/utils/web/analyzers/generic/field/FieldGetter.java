package org.jtheque.primary.utils.web.analyzers.generic.field;

import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;

/**
 * @author Baptiste Wicht
 */
public interface FieldGetter {
    /**
     * Indicate if the getter must analyze the line or not.
     *
     * @param line The line to analyze.
     *
     * @return true if the getter must analyze the line, else false.
     */
    boolean mustGet(String line);

    /**
     * Return the value for the line.
     *
     * @param line The line.
     *
     * @return The value of the field.
     */
    String getValue(String line);

    /**
     * Return the name of the field for which the getter is made.
     *
     * @return The name of the field.
     */
    String getFieldName();

    /**
     * Perform the operations on the line. The operations are performed before the getter analyze the line.
     *
     * @param line     The line.
     * @param analyzer The analyzer.
     *
     * @return The line eventually modified by the different operations.
     */
    String performOperations(String line, ScannerPossessor analyzer);
}
