package ru.mshamanin.pfrxml.web;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PackValidator implements Validator {

    public static final String PACK_FILENAME_REGEXP = "^\\d{3}(0[1-9]|[12]\\d|3[01])(0[1-9]|1[012])\\d{2}.\\d{3}$";

    @Override
    public void validate(@NonNull Object uploadedFile, @NonNull Errors errors) {
        Pattern pattern = Pattern.compile(PACK_FILENAME_REGEXP);
        FileModel file = (FileModel) uploadedFile;
        Matcher matcher = pattern.matcher(Objects.requireNonNull(file.getFile().getOriginalFilename()));

        if (file.getFile().getSize() == 0 || !matcher.matches()) {
            errors.rejectValue("file", "uploadForm.selectFile", "Please select a not empty file!");
        }
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return FileModel.class.isAssignableFrom(clazz);
    }
}