package com.financeTracker.finance.tracker.controllers.user;

import com.financeTracker.finance.tracker.dto.user.UserRequestDto;
import com.financeTracker.finance.tracker.useCases.user.CreateUserUseCase;
import com.financeTracker.finance.tracker.useCases.user.VerifyUserUseCase;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RegisterUserController {

    private final CreateUserUseCase createUserUseCase;
    private final VerifyUserUseCase verifyUserUseCase;

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserRequestDto userRequestDto) throws MessagingException, UnsupportedEncodingException {
        this.createUserUseCase.execute(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("code") String code) {
        if (verifyUserUseCase.execute(code)){
            return ResponseEntity.ok("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Verification Success</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            background-color: #f4f4f9;\n" +
                    "            display: flex;\n" +
                    "            justify-content: center;\n" +
                    "            align-items: center;\n" +
                    "            height: 100vh;\n" +
                    "            margin: 0;\n" +
                    "        }\n" +
                    "        .message-box {\n" +
                    "            text-align: center;\n" +
                    "            background-color: #e0f7fa;\n" +
                    "            border: 1px solid #00838f;\n" +
                    "            padding: 20px;\n" +
                    "            border-radius: 10px;\n" +
                    "        }\n" +
                    "        h1 {\n" +
                    "            color: #006064;\n" +
                    "        }\n" +
                    "        p {\n" +
                    "            color: #004d40;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"message-box\">\n" +
                    "        <h1>Verification Successful!</h1>\n" +
                    "        <p>Your account has been successfully verified. You can now log in and start using the platform.</p>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>\n");
        }
        return ResponseEntity.badRequest().build();
    }
}

