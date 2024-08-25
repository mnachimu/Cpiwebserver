package com.orisinterview.cpiwebserver;

import com.orisinterview.cpiwebserver.exceptions.InvalidInputException;
import com.orisinterview.cpiwebserver.models.CpiResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class CPIController {

    @Autowired
    Executor executor;

    @PostMapping("/cpi")
    public ResponseEntity<CpiResponseBody> cpiForRequestBody(@RequestBody String req) throws InvalidInputException {
        // Change the request body type if it is not passed as string
        String input = req.trim();
        if (isValidInput(input)) {
            String[] words = input.split(" ");
            String month = words[0].trim();
            String year = words[words.length - 1].trim();
            CpiResponseBody response = executor.getCPIForMonthAndYear(month, year);
            return ResponseEntity.ok(response);
        }
        throw new InvalidInputException("The given input was not in the right format");
    }

    @GetMapping("/cpi")
    public ResponseEntity<CpiResponseBody> getCpiForRequestParameters(@RequestParam String month, String year) throws InvalidInputException {
        if (!month.isEmpty() && !year.isEmpty() && isValidInput(month, year)) {
            CpiResponseBody response = executor.getCPIForMonthAndYear(month, year);
            return ResponseEntity.ok(response);
        }
        throw new InvalidInputException("The given input was not in the right format");
    }

    private boolean isValidInput(String monthYear) {
        String regex = "^[A-Za-z]+\\s\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(monthYear).matches();
    }

    private boolean isValidInput(String month, String year) {
        String reg = "^[A-Za-z]+";
        Pattern patt = Pattern.compile(reg);
        if (patt.matcher(month.trim()).matches()) {
            reg = "^\\d{4}$";
            patt = Pattern.compile(reg);
            return patt.matcher(year).matches();
        }
        return false;
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
