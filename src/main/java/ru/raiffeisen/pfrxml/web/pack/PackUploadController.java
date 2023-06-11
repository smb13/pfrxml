package ru.raiffeisen.pfrxml.web.pack;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.raiffeisen.pfrxml.web.FileModel;
import ru.raiffeisen.pfrxml.web.FileValidator;

@Controller
@RequestMapping("/uploading")
public class PackUploadController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ServletContext context;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView fileUploadPage() {
        FileModel file = new FileModel();
        ModelAndView modelAndView = new ModelAndView("uploading", "command", file);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fileUpload(@Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
        MultipartFile multipartFile = file.getFile();
        new FileValidator().validate(file, result);
        if (result.hasErrors()) {
            log.warn("validation file errors");
            return "pfrxml";
        } else {
            log.info("Fetching file");
            String uploadPath = context.getRealPath("") + File.separator + "temp" + File.separator;
            File uploadedFile = new File(uploadPath+file.getFile().getOriginalFilename());
            new File(uploadPath).mkdirs();
            if (uploadedFile.exists()) {
                uploadedFile.delete();
            }
            uploadedFile.createNewFile();
            FileCopyUtils.copy(file.getFile().getBytes(), uploadedFile);
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            log.info("Fetching file finished successfully");
            return "success";
        }
    }
}
