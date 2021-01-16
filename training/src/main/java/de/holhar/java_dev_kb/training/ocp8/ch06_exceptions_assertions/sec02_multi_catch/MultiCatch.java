package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec02_multi_catch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class MultiCatch {

    public static void main(String[] args) {

        applyingMultiCatch();

        multiCatchIsEffectivelyFinal();

        theManyErrorsMultiCatchCanHave();
    }

    private static void applyingMultiCatch() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            LocalDate date = LocalDate.parse(text);
            println(date);

            // Catching every exception on its own - duplicate code:
        /*
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        */

            // Catching EVERY exception - BAD approach
        /*
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        */

            // Externalize handling - a little bit better, but still duplication and hard to read...
        /*
        } catch (DateTimeParseException e) {
            handleException(e);
        } catch (IOException e) {
            handleException(e);
        }
        */

            // Finally applying multi-catch: What a dream!
        } catch (DateTimeParseException | IOException e) {
            handleException(e);
        }
    }

    private static void handleException(Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }

    private static void multiCatchIsEffectivelyFinal() {
        // This try statement is legal. It is a bad idea to reassign the variable in catch block, it is allowed:
        try {
            // Do some work
        } catch (RuntimeException e) {
            e = new RuntimeException();
        }
        // When adding multi-catch, this pattern is no longer allowed
        try {
            // Do some work
            Files.readAllBytes(Paths.get("blub.txt"));
        } catch (IOException | RuntimeException e) {
            // DOES NOT COMPILE - e is here EFFECTIVELY FINAL
            // e = new RuntimeException();
        }
    }

    // METHOD DOES NOT COMPILE
    private static void theManyErrorsMultiCatchCanHave() {
        try {
            mightThrow();
        } catch (FileNotFoundException | IllegalStateException e) {
            // } catch (InputMismatchException e |MissingResourceException e) { // There are two variable names
            // } catch (SQLException | ArrayIndexOutOfBoundsException e) { // SQLException can not be thrown
            // } catch (FileNotFoundException | IllegalArgumentException e) { // Already handled above
        } catch (Exception e) {
            // } catch (IOException e) { Unreachable because of the priorily handled Exception
        }
    }

    private static void mightThrow() throws DateTimeParseException, IOException {
    }
}
