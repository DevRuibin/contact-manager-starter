package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * Before ALL
 * Before Each
 * Test
 * After Each
 * After All
 * */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    ContactManager contactManager;
    @BeforeAll
    public void setupAll() {
        System.out.print("Should Print Before All Tests\n");
    }

    @BeforeEach
    void setUp() {
        System.out.print("Should Print Before Each Test\n");
        this.contactManager = new ContactManager();
    }
    @Test
    @Disabled
    public void shouldCreateContact() {
        contactManager.addContact("John", "Doe", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(contactManager.getAllContacts().size(), 1);
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact ->
                    contact.getFirstName().equals("John") &&
                    contact.getLastName().equals("Doe") &&
                    contact.getPhoneNumber().equals("0123456789")
                ));
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldNotCreateContactWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact(null, "Doe", "0123456789"));
    }

    @Test
    @DisplayName("Should Not Create Contact When Last Name is Null")
    public void shouldNotCreateContactWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact("john", null, "0123456789"));
    }

    @Test
    @DisplayName("Should Not Create Contact When Phone Number is Null")
    public void shouldNotCreateContactWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact("john", "Doe", null));
    }

    @Test
    @DisplayName("Should Not Create Contact on Windows")
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Disabled on Windows OS")
    public void shouldNotCreateContactWhenFirstNameIsNullOnlyOnWindows() {
        System.out.println("(Not On Windows)Should Not Create Contact When First Name is Null");
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact(null, "Doe", "0123456789"));
    }

    @Test
    @DisplayName("Should Not Create Contact on Mac")
    @DisabledOnOs(value = OS.MAC, disabledReason = "Disabled on Mac OS")
    public void shouldNotCreateContactWhenFirstNameIsNullOnlyOnMac() {
        System.out.println("(Not On Mac)Should Not Create Contact When First Name is Null");
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact(null, "Doe", "0123456789"));
    }

    @Test
    @DisplayName("Should Only be executed on DEV environment")
    public void shouldNotCreateContactWhenFirstNameIsNullOnlyOnDev() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        System.out.println("Should Only be executed on DEV environment");
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact(null, "Doe", "0123456789"));
    }

    @RepeatedTest(value = 5)
    @DisplayName("Repeated Test")
    public void shouldNotCreateContactWhenPhoneNumberIsNullMultipleTimes() {
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact("john", "Doe", null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
    public void shouldCreateContactValuesSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(contactManager.getAllContacts().size(), 1);
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact ->
                        contact.getFirstName().equals("John") &&
                                contact.getLastName().equals("Doe") &&
                                contact.getPhoneNumber().equals("0123456789")
                ));
    }

    @ParameterizedTest
    @MethodSource("com.programming.techie.Utils#phoneNumberList")
    public void shouldCreateContactMethodSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(contactManager.getAllContacts().size(), 1);
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact ->
                        contact.getFirstName().equals("John") &&
                                contact.getLastName().equals("Doe") &&
                                contact.getPhoneNumber().equals("0123456789")
                ));
    }

    @ParameterizedTest
    @CsvSource({"0123456789", "0123456789", "0123456789"})
    public void shouldCreateContactCsvSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(contactManager.getAllContacts().size(), 1);
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact ->
                        contact.getFirstName().equals("John") &&
                                contact.getLastName().equals("Doe") &&
                                contact.getPhoneNumber().equals("0123456789")
                ));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void shouldCreateContactCsvFileSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(contactManager.getAllContacts().size(), 1);
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact ->
                        contact.getFirstName().equals("John") &&
                                contact.getLastName().equals("Doe") &&
                                contact.getPhoneNumber().equals("0123456789")
                ));
    }

    @AfterEach
    void tearDown() {
        System.out.print("Should Print After Each Test\n");
    }

    @AfterAll
    public void tearDownAll() {
        System.out.print("Should Print After All Test\n");
    }

    @Nested
    class RepeatedNestedTest{
        @Test
        @DisplayName("Should Not Create Contact When First Name is Null")
        public void shouldNotCreateContactWhenFirstNameIsNull() {
            Assertions.assertThrows(RuntimeException.class,
                    () -> contactManager.addContact(null, "Doe", "0123456789"));
        }

        @Test
        @DisplayName("Should Not Create Contact When Last Name is Null")
        public void shouldNotCreateContactWhenLastNameIsNull() {
            Assertions.assertThrows(RuntimeException.class,
                    () -> contactManager.addContact("john", null, "0123456789"));
        }

        @Test
        @DisplayName("Should Not Create Contact When Phone Number is Null")
        public void shouldNotCreateContactWhenPhoneNumberIsNull() {
            Assertions.assertThrows(RuntimeException.class,
                    () -> contactManager.addContact("john", "Doe", null));
        }
    }

    @Nested
    class ParameterizedNestedTest{
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
        public void shouldCreateContactValuesSource(String phoneNumber) {
            contactManager.addContact("John", "Doe", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(contactManager.getAllContacts().size(), 1);
            Assertions.assertTrue(contactManager.getAllContacts().stream()
                    .anyMatch(contact ->
                            contact.getFirstName().equals("John") &&
                                    contact.getLastName().equals("Doe") &&
                                    contact.getPhoneNumber().equals("0123456789")
                    ));
        }

        @ParameterizedTest
        @MethodSource("com.programming.techie.Utils#phoneNumberList")
        public void shouldCreateContactMethodSource(String phoneNumber) {
            contactManager.addContact("John", "Doe", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(contactManager.getAllContacts().size(), 1);
            Assertions.assertTrue(contactManager.getAllContacts().stream()
                    .anyMatch(contact ->
                            contact.getFirstName().equals("John") &&
                                    contact.getLastName().equals("Doe") &&
                                    contact.getPhoneNumber().equals("0123456789")
                    ));
        }

        @ParameterizedTest
        @CsvSource({"0123456789", "0123456789", "0123456789"})
        public void shouldCreateContactCsvSource(String phoneNumber) {
            contactManager.addContact("John", "Doe", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(contactManager.getAllContacts().size(), 1);
            Assertions.assertTrue(contactManager.getAllContacts().stream()
                    .anyMatch(contact ->
                            contact.getFirstName().equals("John") &&
                                    contact.getLastName().equals("Doe") &&
                                    contact.getPhoneNumber().equals("0123456789")
                    ));
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        public void shouldCreateContactCsvFileSource(String phoneNumber) {
            contactManager.addContact("John", "Doe", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(contactManager.getAllContacts().size(), 1);
            Assertions.assertTrue(contactManager.getAllContacts().stream()
                    .anyMatch(contact ->
                            contact.getFirstName().equals("John") &&
                                    contact.getLastName().equals("Doe") &&
                                    contact.getPhoneNumber().equals("0123456789")
                    ));
        }
    }
}

