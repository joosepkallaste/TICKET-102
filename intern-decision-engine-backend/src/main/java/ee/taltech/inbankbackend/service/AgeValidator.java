package ee.taltech.inbankbackend.service;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeParser;
import ee.taltech.inbankbackend.exceptions.InvalidPersonalCodeException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AgeValidator {

    private static final EstonianPersonalCodeParser parser = new EstonianPersonalCodeParser();

    public static void validate(String personalCode, int loanPeriodMonths) throws  PersonalCodeException, InvalidPersonalCodeException {
        LocalDate birthDate = parser.getDateOfBirth(personalCode);
        if (birthDate == null) {
            throw new InvalidPersonalCodeException("Could not determine birthdate from personal code.");
        }

 //       String country = validator.getCountry(personalCode);
 //       if (!("EE".equals(country) || "LV".equals(country) || "LT".equals(country))) {
 //           throw new NoValidLoanException("Loan service only available for Baltic countries.");
 //       }

        int age = Period.between(birthDate, LocalDate.now()).getYears();
        int maxLoanYears = loanPeriodMonths / 12;
        int expectedLifetime = 78;

        if (age < 18) {
            throw new InvalidPersonalCodeException("Customer is underage.");
        }

        if (age > (expectedLifetime - maxLoanYears)) {
            throw new InvalidPersonalCodeException("Customer exceeds age limit for loan approval.");
        }
    }
}
