package ee.taltech.inbankbackend.service;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import ee.taltech.inbankbackend.exceptions.InvalidPersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class AgeValidatorTest {
    @InjectMocks
    private AgeValidator ageValidator;

    private String debtorPersonalCode;
    private String segment3PersonalCode;

    @BeforeEach
    void setUp() {
        debtorPersonalCode = "30905030299";
        segment3PersonalCode = "34406069515";
    }
    @Test
    void testAgeValidator() {
        PersonalCodeException personalCodeException= assertThrows(PersonalCodeException.class,
                () -> AgeValidator.validate(segment3PersonalCode, 12));
    }
    @Test
    void testPersonalCode() {
        PersonalCodeException personalCodeException = assertThrows(PersonalCodeException.class,
                () -> AgeValidator.validate(debtorPersonalCode, 12));
    }
    @Test
    void testPersonalCode2() {
        String invalidPersonalCode = "12345678901";
        PersonalCodeException personalCodeException = assertThrows(PersonalCodeException.class,
                () -> AgeValidator.validate(invalidPersonalCode, 12));
    }

}